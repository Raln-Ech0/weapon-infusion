package net.ps.weaponinfusion.fabric.datagen

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ItemModelUtils
import net.minecraft.resources.Identifier
import net.ps.weaponinfusion.item.ModItems

class ModModelProvider(output: FabricPackOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(blockModelGenerators: BlockModelGenerators) {}

    override fun generateItemModels(itemModelGenerator: ItemModelGenerators) {
        itemModelGenerator.generatePotion(ModItems.tincture.asItem())
        itemModelGenerator.itemModelOutput.accept(
            ModItems.sandPaper.asItem(),
            ItemModelUtils.plainModel(Identifier.withDefaultNamespace("item/paper"))
        )
    }
}
