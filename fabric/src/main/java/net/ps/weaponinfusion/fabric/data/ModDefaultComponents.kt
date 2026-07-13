package net.ps.weaponinfusion.fabric.data;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionContents;
import net.ps.weaponinfusion.data.ModDataComponents;

import java.util.Set;
import java.util.stream.Collectors;

public class ModDefaultComponents {
    private ModDefaultComponents() {
        /* This utility class should not be instantiated */
    }

    public static void initialize() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            Set<Item> meleeWeaponTools = BuiltInRegistries.ITEM
                    .get(ConventionalItemTags.MELEE_WEAPON_TOOLS)
                    .get()
                    .stream()
                    .map(Holder::value)
                    .collect(Collectors.toSet());

            context.modify(meleeWeaponTools, (builder, _) -> {
                builder.set(ModDataComponents.charges.value(), 0);
                builder.set(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            });
        });
    }
}
