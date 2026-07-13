package net.ps.weaponinfusion

import net.blay09.mods.balm.core.BalmRegistrars
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.ps.weaponinfusion.data.ModDataComponents
import net.ps.weaponinfusion.event.ModEvents
import net.ps.weaponinfusion.item.ModItems
import net.ps.weaponinfusion.potion.ModPotions
import net.ps.weaponinfusion.sound.ModSoundEvents
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object WeaponInfusion {
    val logger: Logger = LoggerFactory.getLogger(WeaponInfusion::class.java)

    const val MOD_ID: String = "weaponinfusion"

    fun id(path: String): Identifier {
        return Identifier.fromNamespaceAndPath(MOD_ID, path)
    }

    fun initialize(registrars: BalmRegistrars) {
        registrars.items(ModItems::initialize)
        registrars.creativeModeTabs(ModItems::initialize)

        registrars.dataComponentTypes(ModDataComponents::initialize)

        registrars.registrar(Registries.POTION, ModPotions::initialize)
        registrars.registrar(Registries.SOUND_EVENT, ModSoundEvents::initialize)

        ModEvents.initialize()
    }
}
