package com.li64.tide.data.fishing.conditions.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;

//? if >= 1.21 {
import net.minecraft.core.Holder;
//?} else {
/*import net.minecraft.core.registries.BuiltInRegistries;
*///?}

public record EffectRequirement(ResourceKey<MobEffect> id, LevelRange levelRange) {
    public static final Codec<EffectRequirement> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        ResourceKey.codec(Registries.MOB_EFFECT).fieldOf("id").forGetter(EffectRequirement::id),
        LevelRange.CODEC.optionalFieldOf("levels", new LevelRange()).forGetter(EffectRequirement::levelRange)
    ).apply(instance, EffectRequirement::new));

    //? if >=1.21 {
    public boolean matches(Holder<MobEffect> effect, int level) {
        return effect.is(id) && levelRange.contains(level);
    }
    //?} else {
    /*@SuppressWarnings("deprecation")
    public boolean matches(MobEffect effect, int level) {
        return id.location().equals(BuiltInRegistries.MOB_EFFECT.getKey(effect)) && levelRange.contains(level);
    }
    *///?}
}
