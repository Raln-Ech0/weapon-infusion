package net.ps.weaponinfusion.fabric.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancements.predicates.ItemPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.world.item.Items
import net.ps.weaponinfusion.WeaponInfusion
import net.ps.weaponinfusion.item.ModItems
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(output: FabricPackOutput, provider: CompletableFuture<HolderLookup.Provider>) :
    FabricRecipeProvider(output, provider) {
    override fun createRecipeProvider(registryLookup: HolderLookup.Provider, exporter: RecipeOutput): RecipeProvider {
        return object : RecipeProvider(registryLookup, exporter) {
            override fun buildRecipes() {
                shapeless(RecipeCategory.MISC, ModItems.sandPaper)
                    .requires(Items.PAPER)
                    .requires(Items.SAND)
                    .unlockedBy(
                        "has_paper_and_sand",
                        inventoryTrigger(
                            ItemPredicate.Builder.item()
                                .of(registries.lookupOrThrow(Registries.ITEM), Items.PAPER, Items.SAND)
                        )
                    )
                    .save(exporter)
            }
        }
    }

    override fun getName(): String {
        return WeaponInfusion.MOD_ID
    }
}
