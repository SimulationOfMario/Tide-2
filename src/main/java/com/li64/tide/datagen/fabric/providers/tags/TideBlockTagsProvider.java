//? if fabric {
package com.li64.tide.datagen.fabric.providers.tags;

import com.li64.tide.Tide;
import com.li64.tide.compat.CompatHelper;
import com.li64.tide.data.TideTags;
import com.li64.tide.registries.TideBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class TideBlockTagsProvider extends FabricTagProvider<Block> {
    public TideBlockTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.BLOCK, registries);
    }

    @Override
    public @NotNull String getName() {
        return "Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(TideTags.Blocks.INFORMATIONAL)
                .add(TideBlocks.LUNAR_CALENDAR)
                .add(TideBlocks.WEATHER_RADIO);

        getOrCreateTagBuilder(TideTags.Blocks.DESERT_WELL_LOOT)
                .add(Blocks.SUSPICIOUS_SAND);

        getOrCreateTagBuilder(TideTags.Blocks.CHASM_EEL_CAN_EAT)
                .add(Blocks.BEDROCK);

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(TideBlocks.WOODEN_CRATE)
                .add(TideBlocks.ANGLING_TABLE)
                .add(TideBlocks.FISH_DISPLAY);

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(TideBlocks.PURPUR_CRATE)
                .add(TideBlocks.OBSIDIAN_CRATE)
                .add(TideBlocks.WEATHER_RADIO);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(TideBlocks.OBSIDIAN_CRATE);

        getOrCreateTagBuilder(TagKey.create(Registries.BLOCK,
                Tide.resource(CompatHelper.Namespace.VISUAL_WORKBENCH, "unaltered_workbenches")))
                .add(TideBlocks.ANGLING_TABLE);
    }
}
//?}