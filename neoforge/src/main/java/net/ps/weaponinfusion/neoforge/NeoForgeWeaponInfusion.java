package net.ps.weaponinfusion.neoforge;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.ps.weaponinfusion.WeaponInfusion;

@Mod(WeaponInfusion.MOD_ID)
public class NeoForgeWeaponInfusion {
    public NeoForgeWeaponInfusion(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        Balm.initializeMod(WeaponInfusion.MOD_ID, context, WeaponInfusion::initialize);
    }
}
