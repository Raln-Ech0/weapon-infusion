package net.ps.weaponinfusion.potion;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionIds;
import net.minecraft.world.item.alchemy.Potions;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModPotions {
    private ModPotions() {
        /* This utility class should not be instantiated */
    }

    private static final Map<Holder<Potion>, Holder<Potion>> POTION_MAPPING = new HashMap<>();

    public static Holder<Potion> nightVision;
    public static Holder<Potion> longNightVision;

    public static Holder<Potion> invisibility;
    public static Holder<Potion> longInvisibility;

    public static Holder<Potion> leaping;
    public static Holder<Potion> longLeaping;
    public static Holder<Potion> strongLeaping;

    public static Holder<Potion> fireResistance;
    public static Holder<Potion> longFireResistance;

    public static Holder<Potion> swiftness;
    public static Holder<Potion> longSwiftness;
    public static Holder<Potion> strongSwiftness;

    public static Holder<Potion> slowness;
    public static Holder<Potion> longSlowness;
    public static Holder<Potion> strongSlowness;

    public static Holder<Potion> turtleMaster;
    public static Holder<Potion> longTurtleMaster;
    public static Holder<Potion> strongTurtleMaster;

    public static Holder<Potion> waterBreathing;
    public static Holder<Potion> longWaterBreathing;

    public static Holder<Potion> healing;
    public static Holder<Potion> strongHealing;

    public static Holder<Potion> harming;
    public static Holder<Potion> strongHarming;

    public static Holder<Potion> poison;
    public static Holder<Potion> longPoison;
    public static Holder<Potion> strongPoison;

    public static Holder<Potion> regeneration;
    public static Holder<Potion> longRegeneration;
    public static Holder<Potion> strongRegeneration;

    public static Holder<Potion> strength;
    public static Holder<Potion> longStrength;
    public static Holder<Potion> strongStrength;

    public static Holder<Potion> weakness;
    public static Holder<Potion> longWeakness;

    public static Holder<Potion> luck;

    public static Holder<Potion> slowFalling;
    public static Holder<Potion> longSlowFalling;

    public static Holder<Potion> windCharged;
    public static Holder<Potion> weaving;
    public static Holder<Potion> oozing;
    public static Holder<Potion> infested;

    public static void initialize(BalmRegistrar.@NonNull Scoped<Potion> potions) {
        nightVision = register(potions, Potions.NIGHT_VISION, PotionIds.NIGHT_VISION);
        longNightVision = register(potions, Potions.LONG_NIGHT_VISION, PotionIds.LONG_NIGHT_VISION);

        invisibility = register(potions, Potions.INVISIBILITY, PotionIds.INVISIBILITY);
        longInvisibility = register(potions, Potions.LONG_INVISIBILITY, PotionIds.LONG_INVISIBILITY);

        leaping = register(potions, Potions.LEAPING, PotionIds.LEAPING);
        longLeaping = register(potions, Potions.LONG_LEAPING, PotionIds.LONG_LEAPING);
        strongLeaping = register(potions, Potions.STRONG_LEAPING, PotionIds.STRONG_LEAPING);

        fireResistance = register(potions, Potions.FIRE_RESISTANCE, PotionIds.FIRE_RESISTANCE);
        longFireResistance = register(potions, Potions.LONG_FIRE_RESISTANCE, PotionIds.LONG_FIRE_RESISTANCE);

        swiftness = register(potions, Potions.SWIFTNESS, PotionIds.SWIFTNESS);
        longSwiftness = register(potions, Potions.LONG_SWIFTNESS, PotionIds.LONG_SWIFTNESS);
        strongSwiftness = register(potions, Potions.STRONG_SWIFTNESS, PotionIds.STRONG_SWIFTNESS);

        slowness = register(potions, Potions.SLOWNESS, PotionIds.SLOWNESS);
        longSlowness = register(potions, Potions.LONG_SLOWNESS, PotionIds.LONG_SLOWNESS);
        strongSlowness = register(potions, Potions.STRONG_SLOWNESS, PotionIds.STRONG_SLOWNESS);

        turtleMaster = register(potions, Potions.TURTLE_MASTER, PotionIds.TURTLE_MASTER);
        longTurtleMaster = register(potions, Potions.LONG_TURTLE_MASTER, PotionIds.LONG_TURTLE_MASTER);
        strongTurtleMaster = register(potions, Potions.STRONG_TURTLE_MASTER, PotionIds.STRONG_TURTLE_MASTER);

        waterBreathing = register(potions, Potions.WATER_BREATHING, PotionIds.WATER_BREATHING);
        longWaterBreathing = register(potions, Potions.LONG_WATER_BREATHING, PotionIds.LONG_WATER_BREATHING);
        
        healing = register(potions, Potions.HEALING, PotionIds.HEALING);
        strongHealing = register(potions, Potions.STRONG_HEALING, PotionIds.STRONG_HEALING);

        harming = register(potions, Potions.HARMING, PotionIds.HARMING);
        strongHarming = register(potions, Potions.STRONG_HARMING, PotionIds.STRONG_HARMING);

        poison = register(potions, Potions.POISON, PotionIds.POISON);
        longPoison = register(potions, Potions.LONG_POISON, PotionIds.LONG_POISON);
        strongPoison = register(potions, Potions.STRONG_POISON, PotionIds.STRONG_POISON);

        regeneration = register(potions, Potions.REGENERATION, PotionIds.REGENERATION);
        longRegeneration = register(potions, Potions.LONG_REGENERATION, PotionIds.LONG_REGENERATION);
        strongRegeneration = register(potions, Potions.STRONG_REGENERATION, PotionIds.STRONG_REGENERATION);

        strength = register(potions, Potions.STRENGTH, PotionIds.STRENGTH);
        longStrength = register(potions, Potions.LONG_STRENGTH, PotionIds.LONG_STRENGTH);
        strongStrength = register(potions, Potions.STRONG_STRENGTH, PotionIds.STRONG_STRENGTH);

        weakness = register(potions, Potions.WEAKNESS, PotionIds.WEAKNESS);
        longWeakness = register(potions, Potions.LONG_WEAKNESS, PotionIds.LONG_WEAKNESS);

        luck = register(potions, Potions.LUCK, PotionIds.LUCK);

        slowFalling = register(potions, Potions.SLOW_FALLING, PotionIds.SLOW_FALLING);
        longSlowFalling = register(potions, Potions.LONG_SLOW_FALLING, PotionIds.LONG_SLOW_FALLING);

        windCharged = register(potions, Potions.WIND_CHARGED, PotionIds.WIND_CHARGED);
        weaving = register(potions, Potions.WEAVING, PotionIds.WEAVING);
        oozing = register(potions, Potions.OOZING, PotionIds.OOZING);
        infested = register(potions, Potions.INFESTED, PotionIds.INFESTED);

        POTION_MAPPING.put(Potions.NIGHT_VISION, ModPotions.nightVision);
        POTION_MAPPING.put(Potions.LONG_NIGHT_VISION, ModPotions.longNightVision);

        POTION_MAPPING.put(Potions.INVISIBILITY, ModPotions.invisibility);
        POTION_MAPPING.put(Potions.LONG_INVISIBILITY, ModPotions.longInvisibility);

        POTION_MAPPING.put(Potions.LEAPING, ModPotions.leaping);
        POTION_MAPPING.put(Potions.LONG_LEAPING, ModPotions.longLeaping);
        POTION_MAPPING.put(Potions.STRONG_LEAPING, ModPotions.strongLeaping);

        POTION_MAPPING.put(Potions.FIRE_RESISTANCE, ModPotions.fireResistance);
        POTION_MAPPING.put(Potions.LONG_FIRE_RESISTANCE, ModPotions.longFireResistance);

        POTION_MAPPING.put(Potions.SWIFTNESS, ModPotions.swiftness);
        POTION_MAPPING.put(Potions.LONG_SWIFTNESS, ModPotions.longSwiftness);
        POTION_MAPPING.put(Potions.STRONG_SWIFTNESS, ModPotions.strongSwiftness);

        POTION_MAPPING.put(Potions.SLOWNESS, ModPotions.slowness);
        POTION_MAPPING.put(Potions.LONG_SLOWNESS, ModPotions.longSlowness);
        POTION_MAPPING.put(Potions.STRONG_SLOWNESS, ModPotions.strongSlowness);

        POTION_MAPPING.put(Potions.TURTLE_MASTER, ModPotions.turtleMaster);
        POTION_MAPPING.put(Potions.LONG_TURTLE_MASTER, ModPotions.longTurtleMaster);
        POTION_MAPPING.put(Potions.STRONG_TURTLE_MASTER, ModPotions.strongTurtleMaster);

        POTION_MAPPING.put(Potions.WATER_BREATHING, ModPotions.waterBreathing);
        POTION_MAPPING.put(Potions.LONG_WATER_BREATHING, ModPotions.longWaterBreathing);

        POTION_MAPPING.put(Potions.HEALING, ModPotions.healing);
        POTION_MAPPING.put(Potions.STRONG_HEALING, ModPotions.strongHealing);

        POTION_MAPPING.put(Potions.HARMING, ModPotions.harming);
        POTION_MAPPING.put(Potions.STRONG_HARMING, ModPotions.strongHarming);

        POTION_MAPPING.put(Potions.POISON, ModPotions.poison);
        POTION_MAPPING.put(Potions.LONG_POISON, ModPotions.longPoison);
        POTION_MAPPING.put(Potions.STRONG_POISON, ModPotions.strongPoison);

        POTION_MAPPING.put(Potions.REGENERATION, ModPotions.regeneration);
        POTION_MAPPING.put(Potions.LONG_REGENERATION, ModPotions.longRegeneration);
        POTION_MAPPING.put(Potions.STRONG_REGENERATION, ModPotions.strongRegeneration);

        POTION_MAPPING.put(Potions.STRENGTH, ModPotions.strength);
        POTION_MAPPING.put(Potions.STRONG_STRENGTH, ModPotions.strongStrength);
        POTION_MAPPING.put(Potions.LONG_STRENGTH, ModPotions.longStrength);

        POTION_MAPPING.put(Potions.WEAKNESS, ModPotions.weakness);
        POTION_MAPPING.put(Potions.LONG_WEAKNESS, ModPotions.longWeakness);

        POTION_MAPPING.put(Potions.LUCK, ModPotions.luck);

        POTION_MAPPING.put(Potions.SLOW_FALLING, ModPotions.slowFalling);
        POTION_MAPPING.put(Potions.LONG_SLOW_FALLING, ModPotions.longSlowFalling);

        POTION_MAPPING.put(Potions.WIND_CHARGED, ModPotions.windCharged);
        POTION_MAPPING.put(Potions.WEAVING, ModPotions.weaving);
        POTION_MAPPING.put(Potions.OOZING, ModPotions.oozing);
        POTION_MAPPING.put(Potions.INFESTED, ModPotions.infested);
    }

    private static Holder<Potion> register(BalmRegistrar.Scoped<Potion> potions, Holder<Potion> copy, ResourceKey<Potion> id) {
        List<MobEffectInstance> effects = copy.value().getEffects().stream()
                .map(mobEffectInstance ->
                        new MobEffectInstance(
                                mobEffectInstance.getEffect(),
                                mobEffectInstance.getDuration() / 2,
                                mobEffectInstance.getAmplifier()
                        )
                )
                .toList();

        return potions.register(id.identifier().getPath(),
                _ -> new Potion(
                        copy.value().name(),
                        effects.toArray(MobEffectInstance[]::new)
                )
        );
    }

    public static Map<Holder<Potion>, Holder<Potion>> getPotionMapping() {
        return POTION_MAPPING;
    }
}
