package net.ps.weaponinfusion.potion

import net.blay09.mods.balm.core.BalmRegistrar
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionIds
import net.minecraft.world.item.alchemy.Potions

object ModPotions {
    val potionMapping: MutableMap<Holder<Potion>, Holder<Potion>> = HashMap()

    fun initialize(potions: BalmRegistrar.Scoped<Potion>) {
        val entries = listOf(
            Potions.NIGHT_VISION to PotionIds.NIGHT_VISION,
            Potions.LONG_NIGHT_VISION to PotionIds.LONG_NIGHT_VISION,
            Potions.INVISIBILITY to PotionIds.INVISIBILITY,
            Potions.LONG_INVISIBILITY to PotionIds.LONG_INVISIBILITY,
            Potions.LEAPING to PotionIds.LEAPING,
            Potions.LONG_LEAPING to PotionIds.LONG_LEAPING,
            Potions.STRONG_LEAPING to PotionIds.STRONG_LEAPING,
            Potions.FIRE_RESISTANCE to PotionIds.FIRE_RESISTANCE,
            Potions.LONG_FIRE_RESISTANCE to PotionIds.LONG_FIRE_RESISTANCE,
            Potions.SWIFTNESS to PotionIds.SWIFTNESS,
            Potions.LONG_SWIFTNESS to PotionIds.LONG_SWIFTNESS,
            Potions.STRONG_SWIFTNESS to PotionIds.STRONG_SWIFTNESS,
            Potions.SLOWNESS to PotionIds.SLOWNESS,
            Potions.LONG_SLOWNESS to PotionIds.LONG_SLOWNESS,
            Potions.STRONG_SLOWNESS to PotionIds.STRONG_SLOWNESS,
            Potions.TURTLE_MASTER to PotionIds.TURTLE_MASTER,
            Potions.LONG_TURTLE_MASTER to PotionIds.LONG_TURTLE_MASTER,
            Potions.STRONG_TURTLE_MASTER to PotionIds.STRONG_TURTLE_MASTER,
            Potions.WATER_BREATHING to PotionIds.WATER_BREATHING,
            Potions.LONG_WATER_BREATHING to PotionIds.LONG_WATER_BREATHING,
            Potions.HEALING to PotionIds.HEALING,
            Potions.STRONG_HEALING to PotionIds.STRONG_HEALING,
            Potions.HARMING to PotionIds.HARMING,
            Potions.STRONG_HARMING to PotionIds.STRONG_HARMING,
            Potions.POISON to PotionIds.POISON,
            Potions.LONG_POISON to PotionIds.LONG_POISON,
            Potions.STRONG_POISON to PotionIds.STRONG_POISON,
            Potions.REGENERATION to PotionIds.REGENERATION,
            Potions.LONG_REGENERATION to PotionIds.LONG_REGENERATION,
            Potions.STRONG_REGENERATION to PotionIds.STRONG_REGENERATION,
            Potions.STRENGTH to PotionIds.STRENGTH,
            Potions.LONG_STRENGTH to PotionIds.LONG_STRENGTH,
            Potions.STRONG_STRENGTH to PotionIds.STRONG_STRENGTH,
            Potions.WEAKNESS to PotionIds.WEAKNESS,
            Potions.LONG_WEAKNESS to PotionIds.LONG_WEAKNESS,
            Potions.LUCK to PotionIds.LUCK,
            Potions.SLOW_FALLING to PotionIds.SLOW_FALLING,
            Potions.LONG_SLOW_FALLING to PotionIds.LONG_SLOW_FALLING,
            Potions.WIND_CHARGED to PotionIds.WIND_CHARGED,
            Potions.WEAVING to PotionIds.WEAVING,
            Potions.OOZING to PotionIds.OOZING,
            Potions.INFESTED to PotionIds.INFESTED,
        )

        for ((vanilla, id) in entries) {
            potionMapping[vanilla] = register(potions, vanilla, id)
        }
    }

    private fun register(
        potions: BalmRegistrar.Scoped<Potion>,
        copy: Holder<Potion>,
        id: ResourceKey<Potion>
    ): Holder<Potion> {
        val effects = copy.value().effects.map { mobEffectInstance ->
            MobEffectInstance(
                mobEffectInstance.effect,
                mobEffectInstance.duration / 2,
                mobEffectInstance.amplifier
            )
        }

        return potions.register(id.identifier().path) { _ ->
            Potion(
                copy.value().name(),
                *effects.toTypedArray()
            )
        }
    }
}
