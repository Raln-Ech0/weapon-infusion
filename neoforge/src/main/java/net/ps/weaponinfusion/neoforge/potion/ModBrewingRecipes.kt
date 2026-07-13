package net.ps.weaponinfusion.neoforge.potion

import net.minecraft.world.item.Items
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent
import net.ps.weaponinfusion.WeaponInfusion
import net.ps.weaponinfusion.item.ModItems

@EventBusSubscriber(modid = WeaponInfusion.MOD_ID)
object ModBrewingRecipes {
    @SubscribeEvent
    fun registerBrewingRecipes(event: RegisterBrewingRecipesEvent) {
        val builder = event.builder

        builder.addContainer(ModItems.tincture.asItem())
        builder.addContainerRecipe(Items.POTION, Items.SLIME_BALL, ModItems.tincture.asItem())
    }
}
