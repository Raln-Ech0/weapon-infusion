package net.ps.weaponinfusion;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.blay09.mods.balm.core.BalmRegistrars;
import net.ps.weaponinfusion.data.ModDataComponents;
import net.ps.weaponinfusion.event.ModEvents;
import net.ps.weaponinfusion.potion.ModPotions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.ps.weaponinfusion.item.ModItems;

public class WeaponInfusion {
    private WeaponInfusion() {
        /* This utility class should not be instantiated */
    }

    public static final Logger logger = LoggerFactory.getLogger(WeaponInfusion.class);

    public static final String MOD_ID = "weaponinfusion";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void initialize(BalmRegistrars registrars) {
        registrars.items(ModItems::initialize);
        registrars.registrar(Registries.POTION, ModPotions::initialize);
        registrars.creativeModeTabs(ModItems::initialize);

        registrars.dataComponentTypes(ModDataComponents::initialize);

        ModEvents.initialize();
    }
}
