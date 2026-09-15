package com.li64.tide.compat.alexsmobs;

import com.li64.tide.data.fishing.FishingContext;

//? if =1.20.1 {
/*import com.github.alexthe666.alexsmobs.world.AMWorldData;
*///?}

public class AlexsMobsCompat {

    public static boolean isInPupfishChunk(FishingContext context) {
        //? if =1.20.1 {
        /*return AMWorldData.get(context.level()).isInPupfishChunk(context.blockPos());
        *///?} else {
        return false;
        //?}
    }

}
