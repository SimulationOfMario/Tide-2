package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.Collection;
import java.util.List;

public class EffectsCondition extends FishingCondition {
    public static final MapCodec<EffectsCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            EffectRequirement.CODEC.listOf().optionalFieldOf("effects", List.of()).forGetter(EffectsCondition::getEffects),
            Codec.STRING.optionalFieldOf("match", "any").forGetter(EffectsCondition::getMatch)
    ).apply(instance, EffectsCondition::new));

    private final List<EffectRequirement> effects;
    private final String match;

    public EffectsCondition(List<EffectRequirement> effects, String match) {
        this.effects = effects;
        this.match = match;
    }

    public List<EffectRequirement> getEffects() {
        return effects;
    }

    public String getMatch() {
        return match;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.HAS_EFFECTS;
    }

    @Override
    public boolean test(FishingContext context) {
        if (context.hook() == null || context.hook().getPlayerOwner() == null) return false;

        Collection<MobEffectInstance> playerEffects = context.hook().getPlayerOwner().getActiveEffects();

        return switch (this.match) {

            // Note: When using 'all', if effects list is empty, the result is always true.
            case "all" ->
                this.effects.stream().allMatch(e -> anyMatches(e, playerEffects));

            // Default always applies whether you set it to 'any' or enter something incorrect.
            // Note: When using 'any', if effects list is empty, the result is always false.
            default ->
                this.effects.stream().anyMatch(e -> anyMatches(e, playerEffects));
        };
    }

    private boolean anyMatches(EffectRequirement requirement, Collection<MobEffectInstance> candidates)
    {
        return candidates.stream().anyMatch(effectInstance ->
                requirement.matches(effectInstance.getEffect(), effectInstance.getAmplifier() + 1));
    }
}
