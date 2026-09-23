package com.li64.tide.data.fishing;

import com.li64.tide.data.ModAssociatedEntry;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.modifiers.FishingModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record CrateSelectorData(
                        List<String> associatedMods,
                        List<FishingCondition> conditions,
                        List<FishingModifier> modifiers) implements ModAssociatedEntry {

    public static final Codec<CrateSelectorData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.listOf().optionalFieldOf("associated_mods", List.of()).forGetter(CrateSelectorData::associatedMods),
            FishingCondition.CODEC.listOf().optionalFieldOf("conditions", List.of()).forGetter(CrateSelectorData::conditions),
            FishingModifier.CODEC.listOf().optionalFieldOf("modifiers", List.of()).forGetter(CrateSelectorData::modifiers)
    ).apply(instance, CrateSelectorData::new));

    public boolean shouldApply(FishingContext context) {
        return conditions.stream().allMatch(condition -> condition.test(context));
    }

    public double apply(double weight, FishingContext context) {
        if (!shouldApply(context)) return weight;

        double result = weight;
        for (FishingModifier modifier : modifiers) {
            result = modifier.apply(result, context);
            if (result <= 0) return 0;
        }
        return result;
    }
}
