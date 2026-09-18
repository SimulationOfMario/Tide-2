package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.List;

//? if >=1.21 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.enchantment.ItemEnchantments;
//?} else {
/*import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import java.util.Map;
*///?}

public class EnchantmentsCondition extends FishingCondition {
    public static final MapCodec<EnchantmentsCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceKey.codec(Registries.ENCHANTMENT).listOf().optionalFieldOf("enchantments",List.of()).forGetter(EnchantmentsCondition::getEnchantments),
            Codec.STRING.optionalFieldOf("match", "any").forGetter(EnchantmentsCondition::getMatch)
    ).apply(instance, EnchantmentsCondition::new));

    private final List<ResourceKey<Enchantment>> enchantments;
    private final String match;

    public EnchantmentsCondition(List<ResourceKey<Enchantment>> enchantments, String match) {
        this.enchantments = enchantments;
        this.match = match;
    }

    public List<ResourceKey<Enchantment>> getEnchantments() {
        return enchantments;
    }

    public String getMatch() {
        return match;
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
        ItemEnchantments rodEnchantments = context.hook().rod().getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        //?} else {
        /*Map<Enchantment, Integer> rodEnchantments = EnchantmentHelper.getEnchantments(context.hook().rod());
        *///?}

        // When using 'all', an empty list always results in true.

        return switch (match) {

            // Note: When using 'all', if enchantments list is empty, the result is always true.
            case "all" ->
                //? if >=1.21 {
                this.enchantments.stream().allMatch(e -> rodEnchantments.keySet().stream().anyMatch(e2 -> e2.is(e)));
                //?} else {
                /*this.enchantments.stream().allMatch(e ->
                    rodEnchantments.keySet().stream().anyMatch(e2 ->
                        e.location().equals(BuiltInRegistries.ENCHANTMENT.getKey(e2))));
                *///?}

            // This applies whether you set it to 'any' or enter something incorrect.
            // Note: When using 'any', if enchantments list is empty, the result is always false.
            default ->
                //? if >=1.21 {
                rodEnchantments.keySet().stream().anyMatch(e -> this.enchantments.stream().anyMatch(e::is));
                //?} else {
                /*rodEnchantments.keySet().stream().anyMatch(e ->
                    this.enchantments.stream().anyMatch(e2 ->
                        e2.location().equals(BuiltInRegistries.ENCHANTMENT.getKey(e))));
                *///?}
        };
    }
}
