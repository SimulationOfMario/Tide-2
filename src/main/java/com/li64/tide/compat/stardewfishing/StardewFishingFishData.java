//? if fabric {
package com.li64.tide.compat.stardewfishing;

import com.li64.tide.Tide;
import com.li64.tide.compat.CompatHelper;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.journal.FishRarity;
import com.li64.tide.data.journal.JournalGroup;
import com.li64.tide.datagen.fabric.providers.SimpleDataOutput;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class StardewFishingFishData {
    public static final String MOD_ID = CompatHelper.Namespace.STARDEW_FISHING;

    private static final TagKey<Biome> HAS_RIVER_FISH = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "has_river_fish"));
    private static final TagKey<Biome> HAS_NORMAL_OCEAN_FISH = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "has_normal_ocean_fish"));
    private static final TagKey<Biome> HAS_WARM_OCEAN_FISH = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "has_warm_ocean_fish"));
    private static final TagKey<Biome> HAS_ARID_FISH = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "has_arid_fish"));
    private static final TagKey<Biome> HAS_JUNGLE_FISH = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "has_jungle_fish"));

    public static void generate(SimpleDataOutput<FishData> output) {
        FishData.builder().fish(MOD_ID, "crystalline_snakehead")
                .size(80, 140, 200)
                .selectionWeight(2).selectionQuality(0.5)
                .overworld().water().surface().inBiome(HAS_RIVER_FISH)
                .journalLocation("journal.info.location.freshwater")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.LEGENDARY)
                .build(output);

        FishData.builder().fish(MOD_ID, "demon_gar")
                .size(120, 180, 280)
                .selectionWeight(2).selectionQuality(0.5)
                .overworld().water().surface().inBiome(HAS_RIVER_FISH)
                .journalLocation("journal.info.location.freshwater")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.LEGENDARY)
                .build(output);

        FishData.builder().fish(MOD_ID, "goliath_grouper")
                .size(150, 200, 300)
                .selectionWeight(2).selectionQuality(0.5)
                .overworld().water().surface().inBiome(HAS_NORMAL_OCEAN_FISH)
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.LEGENDARY)
                .build(output);

        FishData.builder().fish(MOD_ID, "storm_tarpon")
                .size(130, 180, 300)
                .selectionWeight(2).selectionQuality(0.5)
                .overworld().water().surface().inBiome(HAS_NORMAL_OCEAN_FISH)
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.LEGENDARY)
                .build(output);

        FishData.builder().fish(MOD_ID, "cyclops_mahimahi")
                .size(95, 140, 210)
                .selectionWeight(2).selectionQuality(0.5)
                .overworld().water().surface().inBiome(HAS_WARM_OCEAN_FISH)
                .journalLocation("journal.info.location.saltwater_warm")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.LEGENDARY)
                .build(output);

        FishData.builder().fish(MOD_ID, "blazing_oarfish")
                .size(250, 500, 850)
                .selectionWeight(2).selectionQuality(0.5)
                .overworld().water().surface().inBiome(HAS_WARM_OCEAN_FISH)
                .journalLocation("journal.info.location.saltwater_warm")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.LEGENDARY)
                .build(output);

        FishData.builder().fish(MOD_ID, "sabretoothed_tigerfish")
                .size(55, 85, 140)
                .selectionWeight(2).selectionQuality(0.5)
                .overworld().water().surface().inBiome(HAS_ARID_FISH)
                .journalLocation("journal.info.location.desert")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.LEGENDARY)
                .build(output);

        FishData.builder().fish(MOD_ID, "golden_snook")
                .size(58, 88, 145)
                .selectionWeight(2).selectionQuality(0.5)
                .overworld().water().surface().inBiome(HAS_ARID_FISH)
                .journalLocation("journal.info.location.desert")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.LEGENDARY)
                .build(output);

        FishData.builder().fish(MOD_ID, "chromatic_arapaima")
                .size(240, 340, 460)
                .selectionWeight(2).selectionQuality(0.5)
                .overworld().water().surface().inBiome(HAS_JUNGLE_FISH)
                .journalLocation("journal.info.location.jungle")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.LEGENDARY)
                .build(output);

        FishData.builder().fish(MOD_ID, "vampire_payara")
                .size(80, 140, 200)
                .selectionWeight(2).selectionQuality(0.5)
                .overworld().water().surface().inBiome(HAS_JUNGLE_FISH)
                .journalLocation("journal.info.location.jungle")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.LEGENDARY)
                .build(output);
    }
}
//?}