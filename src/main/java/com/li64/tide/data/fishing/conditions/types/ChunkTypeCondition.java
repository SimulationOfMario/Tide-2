package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.chunks.ChunkType;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class ChunkTypeCondition extends FishingCondition {
    public static final MapCodec<ChunkTypeCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("chunk_type").forGetter(ChunkTypeCondition::getChunkTypeId)
    ).apply(instance, ChunkTypeCondition::new));

    private final String chunkTypeId;

    public ChunkTypeCondition(String chunkTypeId) {
        this.chunkTypeId = chunkTypeId;
    }

    public String getChunkTypeId() {
        return chunkTypeId;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.CHUNK_TYPE;
    }

    @Override
    public boolean test(FishingContext context) {
        ChunkType chunkType = ChunkType.get(chunkTypeId);
        return chunkType != null && chunkType.matches(context);
    }
}