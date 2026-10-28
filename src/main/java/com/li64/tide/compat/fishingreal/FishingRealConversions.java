//? if fabric {
package com.li64.tide.compat.fishingreal;

import com.li64.tide.Tide;
import com.li64.tide.compat.CompatHelper;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.datagen.fabric.providers.SimpleDataOutput;
import com.li64.tide.datagen.fabric.providers.SimpleDataProvider;
import com.li64.tide.registries.TideFish;
import com.mojang.serialization.Codec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class FishingRealConversions extends SimpleDataProvider<FishingConversion> {
    private static final String MOD_ID = CompatHelper.Namespace.FISHING_REAL;

    public FishingRealConversions(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super("fishing", output, registries);
    }

    @Override
    public @NotNull String getName() {
        return "Fishing Real Conversions";
    }

    @Override
    protected Codec<FishingConversion> dataCodec() {
        return FishingConversion.CODEC;
    }

    @Override
    protected void generate(SimpleDataOutput<FishingConversion> output) {
        TideFish.DATA_BUILDERS.forEach((item, constructor) -> {
            FishData.Builder builder = FishData.builder();
            constructor.accept(builder.fish(item));
            FishData data = builder.build();
            if (data.display().isEmpty()) return;
            ResourceLocation path = Tide.resource(MOD_ID, BuiltInRegistries.ITEM.getKey(data.fish().value()).getPath());
            output.accept(path, new FishingConversion(
                new ItemStack(data.fish().value()),
                new FishingConversion.FishingResult(data.display().get().entityHolder(), Optional.empty())
            ));
        });
    }
}
//?}