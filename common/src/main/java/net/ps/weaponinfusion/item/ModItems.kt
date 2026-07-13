package net.ps.weaponinfusion.item

import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar
import net.blay09.mods.balm.world.item.BalmItemRegistrar
import net.blay09.mods.balm.world.item.DeferredItem
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.item.component.Consumables
import net.ps.weaponinfusion.WeaponInfusion
import net.ps.weaponinfusion.WeaponInfusion.id
import net.ps.weaponinfusion.item.custom.SandPaperItem
import net.ps.weaponinfusion.item.custom.TinctureItem

object ModItems {
    lateinit var tincture: DeferredItem
    lateinit var sandPaper: DeferredItem

    fun initialize(items: BalmItemRegistrar) {
        tincture = items.register(
            "tincture",
            ::TinctureItem
        ) { properties ->
            properties
                .component(DataComponents.POTION_CONTENTS, PotionContents(Potions.WATER))
                .component(
                    DataComponents.CONSUMABLE,
                    Consumables.defaultDrink().consumeSeconds(0.8f).build()
                )
        }.asDeferredItem()
        sandPaper = items.register("sand_paper", ::SandPaperItem)
            .asDeferredItem()
    }

    fun initialize(creativeModeTabs: BalmCreativeModeTabRegistrar) {
        creativeModeTabs.register(WeaponInfusion.MOD_ID
        ) { builder ->
            builder.title(
                Component.translatable(id("tincture_items").toLanguageKey("itemGroup"))
            )
                .icon {
                    val stack = tincture.createStack()
                    stack[DataComponents.POTION_CONTENTS] =  PotionContents(Potions.POISON)
                    stack
                }
                .displayItems { displayParameters, output ->
                    generatePotionEffectTypes(displayParameters, output, tincture.asItem())
                    output.accept(sandPaper)
                }
        }
    }

    private fun generatePotionEffectTypes(
        displayParameters: CreativeModeTab.ItemDisplayParameters,
        output: CreativeModeTab.Output,
        item: Item
    ) {
        displayParameters.holders().lookup(Registries.POTION)
            .ifPresent { potions ->
                potions.listElements()
                    .filter { potion ->
                        potion.key().identifier().namespace == WeaponInfusion.MOD_ID
                                || potion.value().effects.toTypedArray().isEmpty()
                    }
                    .filter { potion ->
                        potion.value().isEnabled(displayParameters.enabledFeatures())
                    }
                    .map { potion ->
                        PotionContents.createItemStack(
                            item,
                            potion
                        )
                    }
                    .forEach(output::accept)
            }
    }
}
