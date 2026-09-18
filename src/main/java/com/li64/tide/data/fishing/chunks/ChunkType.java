package com.li64.tide.data.fishing.chunks;

import com.li64.tide.data.fishing.FishingContext;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public interface ChunkType {
    List<ChunkType> TYPES = new ArrayList<>();

    SlimeChunk SLIME_CHUNK = register(new SlimeChunk());
    DevilsHoleChunk DEVILS_HOLE_CHUNK = register(new DevilsHoleChunk());

    static <T extends ChunkType> T register(T chunkType) {
        TYPES.add(chunkType);
        return chunkType;
    }

    static ChunkType get(String id) {
        return TYPES.stream()
            .filter(chunkType -> chunkType.id().getPath().equals(id))
            .findFirst().orElse(null);
    }

    ResourceLocation id();

    boolean matches(FishingContext context);
}
