//? if fabric {
package com.li64.tide.datagen.fabric.providers.tags;

import com.li64.tide.Tide;
import com.li64.tide.compat.CompatHelper;
import com.li64.tide.data.TideTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class TideBiomeTagsProvider extends FabricTagProvider<Biome> {
    public TideBiomeTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.BIOME, registries);
    }

    @Override
    public @NotNull String getName() {
        return "Biome Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(TideTags.Biomes.WATER_BIOMES)
                .forceAddTag(BiomeTags.IS_RIVER)
                .forceAddTag(BiomeTags.IS_OCEAN);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_FISHING_BOAT)
                .forceAddTag(BiomeTags.IS_OCEAN);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_END_OASIS)
                .add(Biomes.END_MIDLANDS)
                .add(Biomes.END_HIGHLANDS);

        getOrCreateTagBuilder(TideTags.Biomes.CAN_CATCH_STARFISH)
                .forceAddTag(BiomeTags.IS_DEEP_OCEAN);

        getOrCreateTagBuilder(TideTags.Biomes.IS_SALTWATER)
                .forceAddTag(TideTags.Convention.IS_OCEAN)
                .forceAddTag(TideTags.Convention.IS_BEACH)
                .add(Biomes.STONY_SHORE)
                .add(Biomes.MUSHROOM_FIELDS);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_CHERRY_GROVE_FISH)
                .add(Biomes.CHERRY_GROVE)
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "snowblossom_grove"));

        getOrCreateTagBuilder(TideTags.Biomes.HAS_DEEP_DARK_FISH)
                .add(Biomes.DEEP_DARK);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_PLAINS_FISH)
                .forceAddTag(TideTags.Convention.IS_PLAINS)
                .add(Biomes.PLAINS)
                .add(Biomes.SUNFLOWER_PLAINS);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_DESERT_FISH)
                .forceAddTag(TideTags.Convention.IS_DESERT)
                .forceAddTag(TideTags.Convention.IS_BADLANDS)
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "dune_beach"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "lush_desert"));

        getOrCreateTagBuilder(TideTags.Biomes.HAS_COASTAL_FISH)
                .forceAddTag(BiomeTags.IS_BEACH)
                .add(Biomes.STONY_SHORE);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_DRIPSTONE_FISH)
                .add(Biomes.DRIPSTONE_CAVES);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_FROZEN_FISH)
                .forceAddTag(TideTags.Convention.IS_ICY)
                .forceAddTag(TideTags.Convention.IS_SNOWY)
                .add(Biomes.FROZEN_RIVER)
                .add(Biomes.FROZEN_OCEAN)
                .add(Biomes.SNOWY_BEACH)
                .add(Biomes.SNOWY_PLAINS)
                .add(Biomes.ICE_SPIKES)
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "auroral_garden"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "cold_desert"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "tundra"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "wintry_origin_valley"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "snowy_coniferous_forest"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "snowy_fir_clearing"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "snowy_maple_woods"));

        getOrCreateTagBuilder(TideTags.Biomes.HAS_JUNGLE_FISH)
                .forceAddTag(TideTags.Convention.IS_JUNGLE)
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "floodplain"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "rainforest"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "rocky_rainforest"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "tropics"));

        getOrCreateTagBuilder(TideTags.Biomes.HAS_MOUNTAIN_FISH)
                .forceAddTag(TideTags.Convention.IS_MOUNTAIN)
                .forceAddTag(BiomeTags.IS_MOUNTAIN);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_MUSHROOM_FISH)
                .forceAddTag(TideTags.Convention.IS_MUSHROOM)
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "fungal_jungle"));

        getOrCreateTagBuilder(TideTags.Biomes.HAS_SWAMP_FISH)
                .forceAddTag(TideTags.Convention.IS_SWAMP)
                .addOptional(Tide.resource(CompatHelper.Namespace.HYBRID_AQUATIC, "swamp"))
                .addOptional(Tide.resource(CompatHelper.Namespace.HYBRID_AQUATIC, "mangroves"))
                .addOptional(Tide.resource(CompatHelper.Namespace.HYBRID_AQUATIC, "marshes"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "bayou"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "bog"))
                .addOptional(Tide.resource(CompatHelper.Namespace.BIOMES_O_PLENTY, "marsh"));

        getOrCreateTagBuilder(TideTags.Biomes.THE_VOID)
                .add(Biomes.THE_VOID);
    }
}
//?}