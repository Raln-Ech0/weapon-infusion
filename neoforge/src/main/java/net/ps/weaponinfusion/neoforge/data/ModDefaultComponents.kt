package net.ps.weaponinfusion.neoforge.data;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.ps.weaponinfusion.WeaponInfusion;
import net.ps.weaponinfusion.data.ModDataComponents;

@EventBusSubscriber(modid = WeaponInfusion.MOD_ID)
public class ModDefaultComponents {
    private ModDefaultComponents() {
        /* This utility class should not be instantiated */
    }

    @SubscribeEvent
    public static void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        event.modifyMatching(
                (item, components) -> components.has(DataComponents.WEAPON),
                (builder, _, _) -> {
                    builder.set(ModDataComponents.charges.value(), 0);
                    builder.set(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
                }
        );
    }
}
