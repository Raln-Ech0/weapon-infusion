package net.ps.weaponinfusion.fabric.potion;

import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.world.item.Items;
import net.ps.weaponinfusion.item.ModItems;

public class ModBrewingRecipes {
    private ModBrewingRecipes() {
        /* This utility class should not be instantiated */
    }

    public static void initialize() {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.addContainer(ModItems.tincture.asItem());
            builder.addContainerRecipe(Items.POTION, Items.SLIME_BALL, ModItems.tincture.asItem());
        });
    }
}
