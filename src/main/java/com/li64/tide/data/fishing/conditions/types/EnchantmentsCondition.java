package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

//? if >=1.21 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.enchantment.ItemEnchantments;
//?} else {
/*import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import java.util.Map;
*///?}

public class EnchantmentsCondition extends FishingCondition {
    public static final MapCodec<EnchantmentsCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            EnchantmentRequirement.CODEC.listOf().optionalFieldOf("enchantments", List.of()).forGetter(EnchantmentsCondition::getEnchantments),
            Codec.STRING.optionalFieldOf("match", "any").forGetter(EnchantmentsCondition::getMatch)
    ).apply(instance, EnchantmentsCondition::new));

    private final List<EnchantmentRequirement> enchantments;
    private final String match;

    public EnchantmentsCondition(List<EnchantmentRequirement> enchantments, String match) {
        this.enchantments = enchantments;
        this.match = match;
    }

    public List<EnchantmentRequirement> getEnchantments() {
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
    public boolean test(FishingContext context) {
        if (context.hook() == null || context.hook().rod() == null || context.hook().rod().isEmpty()) return false;

        //? if >=1.21 {
        ItemEnchantments rodEnchantments = context.hook().rod().getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        //?} else {
        /*Map<Enchantment, Integer> rodEnchantments = EnchantmentHelper.getEnchantments(context.hook().rod());
        *///?}

        return switch (this.match) {

            // Note: When using 'all', if enchantments list is empty, the result is always true.
            case "all" ->
                this.enchantments.stream().allMatch(e -> anyMatches(e, rodEnchantments));

            // Default always applies whether you set it to 'any' or enter something incorrect.
            // Note: When using 'any', if enchantments list is empty, the result is always false.
            default ->
                this.enchantments.stream().anyMatch(e -> anyMatches(e, rodEnchantments));
        };
    }

    private boolean anyMatches(EnchantmentRequirement requirement,
                               /*? if >=1.21 {*/ ItemEnchantments /*?} else {*/ /*Map<Enchantment, Integer> *//*?}*/ candidates)
    {
        return candidates.entrySet().stream().anyMatch(entry ->
                    requirement.matches(entry.getKey(), /*? if >=1.21 {*/ entry.getIntValue() /*?} else {*/ /*entry.getValue() *//*?}*/));
    }
}
