package net.ps.weaponinfusion.potion;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
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

    private static final String NIGHT_VISION_ID = "night_vision";
    private static final String INVISIBILITY_ID = "invisibility";
    private static final String LEAPING_ID = "leaping";
    private static final String FIRE_RESISTANCE_ID = "fire_resistance";

    private static final String SWIFTNESS_ID = "swiftness";
    private static final String SLOWNESS_ID = "slowness";
    private static final String TURTLE_MASTER_ID = "turtle_master";
    private static final String WATER_BREATHING_ID = "water_breathing";

    private static final String HEALING_ID = "healing";
    private static final String HARMING_ID = "harming";
    private static final String POISON_ID = "poison";
    private static final String REGENERATION_ID = "regeneration";

    private static final String STRENGTH_ID = "strength";
    private static final String WEAKNESS_ID = "weakness";
    private static final String LUCK_ID = "luck";
    private static final String SLOW_FALLING_ID = "slow_falling";

    private static final String WIND_CHARGED_ID = "wind_charged";
    private static final String WEAVING_ID = "weaving";
    private static final String OOZING_ID = "oozing";
    private static final String INFESTED_ID = "infested";

    public static void initialize(BalmRegistrar.@NonNull Scoped<Potion> potions) {
        nightVision = potions.register(NIGHT_VISION_ID,
                _ -> new Potion(NIGHT_VISION_ID, new MobEffectInstance(MobEffects.NIGHT_VISION, 3600 / 2)));
        longNightVision = potions.register(longEffect(NIGHT_VISION_ID),
                _ -> new Potion(NIGHT_VISION_ID, new MobEffectInstance(MobEffects.NIGHT_VISION, 9600 / 2)));

        invisibility = potions.register("invisibility",
                _ -> new Potion("invisibility", new MobEffectInstance(MobEffects.INVISIBILITY, 3600 / 2)));
        longInvisibility = potions.register("long_invisibility",
                _ -> new Potion("invisibility", new MobEffectInstance(MobEffects.INVISIBILITY, 9600 / 2)));

        leaping = potions.register(LEAPING_ID,
                _ -> new Potion(LEAPING_ID, new MobEffectInstance(MobEffects.JUMP_BOOST, 3600 / 2)));
        longLeaping = potions.register(longEffect(LEAPING_ID),
                _ -> new Potion(LEAPING_ID, new MobEffectInstance(MobEffects.JUMP_BOOST, 9600 / 2)));
        strongLeaping = potions.register(strongEffect(LEAPING_ID),
                _ -> new Potion(LEAPING_ID, new MobEffectInstance(MobEffects.JUMP_BOOST, 1800 / 2, 1)));

        fireResistance = potions.register(FIRE_RESISTANCE_ID,
                _ -> new Potion(FIRE_RESISTANCE_ID, new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600 / 2)));
        longFireResistance = potions.register(longEffect(FIRE_RESISTANCE_ID),
                _ -> new Potion(FIRE_RESISTANCE_ID, new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 9600 / 2)));

        swiftness = potions.register(SWIFTNESS_ID,
                _ -> new Potion(SWIFTNESS_ID, new MobEffectInstance(MobEffects.SPEED, 3600 / 2)));
        longSwiftness = potions.register(longEffect(SWIFTNESS_ID),
                _ -> new Potion(SWIFTNESS_ID, new MobEffectInstance(MobEffects.SPEED, 9600 / 2)));
        strongSwiftness = potions.register(strongEffect(SWIFTNESS_ID),
                _ -> new Potion(SWIFTNESS_ID, new MobEffectInstance(MobEffects.SPEED, 1800 / 2, 1)));

        slowness = potions.register(SLOWNESS_ID,
                _ -> new Potion(SLOWNESS_ID, new MobEffectInstance(MobEffects.SLOWNESS, 1800 / 2)));
        longSlowness = potions.register(longEffect(SLOWNESS_ID),
                _ -> new Potion(SLOWNESS_ID, new MobEffectInstance(MobEffects.SLOWNESS, 4800 / 2)));
        strongSlowness = potions.register(strongEffect(SLOWNESS_ID),
                _ -> new Potion(SLOWNESS_ID, new MobEffectInstance(MobEffects.SLOWNESS, 400 / 2, 3)));

        turtleMaster = potions.register(TURTLE_MASTER_ID,
                _ -> new Potion(TURTLE_MASTER_ID, new MobEffectInstance(MobEffects.SLOWNESS, 400 / 2, 3),
                        new MobEffectInstance(MobEffects.RESISTANCE, 400 / 2, 2)));
        longTurtleMaster = potions.register(longEffect(TURTLE_MASTER_ID),
                _ -> new Potion(TURTLE_MASTER_ID, new MobEffectInstance(MobEffects.SLOWNESS, 800 / 2, 3),
                        new MobEffectInstance(MobEffects.RESISTANCE, 800 / 2, 2)));
        strongTurtleMaster = potions.register(strongEffect(TURTLE_MASTER_ID),
                _ -> new Potion(TURTLE_MASTER_ID, new MobEffectInstance(MobEffects.SLOWNESS, 400 / 2, 5),
                        new MobEffectInstance(MobEffects.RESISTANCE, 400 / 2, 3)));

        waterBreathing = potions.register(WATER_BREATHING_ID,
                _ -> new Potion(WATER_BREATHING_ID, new MobEffectInstance(MobEffects.WATER_BREATHING, 3600 / 2)));
        longWaterBreathing = potions.register(longEffect(WATER_BREATHING_ID),
                _ -> new Potion(WATER_BREATHING_ID, new MobEffectInstance(MobEffects.WATER_BREATHING, 9600 / 2)));

        healing = potions.register(HEALING_ID,
                _ -> new Potion(HEALING_ID, new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1)));
        strongHealing = potions.register(strongEffect(HEALING_ID),
                _ -> new Potion(HEALING_ID, new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 1)));

        harming = potions.register(HARMING_ID,
                _ -> new Potion(HARMING_ID, new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 1)));
        strongHarming = potions.register(strongEffect(HARMING_ID),
                _ -> new Potion(HARMING_ID, new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 1, 1)));

        poison = potions.register(POISON_ID,
                _ -> new Potion(POISON_ID, new MobEffectInstance(MobEffects.POISON, 900 / 2)));
        longPoison = potions.register(longEffect(POISON_ID),
                _ -> new Potion(POISON_ID, new MobEffectInstance(MobEffects.POISON, 1800 / 2)));
        strongPoison = potions.register(strongEffect(POISON_ID),
                _ -> new Potion(POISON_ID, new MobEffectInstance(MobEffects.POISON, 432 / 2, 1)));

        regeneration = potions.register(REGENERATION_ID,
                _ -> new Potion(REGENERATION_ID, new MobEffectInstance(MobEffects.REGENERATION, 900 / 2)));
        longRegeneration = potions.register(longEffect(REGENERATION_ID),
                _ -> new Potion(REGENERATION_ID, new MobEffectInstance(MobEffects.REGENERATION, 1800 / 2)));
        strongRegeneration = potions.register(strongEffect(REGENERATION_ID),
                _ -> new Potion(REGENERATION_ID, new MobEffectInstance(MobEffects.REGENERATION, 450 / 2, 1)));

        strength = potions.register(STRENGTH_ID,
                _ -> new Potion(STRENGTH_ID, new MobEffectInstance(MobEffects.STRENGTH, 3600 / 2)));
        longStrength = potions.register(longEffect(STRENGTH_ID),
                _ -> new Potion(STRENGTH_ID, new MobEffectInstance(MobEffects.STRENGTH, 9600 / 2)));
        strongStrength = potions.register(strongEffect(STRENGTH_ID),
                _ -> new Potion(STRENGTH_ID, new MobEffectInstance(MobEffects.STRENGTH, 1800 / 2, 1)));

        weakness = potions.register(WEAKNESS_ID,
                _ -> new Potion(WEAKNESS_ID, new MobEffectInstance(MobEffects.WEAKNESS, 1800 / 2)));
        longWeakness = potions.register(longEffect(WEAKNESS_ID),
                _ -> new Potion(WEAKNESS_ID, new MobEffectInstance(MobEffects.WEAKNESS, 4800 / 2)));

        luck = potions.register(LUCK_ID,
                _ -> new Potion(LUCK_ID, new MobEffectInstance(MobEffects.LUCK, 6000 / 2)));

        slowFalling = potions.register(SLOW_FALLING_ID,
                _ -> new Potion(SLOW_FALLING_ID, new MobEffectInstance(MobEffects.SLOW_FALLING, 1800 / 2)));
        longSlowFalling = potions.register(longEffect(SLOW_FALLING_ID),
                _ -> new Potion(SLOW_FALLING_ID, new MobEffectInstance(MobEffects.SLOW_FALLING, 4800 / 2)));

        windCharged = potions.register(WIND_CHARGED_ID,
                _ -> new Potion(WIND_CHARGED_ID, new MobEffectInstance(MobEffects.WIND_CHARGED, 3600 / 2)));
        weaving = potions.register(WEAVING_ID,
                _ -> new Potion(WEAVING_ID, new MobEffectInstance(MobEffects.WEAVING, 3600 / 2)));
        oozing = potions.register(OOZING_ID,
                _ -> new Potion(OOZING_ID, new MobEffectInstance(MobEffects.OOZING, 3600 / 2)));
        infested = potions.register(INFESTED_ID,
                _ -> new Potion(INFESTED_ID, new MobEffectInstance(MobEffects.INFESTED, 3600 / 2)));

        POTION_MAPPING.put(ModPotions.nightVision, Potions.NIGHT_VISION);
        POTION_MAPPING.put(ModPotions.longNightVision, Potions.LONG_NIGHT_VISION);

        POTION_MAPPING.put(ModPotions.invisibility, Potions.INVISIBILITY);
        POTION_MAPPING.put(ModPotions.longInvisibility, Potions.LONG_INVISIBILITY);

        POTION_MAPPING.put(ModPotions.leaping, Potions.LEAPING);
        POTION_MAPPING.put(ModPotions.longLeaping, Potions.LONG_LEAPING);
        POTION_MAPPING.put(ModPotions.strongLeaping, Potions.STRONG_LEAPING);

        POTION_MAPPING.put(ModPotions.fireResistance, Potions.FIRE_RESISTANCE);
        POTION_MAPPING.put(ModPotions.longFireResistance, Potions.LONG_FIRE_RESISTANCE);

        POTION_MAPPING.put(ModPotions.swiftness, Potions.SWIFTNESS);
        POTION_MAPPING.put(ModPotions.longSwiftness, Potions.LONG_SWIFTNESS);
        POTION_MAPPING.put(ModPotions.strongSwiftness, Potions.STRONG_SWIFTNESS);

        POTION_MAPPING.put(ModPotions.slowness, Potions.SLOWNESS);
        POTION_MAPPING.put(ModPotions.longSlowness, Potions.LONG_SLOWNESS);
        POTION_MAPPING.put(ModPotions.strongSlowness, Potions.STRONG_SLOWNESS);

        POTION_MAPPING.put(ModPotions.turtleMaster, Potions.TURTLE_MASTER);
        POTION_MAPPING.put(ModPotions.longTurtleMaster, Potions.LONG_TURTLE_MASTER);
        POTION_MAPPING.put(ModPotions.strongTurtleMaster, Potions.STRONG_TURTLE_MASTER);

        POTION_MAPPING.put(ModPotions.waterBreathing, Potions.WATER_BREATHING);
        POTION_MAPPING.put(ModPotions.longWaterBreathing, Potions.LONG_WATER_BREATHING);

        POTION_MAPPING.put(ModPotions.healing, Potions.HEALING);
        POTION_MAPPING.put(ModPotions.strongHealing, Potions.STRONG_HEALING);

        POTION_MAPPING.put(ModPotions.harming, Potions.HARMING);
        POTION_MAPPING.put(ModPotions.strongHarming, Potions.STRONG_HARMING);

        POTION_MAPPING.put(ModPotions.poison, Potions.POISON);
        POTION_MAPPING.put(ModPotions.longPoison, Potions.LONG_POISON);
        POTION_MAPPING.put(ModPotions.strongPoison, Potions.STRONG_POISON);

        POTION_MAPPING.put(ModPotions.regeneration, Potions.REGENERATION);
        POTION_MAPPING.put(ModPotions.longRegeneration, Potions.LONG_REGENERATION);
        POTION_MAPPING.put(ModPotions.strongRegeneration, Potions.STRONG_REGENERATION);

        POTION_MAPPING.put(ModPotions.strength, Potions.STRENGTH);
        POTION_MAPPING.put(ModPotions.strongStrength, Potions.STRONG_STRENGTH);
        POTION_MAPPING.put(ModPotions.longStrength, Potions.LONG_STRENGTH);

        POTION_MAPPING.put(ModPotions.weakness, Potions.WEAKNESS);
        POTION_MAPPING.put(ModPotions.longWeakness, Potions.LONG_WEAKNESS);

        POTION_MAPPING.put(ModPotions.luck, Potions.LUCK);

        POTION_MAPPING.put(ModPotions.slowFalling, Potions.SLOW_FALLING);
        POTION_MAPPING.put(ModPotions.longSlowFalling, Potions.LONG_SLOW_FALLING);

        POTION_MAPPING.put(ModPotions.windCharged, Potions.WIND_CHARGED);
        POTION_MAPPING.put(ModPotions.weaving, Potions.WEAVING);
        POTION_MAPPING.put(ModPotions.oozing, Potions.OOZING);
        POTION_MAPPING.put(ModPotions.infested, Potions.INFESTED);
    }

    private static Holder<Potion> registerPotion(BalmRegistrar.Scoped<Potion> potions, Holder<Potion> copy) {
        List<MobEffectInstance> effects = copy.value().getEffects().stream()
                .map(mobEffectInstance ->
                        new MobEffectInstance(mobEffectInstance.getEffect(), mobEffectInstance.getDuration() / 2))
                .toList();

        return potions.register(copy.value().name(),
                _ -> new Potion(
                        copy.value().name().replaceFirst("^long_", "").replaceFirst("^strong_", ""),
                        effects.toArray(MobEffectInstance[]::new)
                )
        );
    }

    private static String longEffect(String id) {
        return "long_" + id;
    }

    private static String strongEffect(String id) {
        return "strong_" + id;
    }

    public static Map<Holder<Potion>, Holder<Potion>> getPotionMapping() {
        return POTION_MAPPING;
    }
}
