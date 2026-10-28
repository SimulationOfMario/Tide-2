package com.li64.tide.mixin;

import com.li64.tide.Tide;
import com.li64.tide.config.TideServerConfig;
import com.li64.tide.data.FishLengthHolder;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.item.TideItemData;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//? if >=1.21 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
//?}

import java.util.Objects;
import java.util.Optional;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(at = @At(value = "RETURN"), method = "overrideStackedOnOther", cancellable = true)
    public void overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player, CallbackInfoReturnable<Boolean> cir) {
        if (Tide.SERVER_CONFIG.items.bucketableFishItems != TideServerConfig.Items.BucketableMode.NEVER
                && !(stack.getItem() instanceof MobBucketItem)) {
            if (tide$tryContainFish(slot, slot.getItem(), stack, true, player)) cir.setReturnValue(true);
        }
    }

    @Inject(at = @At(value = "RETURN"), method = "overrideOtherStackedOnMe", cancellable = true)
    public void overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access, CallbackInfoReturnable<Boolean> cir) {
        if (Tide.SERVER_CONFIG.items.bucketableFishItems != TideServerConfig.Items.BucketableMode.NEVER
                && !(stack.getItem() instanceof MobBucketItem)) {
            if (tide$tryContainFish(slot, other, stack, false, player)) cir.setReturnValue(true);
        }
    }

    @Unique
    public boolean tide$tryContainFish(Slot slot, ItemStack fish, ItemStack container, boolean containerCarried, Player player) {
        Optional<FishData> dataOp = FishData.get(fish);
        if (dataOp.isEmpty()) return false;

        if (Tide.SERVER_CONFIG.items.bucketableFishItems == TideServerConfig.Items.BucketableMode.WHEN_LIVING
                && !TideItemData.IS_BUCKETABLE.getOrDefault(fish, false))
            return false;

        // Containers that already hold a fish, some aren't MobBucketItems (Blue Skies ventium bucket)
        if (FishData.fromBucket(container).isPresent()) return false;

        Item result = tide$findContainerResult(dataOp.get(), container.getItem());
        if (result == null) return false;

        ItemStack newStack = new ItemStack(result);
        // keep the container's own data, like the durability and enchantments of a jellyfish net
        /*? if >=1.21 {*/
        newStack.applyComponents(container.getComponentsPatch());
        /*?} else*/
        /*if (container.hasTag()) newStack.setTag(Objects.requireNonNull(container.getTag()).copy());*/

        if (TideItemData.FISH_LENGTH.isPresent(fish)) {
            double length = TideItemData.FISH_LENGTH.getOrDefault(fish, 0.0);

            /*? if >=1.21 {*/
            CustomData.update(DataComponents.BUCKET_ENTITY_DATA, newStack, tag -> {
                if (TideItemData.FISH_LENGTH.isPresent(fish)) tag.putDouble(FishLengthHolder.tide$LENGTH_KEY, length);
            });
            /*?} else*/
            /*newStack.getOrCreateTag().putDouble(FishLengthHolder.tide$LENGTH_KEY, length);*/
        }

        fish.shrink(1);
        container.shrink(1);

        ItemStack inSlot = containerCarried ? fish : container;
        ItemStack carried = containerCarried ? container : fish;
        if (inSlot.isEmpty()) slot.set(newStack);
        else {
            slot.setChanged();
            if (carried.isEmpty()) player.containerMenu.setCarried(newStack);
            else if (!player.getInventory().add(newStack)) player.drop(newStack, false);
        }

        player.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0f, 1.0f);
        return true;
    }

    // Picks the item from the fish's bucket list that has the same container material and fluid as the held container
    @Unique
    private static @Nullable Item tide$findContainerResult(FishData data, Item held) {
        Optional<TagKey<Item>> heldMaterial = tide$getContainerMaterial(held);

        // anything that isn't a bucket needs a material tag, otherwise any item could hold fish
        if (!(held instanceof BucketItem) && heldMaterial.isEmpty()) return null;

        Fluid heldFluid = tide$getContainedFluid(held);
        for (Holder<Item> holder : data.buckets()) {
            Item candidate = holder.value();
            if (tide$getContainerMaterial(candidate).equals(heldMaterial)
                    && tide$getContainedFluid(candidate).isSame(heldFluid)) return candidate;
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    @Unique
    private static Optional<TagKey<Item>> tide$getContainerMaterial(Item item) {
        return item.builtInRegistryHolder().tags()
            .filter(tag ->
                tag.location().getNamespace().equals(Tide.MOD_ID) &&
                tag.location().getPath().startsWith("fish_containers/")
            )
            .findFirst();
    }

    // Items that aren't buckets count as holding no fluid
    @Unique
    private static Fluid tide$getContainedFluid(Item item) {
        Fluid fluid = item instanceof BucketItem bucket ? tide$getFluid(bucket) : null;
        return fluid != null ? fluid : Fluids.EMPTY;
    }

    @Unique
    private static @NotNull Fluid tide$getFluid(BucketItem bucket) {
        //? if !forge {
        return bucket.content;
        //?} else {
        /*return bucket.getFluid();
         *///?}
    }
}
