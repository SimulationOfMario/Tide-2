package com.li64.tide.data.fishing.conditions.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record LevelRange(int min, int max) {
    public static final Codec<LevelRange> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.optionalFieldOf("min", 1).forGetter(LevelRange::min),
            Codec.INT.optionalFieldOf("max", Integer.MAX_VALUE).forGetter(LevelRange::max)
    ).apply(instance, LevelRange::new));

    public LevelRange() {
        this(1, Integer.MAX_VALUE);
    }

    public boolean contains(int level) {
        return level >= min && level <= max;
    }
}