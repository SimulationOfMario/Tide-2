package com.li64.tide.data.fishing.chunks;

import com.li64.tide.Tide;
import com.li64.tide.data.fishing.FishingContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.WorldgenRandom;

public class SlimeChunk implements ChunkType {
    private final ResourceLocation id = Tide.resource("slime");

    @Override
    public ResourceLocation id() {
        return id;
    }

    @Override
    public boolean matches(FishingContext context) {
        ChunkPos chunkPos = new ChunkPos(context.blockPos());

        return WorldgenRandom.seedSlimeChunk(
                chunkPos.x,
                chunkPos.z,
                context.level().getSeed(),
                987234911L
        ).nextInt(10) == 0;
    }
}
