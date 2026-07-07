package net.ps.weaponinfusion.potion;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import org.jspecify.annotations.NonNull;

public class ModPotions {
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
        nightVision = potions.register("night_vision",
                _ -> new Potion("night_vision", new MobEffectInstance(MobEffects.NIGHT_VISION, 3600 / 2)));
        longNightVision = potions.register("long_night_vision",
                _ -> new Potion("night_vision", new MobEffectInstance(MobEffects.NIGHT_VISION, 9600 / 2)));

        invisibility = potions.register("invisibility",
                _ -> new Potion("invisibility", new MobEffectInstance(MobEffects.INVISIBILITY, 3600 / 2)));
        longInvisibility = potions.register("long_invisibility",
                _ -> new Potion("invisibility", new MobEffectInstance(MobEffects.INVISIBILITY, 9600 / 2)));

        leaping = potions.register("leaping",
                _ -> new Potion("leaping", new MobEffectInstance(MobEffects.JUMP_BOOST, 3600 / 2)));
        longLeaping = potions.register("long_leaping",
                _ -> new Potion("leaping", new MobEffectInstance(MobEffects.JUMP_BOOST, 9600 / 2)));
        strongLeaping = potions.register("strong_leaping",
                _ -> new Potion("leaping", new MobEffectInstance(MobEffects.JUMP_BOOST, 1800 / 2, 1)));

        fireResistance = potions.register("fire_resistance",
                _ -> new Potion("fire_resistance", new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600 / 2)));
        longFireResistance = potions.register("long_fire_resistance",
                _ -> new Potion("fire_resistance", new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 9600 / 2)));

        swiftness = potions.register("swiftness",
                _ -> new Potion("swiftness", new MobEffectInstance(MobEffects.SPEED, 3600 / 2)));
        longSwiftness = potions.register("long_swiftness",
                _ -> new Potion("swiftness", new MobEffectInstance(MobEffects.SPEED, 9600 / 2)));
        strongSwiftness = potions.register("strong_swiftness",
                _ -> new Potion("swiftness", new MobEffectInstance(MobEffects.SPEED, 1800 / 2, 1)));

        slowness = potions.register("slowness",
                _ -> new Potion("slowness", new MobEffectInstance(MobEffects.SLOWNESS, 1800 / 2)));
        longSlowness = potions.register("long_slowness",
                _ -> new Potion("slowness", new MobEffectInstance(MobEffects.SLOWNESS, 4800 / 2)));
        strongSlowness = potions.register("strong_slowness",
                _ -> new Potion("slowness", new MobEffectInstance(MobEffects.SLOWNESS, 400 / 2, 3)));

        turtleMaster = potions.register("turtle_master",
                _ -> new Potion("turtle_master", new MobEffectInstance(MobEffects.SLOWNESS, 400 / 2, 3),
                        new MobEffectInstance(MobEffects.RESISTANCE, 400 / 2, 2)));
        longTurtleMaster = potions.register("long_turtle_master",
                _ -> new Potion("turtle_master", new MobEffectInstance(MobEffects.SLOWNESS, 800 / 2, 3),
                        new MobEffectInstance(MobEffects.RESISTANCE, 800 / 2, 2)));
        strongTurtleMaster = potions.register("strong_turtle_master",
                _ -> new Potion("turtle_master", new MobEffectInstance(MobEffects.SLOWNESS, 400 / 2, 5),
                        new MobEffectInstance(MobEffects.RESISTANCE, 400 / 2, 3)));

        waterBreathing = potions.register("water_breathing",
                _ -> new Potion("water_breathing", new MobEffectInstance(MobEffects.WATER_BREATHING, 3600 / 2)));
        longWaterBreathing = potions.register("long_water_breathing",
                _ -> new Potion("water_breathing", new MobEffectInstance(MobEffects.WATER_BREATHING, 9600 / 2)));

        healing = potions.register("healing",
                _ -> new Potion("healing", new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1)));
        strongHealing = potions.register("strong_healing",
                _ -> new Potion("healing", new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 1)));

        harming = potions.register("harming",
                _ -> new Potion("harming", new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 1)));
        strongHarming = potions.register("strong_harming",
                _ -> new Potion("harming", new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 1, 1)));

        poison = potions.register("poison",
                _ -> new Potion("poison", new MobEffectInstance(MobEffects.POISON, 900 / 2)));
        longPoison = potions.register("long_poison",
                _ -> new Potion("poison", new MobEffectInstance(MobEffects.POISON, 1800 / 2)));
        strongPoison = potions.register("strong_poison",
                _ -> new Potion("poison", new MobEffectInstance(MobEffects.POISON, 432 / 2, 1)));

        regeneration = potions.register("regeneration",
                _ -> new Potion("regeneration", new MobEffectInstance(MobEffects.REGENERATION, 900 / 2)));
        longRegeneration = potions.register("long_regeneration",
                _ -> new Potion("regeneration", new MobEffectInstance(MobEffects.REGENERATION, 1800 / 2)));
        strongRegeneration = potions.register("strong_regeneration",
                _ -> new Potion("regeneration", new MobEffectInstance(MobEffects.REGENERATION, 450 / 2, 1)));

        strength = potions.register("strength",
                _ -> new Potion("strength", new MobEffectInstance(MobEffects.STRENGTH, 3600 / 2)));
        longStrength = potions.register("long_strength",
                _ -> new Potion("strength", new MobEffectInstance(MobEffects.STRENGTH, 9600 / 2)));
        strongStrength = potions.register("strong_strength",
                _ -> new Potion("strength", new MobEffectInstance(MobEffects.STRENGTH, 1800 / 2, 1)));

        weakness = potions.register("weakness",
                _ -> new Potion("weakness", new MobEffectInstance(MobEffects.WEAKNESS, 1800 / 2)));
        longWeakness = potions.register("long_weakness",
                _ -> new Potion("weakness", new MobEffectInstance(MobEffects.WEAKNESS, 4800 / 2)));

        luck = potions.register("luck",
                _ -> new Potion("luck", new MobEffectInstance(MobEffects.LUCK, 6000 / 2)));

        slowFalling = potions.register("slow_falling",
                _ -> new Potion("slow_falling", new MobEffectInstance(MobEffects.SLOW_FALLING, 1800 / 2)));
        longSlowFalling = potions.register("long_slow_falling",
                _ -> new Potion("slow_falling", new MobEffectInstance(MobEffects.SLOW_FALLING, 4800 / 2)));

        windCharged = potions.register("wind_charged",
                _ -> new Potion("wind_charged", new MobEffectInstance(MobEffects.WIND_CHARGED, 3600 / 2)));
        weaving = potions.register("weaving",
                _ -> new Potion("weaving", new MobEffectInstance(MobEffects.WEAVING, 3600 / 2)));
        oozing = potions.register("oozing",
                _ -> new Potion("oozing", new MobEffectInstance(MobEffects.OOZING, 3600 / 2)));
        infested = potions.register("infested",
                _ -> new Potion("infested", new MobEffectInstance(MobEffects.INFESTED, 3600 / 2)));
    }
}
