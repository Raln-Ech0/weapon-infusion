package net.ps.weaponinfusion.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.ps.weaponinfusion.WeaponInfusion;
import net.ps.weaponinfusion.item.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registryLookup, @NonNull RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                shapeless(RecipeCategory.MISC, ModItems.sandPaper)
                        .requires(Items.PAPER)
                        .requires(Items.SAND)
                        .unlockedBy("has_paper_and_sand", inventoryTrigger(ItemPredicate.Builder.item().of(registries.lookupOrThrow(Registries.ITEM), Items.PAPER, Items.SAND)))
                        .save(exporter);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return WeaponInfusion.MOD_ID;
    }
}
