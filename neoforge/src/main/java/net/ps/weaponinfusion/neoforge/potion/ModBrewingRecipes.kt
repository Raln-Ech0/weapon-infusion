package net.ps.weaponinfusion.neoforge.potion;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.ps.weaponinfusion.WeaponInfusion;
import net.ps.weaponinfusion.item.ModItems;

@EventBusSubscriber(modid = WeaponInfusion.MOD_ID)
public class ModBrewingRecipes {
    private ModBrewingRecipes() {
        /* This utility class should not be instantiated */
    }

    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addContainer(ModItems.tincture.asItem());
        builder.addContainerRecipe(Items.POTION, Items.SLIME_BALL, ModItems.tincture.asItem());
    }
}
