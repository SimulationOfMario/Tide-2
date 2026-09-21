package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.Collection;
import java.util.List;

//? if 1.20.1 {
/*import net.minecraft.core.registries.BuiltInRegistries;
*///?}

public class EffectsCondition extends FishingCondition {
    public static final MapCodec<EffectsCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceKey.codec(Registries.MOB_EFFECT).listOf().optionalFieldOf("effects", List.of()).forGetter(EffectsCondition::getEffects),
            Codec.STRING.optionalFieldOf("match", "any").forGetter(EffectsCondition::getMatch)
    ).apply(instance, EffectsCondition::new));

    private final List<ResourceKey<MobEffect>> effects;
    private final String match;

    public EffectsCondition(List<ResourceKey<MobEffect>> effects, String match) {
        this.effects = effects;
        this.match = match;
    }

    public List<ResourceKey<MobEffect>> getEffects() {
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
    @SuppressWarnings("deprecation")
    public boolean test(FishingContext context) {
        if (context.hook() == null || context.hook().getPlayerOwner() == null) return false;

        Collection<MobEffectInstance> playerEffects = context.hook().getPlayerOwner().getActiveEffects();

        return switch (this.match) {

            // Note: When using 'all', if effects list is empty, the result is always true.
            case "all" ->
                //? if >=1.21.1 {
                this.effects.stream().allMatch(e ->
                        playerEffects.stream().anyMatch(e2 -> e2.getEffect().is(e)));
                //?} else {
                /*this.effects.stream().allMatch(e ->
                        playerEffects.stream().anyMatch(e2 ->
                                e.location().equals(BuiltInRegistries.MOB_EFFECT.getKey(e2.getEffect()))));
                *///?}

            // Default always applies whether you set it to 'any' or enter something incorrect.
            // Note: When using 'any', if effects list is empty, the result is always false.
            default ->
                //? if >=1.21.1 {
                this.effects.stream().anyMatch(e ->
                        playerEffects.stream().anyMatch(e2 -> e2.getEffect().is(e)));
                //?} else {
                /*this.effects.stream().anyMatch(e ->
                        playerEffects.stream().anyMatch(e2 ->
                                e.location().equals(BuiltInRegistries.MOB_EFFECT.getKey(e2.getEffect()))));
                *///?}
        };
    }
}
