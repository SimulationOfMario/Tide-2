package com.li64.tide.compat.alexsmobs;

import com.github.alexthe666.alexsmobs.world.AMWorldData;
import com.li64.tide.data.fishing.FishingContext;

public class AlexsMobsCompat {

    public static boolean isInPupfishChunk(FishingContext context) {
        return AMWorldData.get(context.level()).isInPupfishChunk(context.blockPos());
    }

}
