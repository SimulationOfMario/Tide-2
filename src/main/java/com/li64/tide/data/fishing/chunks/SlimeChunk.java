package com.li64.tide.data.fishing.chunks;

import com.li64.tide.Tide;
import com.li64.tide.data.fishing.FishingContext;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.WorldgenRandom;

public class SlimeChunk implements ChunkType {
    private final ResourceLocation id = Tide.resource("slime");

    @Override
    public ResourceLocation id() {
        return id;
    }

    @Override
    public boolean matches(FishingContext context) {
        BlockPos blockPos = context.blockPos();

        return WorldgenRandom.seedSlimeChunk(
                blockPos.getX(),
                blockPos.getZ(),
                context.level().getSeed(),
                987234911L
        ).nextInt(10) == 0;
    }
}
