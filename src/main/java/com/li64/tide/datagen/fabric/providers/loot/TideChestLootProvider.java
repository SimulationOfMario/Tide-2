//? if fabric {
package com.li64.tide.datagen.fabric.providers.loot;

import com.li64.tide.data.TideLootTables;
import com.li64.tide.data.loot.RandomizeFishLengthFunction;
import com.li64.tide.registries.TideFish;
import com.li64.tide.registries.TideItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class TideChestLootProvider extends TideAbstractLootProvider {
    public TideChestLootProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
    }

    @Override
    public void generateLoot(TideLootOutput output) {
        output.accept(
                TideLootTables.Chests.FISHING_BOAT,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(4, 6))
                        .add(LootItem.lootTableItem(TideItems.COOKED_FISH).setWeight(100))
                        .add(LootItem.lootTableItem(Items.COOKED_SALMON).setWeight(80))
                        .add(LootItem.lootTableItem(Items.COOKED_COD).setWeight(80))
                        .add(LootItem.lootTableItem(Items.STRING).setWeight(45))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(30).setQuality(1))
                        .add(LootItem.lootTableItem(Items.FISHING_ROD).setWeight(12).setQuality(1)
                                .apply(SetItemDamageFunction.setDamage(
                                        UniformGenerator.between(0.4f, 1.0f)))
                                .apply(EnchantRandomlyFunction.randomApplicableEnchantment(/*? if >=1.21 {*/this.registries/*?}*/)
                                        .when(LootItemRandomChanceCondition.randomChance(0.7f))))
                )
        );

        LootPool.Builder fishyNotePool = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(TideItems.FISHY_NOTE)
                        .when(LootItemRandomChanceCondition.randomChance(0.85f))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));

        output.accept(
                TideLootTables.Chests.Crates.SURFACE_FRESHWATER,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(7, 10))
                        .add(LootItem.lootTableItem(Items.SALMON).setWeight(85)
                                .apply(RandomizeFishLengthFunction::new))
                        .add(LootItem.lootTableItem(TideItems.COOKED_FISH).setWeight(80))
                        .add(LootItem.lootTableItem(TideFish.LARGEMOUTH_BASS).setWeight(25)
                                .apply(RandomizeFishLengthFunction::new))
                        .add(LootItem.lootTableItem(TideFish.SMALLMOUTH_BASS).setWeight(25)
                                .apply(RandomizeFishLengthFunction::new))
                        .add(LootItem.lootTableItem(Items.STICK).setWeight(80)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 8), true)
                                        .when(LootItemRandomChanceCondition.randomChance(0.5f))))
                        .add(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(90))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(40).setQuality(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 4), true)
                                        .when(LootItemRandomChanceCondition.randomChance(0.5f))))
                        .add(LootItem.lootTableItem(Items.WHEAT).setWeight(20))
                        .add(LootItem.lootTableItem(Items.WHEAT_SEEDS).setWeight(15))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(5).setQuality(2)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f))))
                ).withPool(fishyNotePool)
        );

        output.accept(
                TideLootTables.Chests.Crates.SURFACE_SALTWATER,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(7, 10))
                        .add(LootItem.lootTableItem(TideItems.COOKED_FISH).setWeight(80))
                        .add(LootItem.lootTableItem(TideFish.OCEAN_PERCH).setWeight(25)
                                .apply(RandomizeFishLengthFunction::new))
                        .add(LootItem.lootTableItem(TideFish.RED_SNAPPER).setWeight(25)
                                .apply(RandomizeFishLengthFunction::new))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(23))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(15))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(8).setQuality(1))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(4).setQuality(1))
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(3).setQuality(1))
                        .add(LootItem.lootTableItem(Items.FISHING_ROD).setWeight(4).setQuality(2)
                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.5f, 1.0f))))
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(15.0f))
                                        .when(LootItemRandomChanceCondition.randomChance(0.3f)))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(5).setQuality(2)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f))))
                ).withPool(fishyNotePool)
        );

        output.accept(
                TideLootTables.Chests.Crates.UNDERGROUND,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(7, 10))
                        .add(LootItem.lootTableItem(Items.STICK).setWeight(100)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2), true)))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(70)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3), true)))
                        .add(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(60)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5), true)))
                        .add(LootItem.lootTableItem(Items.ANDESITE).setWeight(60))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(25).setQuality(1))
                        .add(LootItem.lootTableItem(Items.LAPIS_LAZULI).setWeight(20)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2), true)))
                        .add(LootItem.lootTableItem(Items.STONE_PICKAXE).setWeight(9).setQuality(1)
                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 1.0f)))
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/UniformGenerator.between(8, 10))))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(6).setQuality(3)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f))))
                ).withPool(fishyNotePool)
        );

        output.accept(
                TideLootTables.Chests.Crates.DEEP,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(7, 10))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(60))
                        .add(LootItem.lootTableItem(Items.RAW_COPPER).setWeight(45))
                        .add(LootItem.lootTableItem(Items.RAW_IRON).setWeight(45))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(25).setQuality(1))
                        .add(LootItem.lootTableItem(Items.LAPIS_LAZULI).setWeight(20)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3), true)))
                        .add(LootItem.lootTableItem(Items.REDSTONE).setWeight(16))
                        .add(LootItem.lootTableItem(Items.AMETHYST_SHARD).setWeight(14))
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(12).setQuality(1))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(8).setQuality(2))
                        .add(LootItem.lootTableItem(Items.IRON_PICKAXE).setWeight(8).setQuality(2)
                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.45f, 0.95f))
                                        .when(LootItemRandomChanceCondition.randomChance(0.8f)))
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/UniformGenerator.between(20, 30))
                                        .when(LootItemRandomChanceCondition.randomChance(0.8f))))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(6).setQuality(2)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f))))
                ).withPool(fishyNotePool)
        );

        output.accept(
                TideLootTables.Chests.Crates.SURFACE_LAVA,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(6, 9))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(60))
                        .add(LootItem.lootTableItem(Items.FLINT).setWeight(60))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(60))
                        .add(LootItem.lootTableItem(Items.OBSIDIAN).setWeight(50))
                        .add(LootItem.lootTableItem(TideItems.OBSIDIAN_FRAGMENT).setWeight(50))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(5).setQuality(1)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f))))
                ).withPool(fishyNotePool)
        );

        output.accept(
                TideLootTables.Chests.Crates.UNDERGROUND_LAVA,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(6, 9))
                        .add(LootItem.lootTableItem(Items.COAL).setWeight(65))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(65))
                        .add(LootItem.lootTableItem(Items.OBSIDIAN).setWeight(55))
                        .add(LootItem.lootTableItem(Items.CRYING_OBSIDIAN).setWeight(50))
                        .add(LootItem.lootTableItem(TideItems.OBSIDIAN_FRAGMENT).setWeight(45))
                        .add(LootItem.lootTableItem(Items.MAGMA_BLOCK).setWeight(20))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(8).setQuality(1))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(6).setQuality(1))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(5).setQuality(2)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f))))
                ).withPool(fishyNotePool)
        );

        output.accept(
                TideLootTables.Chests.Crates.DEEP_LAVA,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(7, 9))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(65))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(65))
                        .add(LootItem.lootTableItem(Items.OBSIDIAN).setWeight(55))
                        .add(LootItem.lootTableItem(Items.CRYING_OBSIDIAN).setWeight(50))
                        .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(35))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(15).setQuality(1))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(13).setQuality(1))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(5).setQuality(2)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f))))
                ).withPool(fishyNotePool)
        );

        output.accept(
                TideLootTables.Chests.Crates.NETHER,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(7, 9))
                        .add(LootItem.lootTableItem(TideFish.MAGMA_MACKEREL).setWeight(55)
                                .apply(RandomizeFishLengthFunction::new))
                        .add(LootItem.lootTableItem(TideFish.ASH_PERCH).setWeight(55)
                                .apply(RandomizeFishLengthFunction::new))
                        .add(LootItem.lootTableItem(Items.NETHER_BRICK).setWeight(50))
                        .add(LootItem.lootTableItem(Items.NETHER_WART).setWeight(50))
                        .add(LootItem.lootTableItem(Items.BLAZE_POWDER).setWeight(35))
                        .add(LootItem.lootTableItem(Items.MAGMA_CREAM).setWeight(35))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(15))
                        .add(LootItem.lootTableItem(Items.GOLD_BLOCK).setWeight(3).setQuality(1))
                        .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP).setWeight(3).setQuality(1))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(4).setQuality(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                    .when(LootItemRandomChanceCondition.randomChance(0.5f))
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30))))
                ).withPool(fishyNotePool)
        );

        output.accept(
                TideLootTables.Chests.Crates.END,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(6, 8))
                        .add(LootItem.lootTableItem(Items.END_STONE).setWeight(70)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                        .add(LootItem.lootTableItem(Items.CHORUS_FRUIT).setWeight(70))
                        .add(LootItem.lootTableItem(Items.ENDER_PEARL).setWeight(65))
                        .add(LootItem.lootTableItem(Items.POPPED_CHORUS_FRUIT).setWeight(55))
                        .add(LootItem.lootTableItem(Items.SHULKER_SHELL).setWeight(35).setQuality(1))
                        .add(LootItem.lootTableItem(Items.ELYTRA).setWeight(2)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(15))
                                        .when(LootItemRandomChanceCondition.randomChance(0.5f)))
                        )
                ).withPool(fishyNotePool)
        );
    }
}
//?}