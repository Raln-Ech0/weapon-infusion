package net.ps.weaponinfusion.item.custom

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.ChatFormatting
import net.minecraft.core.component.DataComponents
import net.minecraft.core.particles.ItemParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUseAnimation
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.item.component.TooltipDisplay
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import net.ps.weaponinfusion.WeaponInfusion.id
import net.ps.weaponinfusion.data.ModDataComponents
import net.ps.weaponinfusion.sound.ModSoundEvents
import java.util.function.Consumer

class SandPaperItem(properties: Properties) : Item(properties.durability(8)) {
    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        val stack = player.getItemInHand(hand)

        if (Helper.isPolishing(stack)) {
            player.startUsingItem(hand)
            return InteractionResult.PASS
        }

        val otherHand = if (hand == InteractionHand.MAIN_HAND) InteractionHand.OFF_HAND else InteractionHand.MAIN_HAND
        val itemInOtherHand = player.getItemInHand(otherHand)
        if (Helper.canPolish(itemInOtherHand)) {
            val item = itemInOtherHand.copy()
            val toPolish = item.split(1)


            player.startUsingItem(hand)
            stack[ModDataComponents.sandPaperPolishing.value()] = SandPaperItemComponent(toPolish)
            player.setItemInHand(otherHand, item)
            return InteractionResult.SUCCESS
        }

        val hitVec = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE).location

        val bb = AABB(hitVec, hitVec).inflate(1.0)
        var pickUp: ItemEntity? = null
        for (itemEntity in level.getEntitiesOfClass(ItemEntity::class.java, bb)) {
            if (itemEntity.isAlive || itemEntity.position()
                    .distanceTo(player.position()) >= 3 || Helper.canPolish(itemEntity.item)
            ) {
                pickUp = itemEntity
                break
            }
        }

        if (pickUp == null) {
            return InteractionResult.FAIL
        }

        val item = pickUp.item.copy()
        val toPolish = item.split(1)

        player.startUsingItem(hand)

        if (!level.isClientSide) {
            stack[ModDataComponents.sandPaperPolishing.value()] = SandPaperItemComponent(toPolish)

            if (item.isEmpty) pickUp.discard()
            else pickUp.item = item
        }
        return InteractionResult.SUCCESS
    }

    override fun finishUsingItem(stack: ItemStack, level: Level, entity: LivingEntity): ItemStack {
        if (entity !is Player) return stack
        if (Helper.isPolishing(stack)) {
            Helper.getPolishingItem(stack).let { toPolish ->
                val polished =
                    Helper.applyPolish(toPolish!!)
                if (level.isClientSide) {
                    Helper.spawnParticles(entity.getEyePosition(1f).add(entity.lookAngle.scale(.5)), stack, level)
                }
                Helper.returnToInventory(entity, polished)
            }

            entity.cooldowns.addCooldown(stack, 20)

            Helper.stopPolishing(stack)
            stack.hurtAndBreak(1, entity, entity.usedItemHand)
        }

        return stack
    }

    override fun releaseUsing(stack: ItemStack, level: Level, entity: LivingEntity, timeLeft: Int): Boolean {
        if (entity is Player && Helper.isPolishing(stack)) {
            Helper.getPolishingItem(stack)
                .let { toPolish -> Helper.returnToInventory(entity, toPolish!!) }

            Helper.stopPolishing(stack)
        }
        return true
    }

    override fun onUseTick(level: Level, entity: LivingEntity, stack: ItemStack, ticksRemaining: Int) {
        if (Helper.isPolishing(stack)) {
            Helper.getPolishingItem(stack).let { polishing ->
                if (!polishing!!.isEmpty) {
                    entity.spawnItemParticles(polishing, 1)
                }
            }
        }

        if ((entity.ticksUsingItem - 6) % 7 == 0) {
            entity.playSound(
                ModSoundEvents.sandingShort.value(), 0.9f + 0.2f * level.random.nextFloat(),
                level.random.nextFloat() * 0.2f + 0.9f
            )
        }
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        display: TooltipDisplay,
        builder: Consumer<Component>,
        tooltipFlag: TooltipFlag
    ) {
        super.appendHoverText(stack, context, display, builder, tooltipFlag)

        builder.accept(
            Component.translatable(
                id("sand_paper.tooltip").toLanguageKey("tooltip"))
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC)
        )
    }

    override fun getUseAnimation(stack: ItemStack): ItemUseAnimation {
        return ItemUseAnimation.EAT
    }

    override fun getUseDuration(stack: ItemStack, entity: LivingEntity): Int {
        return 32
    }

    data class SandPaperItemComponent(val item: ItemStack) {
        companion object {
            val codec: Codec<SandPaperItemComponent> =
                RecordCodecBuilder.create { instance ->
                    instance
                        .group(
                            ItemStack.OPTIONAL_CODEC.fieldOf("item")
                                .forGetter(SandPaperItemComponent::item)
                        )
                        .apply(
                            instance,
                            ::SandPaperItemComponent
                        )
                }

            val streamCodec: StreamCodec<RegistryFriendlyByteBuf, SandPaperItemComponent> =
                StreamCodec.composite(
                    ItemStack.OPTIONAL_STREAM_CODEC,
                    SandPaperItemComponent::item,
                    ::SandPaperItemComponent)
        }
    }

    private object Helper {
        fun canPolish(stack: ItemStack): Boolean {
            return stack.has(DataComponents.WEAPON)
        }

        fun isPolishing(stack: ItemStack): Boolean {
            return stack.has(ModDataComponents.sandPaperPolishing.value())
        }

        fun stopPolishing(stack: ItemStack) {
            stack.remove(ModDataComponents.sandPaperPolishing.value())
        }

        fun applyPolish(stack: ItemStack): ItemStack {
            stack[DataComponents.POTION_CONTENTS] = PotionContents.EMPTY
            stack[ModDataComponents.charges.value()] = 0
            return stack
        }

        fun spawnParticles(location: Vec3, stack: ItemStack, world: Level) {
            repeat(20) {
                val motion = offsetRandomly(Vec3.ZERO, world.random, 1 / 8f)
                world.addParticle(
                    ItemParticleOption(ParticleTypes.ITEM, stack.item), location.x, location.y,
                    location.z, motion.x, motion.y, motion.z
                )
            }
        }

        fun returnToInventory(player: Player, stack: ItemStack) {
            val inv = player.inventory
            if (inv.selectedItem.isEmpty) {
                inv.selectedItem = stack
            } else if (player.offhandItem.isEmpty) {
                player.setItemInHand(InteractionHand.OFF_HAND, stack)
            } else {
                inv.placeItemBackInInventory(stack)
            }
        }

        fun getPolishingItem(sandPaper: ItemStack): ItemStack? {
            return sandPaper[ModDataComponents.sandPaperPolishing.value()]?.item
        }

        fun offsetRandomly(vec: Vec3, r: RandomSource, radius: Float): Vec3 {
            return Vec3(vec.x + ((r.nextFloat() - 0.5F) * 2.0F * radius), vec.y + ((r.nextFloat() - 0.5F) * 2.0F * radius), vec.z + ((r.nextFloat() - 0.5F) * 2.0F * radius))
        }
    }
}