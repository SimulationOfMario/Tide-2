package com.li64.tide.data.fishing.selector;

import com.li64.tide.Tide;
import com.li64.tide.data.TideData;
import com.li64.tide.data.fishing.CatchResult;
import com.li64.tide.data.fishing.FishingContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Map;
import java.util.function.Predicate;

public class FishSelector implements FishingEntry {
    @Override
    public double weight(FishingContext context) {
        return FishingEntry.modifyWeight(
                Tide.SERVER_CONFIG.general.fishWeight,
                Tide.SERVER_CONFIG.general.fishQuality,
                context);
    }

    @Override
    public boolean shouldKeep(FishingContext context) {
        return TideData.FISH.get().values().stream()
                .anyMatch(fish -> fish.shouldKeep(context) && fish.weight(context) > 0);
    }

    @Override
    public CatchResult getResult(FishingContext context) {
        return this.getResult(context, null, entry -> false);
    }

    @Override
    public CatchResult getResult(FishingContext context, Map<FishingEntry, Double> results, Predicate<FishingEntry> entryTest) {
        return FishingRandomSelector.select(TideData.FISH.get().values(), context, results, entryTest);
    }

    @Override
    public MutableComponent getTestKey() {
        return Component.translatable("commands.fishing.entries.fish_selector");
    }
}
