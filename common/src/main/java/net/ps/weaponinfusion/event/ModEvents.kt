package net.ps.weaponinfusion.event

import net.blay09.mods.balm.platform.event.callback.PlayerCallback
import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.tags.ItemTags
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionContents
import net.ps.weaponinfusion.data.ModDataComponents
import net.ps.weaponinfusion.item.ModItems
import java.util.Optional
import java.util.function.Consumer

object ModEvents {
    fun initialize() {
        PlayerCallback.Attack.Before.EVENT.register(PlayerCallback.Attack.Before { player, target ->
            onAttack(player, target)
            true
        })
    }

    fun onAttack(player: Player, rawTarget: Entity?) {
        val weapon: ItemStack = player.weaponItem

        if (rawTarget is LivingEntity && weapon.has(DataComponents.WEAPON)) {
            var charges: Int = weapon[ModDataComponents.charges.value()]!!

            if (charges == 0) {
                val tincture = getTincture(player)

                if (tincture.isEmpty) return

                val tinctureContents: PotionContents? = tincture[DataComponents.POTION_CONTENTS]

                modifyDuration(tinctureContents!!, weapon)

                if (!player.isCreative) tincture.count = tincture.count() - 1
                weapon[ModDataComponents.charges.value()] = 12
            }

            val contents = weapon[DataComponents.POTION_CONTENTS]
            charges = weapon[ModDataComponents.charges.value()]!!

            for (effect in contents!!.allEffects) {
                rawTarget.addEffect(MobEffectInstance(effect))
            }
            if (!player.isCreative) weapon[ModDataComponents.charges.value()] = charges - 1
        }
    }

    private fun getTincture(player: Player): ItemStack {
        var tincture = ItemStack.EMPTY
        var minCount = ModItems.tincture.asItem().defaultMaxStackSize + 1

        for (stack in player.inventory.nonEquipmentItems) {
            if (stack.`is`(ModItems.tincture.asItem()) && stack.count < minCount) {
                minCount = stack.count
                tincture = stack
            }
        }

        return tincture
    }

    private fun modifyDuration(initialContents: PotionContents, weapon: ItemStack) {
        initialContents.potion().ifPresent { p: Holder<Potion> ->
            val duration = when {
                weapon.`is`(ItemTags.SWORDS) -> 60
                weapon.`is`(ItemTags.AXES) -> 100
                weapon.`is`(ItemTags.MACE_ENCHANTABLE) -> 120
                weapon.`is`(ItemTags.TRIDENT_ENCHANTABLE) -> 80

                weapon.`is`(ItemTags.SPEARS) -> when {
                    weapon.`is`(Items.DIAMOND_SPEAR) -> 70
                    weapon.`is`(Items.STONE_SPEAR) -> 50
                    weapon.`is`(Items.GOLDEN_SPEAR) -> 60
                    weapon.`is`(Items.NETHERITE_SPEAR) -> 80
                    weapon.`is`(Items.WOODEN_SPEAR) -> 40
                    weapon.`is`(Items.IRON_SPEAR) -> 60
                    weapon.`is`(Items.COPPER_SWORD) -> 60
                    else -> 0
                }

                else -> 0
            }
            val allEffects = p.value().effects.map { effect ->
                    MobEffectInstance(
                        effect.effect,
                        if (effect.effect.value().isInstantaneous) effect.duration else duration,
                        effect.amplifier
                    )
                }
                .toList()

            val contents = PotionContents(
                Optional.empty(),
                initialContents.customColor(), allEffects, initialContents.customName()
            )
            weapon[DataComponents.POTION_CONTENTS] = contents
        }
    }
}
