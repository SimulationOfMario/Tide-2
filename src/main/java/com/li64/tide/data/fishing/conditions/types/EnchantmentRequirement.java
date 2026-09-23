package com.li64.tide.data.fishing.conditions.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

//? if >= 1.21 {
import net.minecraft.core.Holder;
//?} else {
/*import net.minecraft.core.registries.BuiltInRegistries;
*///?}

public record EnchantmentRequirement(ResourceKey<Enchantment> id, LevelRange levelRange) {
    public static final Codec<EnchantmentRequirement> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        ResourceKey.codec(Registries.ENCHANTMENT).fieldOf("id").forGetter(EnchantmentRequirement::id),
        LevelRange.CODEC.optionalFieldOf("levels", new LevelRange()).forGetter(EnchantmentRequirement::levelRange)
    ).apply(instance, EnchantmentRequirement::new));

    //? if >=1.21 {
    public boolean matches(Holder<Enchantment> enchantment, int level) {
        return enchantment.is(id) && levelRange.contains(level);
    }
    //?} else {
    /*@SuppressWarnings("deprecation")
    public boolean matches(Enchantment enchantment, int level) {
        return id.location().equals(BuiltInRegistries.ENCHANTMENT.getKey(enchantment)) && levelRange.contains(level);
    }
    *///?}
}
