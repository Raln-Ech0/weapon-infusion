package net.ps.weaponinfusion.neoforge;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.ps.weaponinfusion.WeaponInfusion;
import net.ps.weaponinfusion.data.ModDataComponents;
import net.ps.weaponinfusion.neoforge.data.ModDefaultComponents;

@Mod(WeaponInfusion.MOD_ID)
public class NeoForgeWeaponInfusion {

    public NeoForgeWeaponInfusion(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        Balm.initializeMod(WeaponInfusion.MOD_ID, context, WeaponInfusion::initialize);
    }
}
