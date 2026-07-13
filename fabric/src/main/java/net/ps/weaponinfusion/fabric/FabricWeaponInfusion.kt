package net.ps.weaponinfusion.fabric

import net.blay09.mods.balm.Balm
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext
import net.fabricmc.api.ModInitializer
import net.ps.weaponinfusion.WeaponInfusion
import net.ps.weaponinfusion.fabric.data.ModDefaultComponents
import net.ps.weaponinfusion.fabric.potion.ModBrewingRecipes

class FabricWeaponInfusion : ModInitializer {
    override fun onInitialize() {
        Balm.initializeMod(
            WeaponInfusion.MOD_ID,
            FabricLoadContext.INSTANCE,
            WeaponInfusion::initialize)

        ModBrewingRecipes.initialize()
        ModDefaultComponents.initialize()
    }
}
