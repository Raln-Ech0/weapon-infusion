package net.ps.weaponinfusion.fabric.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.fabricmc.api.ClientModInitializer;
import net.ps.weaponinfusion.WeaponInfusion;
import net.ps.weaponinfusion.client.WeaponInfusionClient;

public class FabricWeaponInfusionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(WeaponInfusion.MOD_ID, FabricLoadContext.INSTANCE, WeaponInfusionClient::initialize);
    }
}
