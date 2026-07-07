package net.ps.weaponinfusion.neoforge.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.ps.weaponinfusion.WeaponInfusion;
import net.ps.weaponinfusion.client.WeaponInfusionClient;

@Mod(value = WeaponInfusion.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeWeaponInfusionClient {

    public NeoForgeWeaponInfusionClient(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        BalmClient.initializeMod(WeaponInfusion.MOD_ID, context, WeaponInfusionClient::initialize);
    }
}
