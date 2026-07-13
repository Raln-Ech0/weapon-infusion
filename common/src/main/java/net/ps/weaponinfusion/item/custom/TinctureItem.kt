package net.ps.weaponinfusion.item.custom

import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.PotionItem
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionContents
import net.ps.weaponinfusion.potion.ModPotions.potionMapping

class TinctureItem(properties: Properties) : PotionItem(properties.stacksTo(16).usingConvertsTo(Items.GLASS_BOTTLE)) {
    override fun getDefaultInstance(): ItemStack {
        val stack: ItemStack = super.defaultInstance
        val contents: PotionContents = stack[DataComponents.POTION_CONTENTS]!!
        contents.potion().ifPresent { potion ->
            var potion: Holder<Potion> = potion
            potion = potionMapping.getOrDefault(potion, potion)
            stack[DataComponents.POTION_CONTENTS] = PotionContents(potion)
        }
        return stack
    }
}
