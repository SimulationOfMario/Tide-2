package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

//? if >=1.21 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.enchantment.ItemEnchantments;
//?} else {
/*import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.core.registries.BuiltInRegistries;
*///?}

import java.util.List;
import java.util.Map;

public class EnchantmentsCondition extends FishingCondition {
    public static final MapCodec<EnchantmentsCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceKey.codec(Registries.ENCHANTMENT).listOf().optionalFieldOf("enchantments",List.of()).forGetter(EnchantmentsCondition::getEnchantments)
    ).apply(instance, EnchantmentsCondition::new));

    private final List<ResourceKey<Enchantment>> enchantments;

    public EnchantmentsCondition(List<ResourceKey<Enchantment>> enchantments) {
        this.enchantments = enchantments;
    }

    public List<ResourceKey<Enchantment>> getEnchantments() {
        return enchantments;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.HAS_ENCHANTMENTS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean test(FishingContext context) {
        if (context.hook() == null || context.hook().rod() == null || context.hook().rod().isEmpty()) return false;

        //? if >=1.21 {
        ItemEnchantments enchantments = context.hook().rod().getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        //?} else {
        /*Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(context.hook().rod());
        *///?}

        // If the list of enchantments you provided is empty (or if you don't provide one),
        // it means you simply want the fishing rod to have at least one enchantment (any enchantment will do).
        if (this.enchantments.isEmpty()) return !enchantments.isEmpty();

        //? if >=1.21 {
        return enchantments.keySet().stream().anyMatch(e -> this.enchantments.stream().anyMatch(e::is));
        //?} else {
        /*return enchantments.keySet().stream().anyMatch(e ->
                this.enchantments.stream().anyMatch(e2 -> e2.location().equals(BuiltInRegistries.ENCHANTMENT.getKey(e))));
        *///?}
    }
}
