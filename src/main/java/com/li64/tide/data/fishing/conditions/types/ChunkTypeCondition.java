package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.chunks.ChunkType;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public class ChunkTypeCondition extends FishingCondition {
    public static final MapCodec<ChunkTypeCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.listOf().fieldOf("chunk_types").forGetter(ChunkTypeCondition::getChunkTypesIds)
    ).apply(instance, ChunkTypeCondition::new));

    private final List<String> chunkTypesIds;

    public ChunkTypeCondition(List<String> chunkTypesIds) {
        this.chunkTypesIds = chunkTypesIds;
    }

    public List<String> getChunkTypesIds() {
        return chunkTypesIds;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.CHUNK_TYPE;
    }

    @Override
    public boolean test(FishingContext context) {
        return ChunkType.get(chunkTypesIds).stream().allMatch(ct -> ct.matches(context));
    }
}