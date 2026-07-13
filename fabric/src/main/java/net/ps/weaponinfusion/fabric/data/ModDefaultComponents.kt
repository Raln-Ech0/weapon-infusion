package net.ps.weaponinfusion.fabric.data

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.Item
import net.minecraft.world.item.alchemy.PotionContents
import net.ps.weaponinfusion.data.ModDataComponents
import java.util.stream.Collectors

object ModDefaultComponents {
    fun initialize() {
        DefaultItemComponentEvents.MODIFY.register { context: DefaultItemComponentEvents.ModifyContext ->
            context.modify(
                { item -> item.components().has(DataComponents.WEAPON) },
                { builder, _ ->
                    builder[ModDataComponents.charges.value()] = 0
                    builder[DataComponents.POTION_CONTENTS] = PotionContents.EMPTY
                }
            )
        }
    }
}
