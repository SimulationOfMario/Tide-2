package com.li64.tide.data.fishing.chunks;

import com.li64.tide.Tide;
import com.li64.tide.compat.CompatHelper;
import com.li64.tide.data.fishing.FishingContext;
import net.minecraft.resources.ResourceLocation;

public class DevilsHoleChunk implements ChunkType {
    private final ResourceLocation id = Tide.resource("devils_hole");

    @Override
    public ResourceLocation id() {
        return id;
    }

    @Override
    public boolean matches(FishingContext context) {
        return CompatHelper.alexsMobsIsInPupfishChunk(context);
    }
}
