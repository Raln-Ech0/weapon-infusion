package net.ps.weaponinfusion.fabric.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.ps.weaponinfusion.item.ModItems;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    @SuppressWarnings("java:S1186")
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {}

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generatePotion(ModItems.tincture.asItem());
    }
}
