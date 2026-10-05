package com.li64.tide.client.gui.screens.journal.components;

import com.li64.tide.Tide;
import com.li64.tide.client.gui.screens.journal.ProfileComponent;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.types.DimensionsCondition;
import com.li64.tide.data.fishing.conditions.types.FishingMediumCondition;
import com.li64.tide.data.fishing.mediums.FishingMedium;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DimensionsComponent extends ProfileComponent {
    private static final ResourceLocation DIMENSIONS = Tide.resource("textures/gui/journal/dimensions.png");
    private static final String CUSTOM_DIMENSION_TEXTURE_PATH = "textures/gui/journal/dimensions/";
    private static final Component TITLE = Component.translatable("journal.info.dimensions.title");
    
    public List<ResourceKey<Level>> dimensions;

    public static boolean shouldCreate(DimensionsCondition condition, List<FishingCondition> others) {
        boolean isWater = others.stream().filter(cond -> cond instanceof FishingMediumCondition)
                .findFirst().map(cond -> ((FishingMediumCondition)cond).getMediumId()
                        .equals(FishingMedium.WATER.id().getPath())).orElse(false);
        if (isWater && condition.isOverworldOnly()) return false;
        return condition.getDimensions().stream().anyMatch(DimensionsComponent::isKnown);
    }

    public DimensionsComponent(List<ResourceKey<Level>> dimensions) {
        this.dimensions = dimensions.stream().filter(DimensionsComponent::isKnown).toList();
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, Font font, int x, int y, int mouseX, int mouseY, float partialTick) {
        int center = x + AREA_WIDTH / 2;
        graphics.drawString(font, TITLE, center - font.width(TITLE) / 2, y, TEXT_COLOR, false);

        int count = dimensions.size();
        int spriteY = y + 12;
        int padding = 4;

        for (int i = 0; i < count; i++) {
            int cellSize = 10 + padding;
            int spriteX = center - ((count - 1) * cellSize / 2) + (i * cellSize) - 4;

            ResourceKey<Level> dimension = dimensions.get(i);

            if (isVanillaDimension(dimension))
                graphics.blit(DIMENSIONS, spriteX, spriteY, getOffset(dimension), 0, 10, 10, 30, 10);
            else graphics.blit(getCustomDimensionTexture(dimension), spriteX, spriteY, 0, 0, 10, 10, 10, 10);

            if (mouseX >= spriteX && mouseX <= spriteX + 10 && mouseY >= spriteY && mouseY <= spriteY + 10)
                graphics.renderTooltip(
                        font,
                        Component.translatableWithFallback("journal.info.dimensions." + dimension.location().getPath(), dimension.location().toString()),
                        mouseX, mouseY
                );
        }
    }

    public static boolean isKnown(ResourceKey<Level> dimension) {
        if (isVanillaDimension(dimension)) return true;

        return Minecraft.getInstance().getResourceManager().getResource(getCustomDimensionTexture(dimension)).isPresent();
    }

    private static boolean isVanillaDimension(ResourceKey<Level> dimension) {
        return dimension == Level.OVERWORLD || dimension == Level.NETHER || dimension == Level.END;
    }

    public static int getOffset(ResourceKey<Level> dimension) {
        if (dimension == Level.OVERWORLD) return 0;
        if (dimension == Level.NETHER) return 10;
        if (dimension == Level.END) return 20;
        return -1;
    }

    private static ResourceLocation getCustomDimensionTexture(ResourceKey<Level> dimension) {
        ResourceLocation location = dimension.location();

        //? if >=1.21 {
        return ResourceLocation.fromNamespaceAndPath(location.getNamespace(), CUSTOM_DIMENSION_TEXTURE_PATH + location.getPath() + ".png");
        //?} else {
        /*return new ResourceLocation(location.getNamespace(), CUSTOM_DIMENSION_TEXTURE_PATH + location.getPath() + ".png");
        *///?}
    }

    @Override
    public int getRequiredHeight() {
        return 26;
    }
}
