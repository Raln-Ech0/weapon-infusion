package net.ps.weaponinfusion.item.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.ps.weaponinfusion.data.ModDataComponents;
import net.ps.weaponinfusion.sound.ModSoundEvents;
import net.ps.weaponinfusion.util.VecHelper;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static net.ps.weaponinfusion.WeaponInfusion.id;

public class SandPaperItem extends Item {

    public SandPaperItem(Properties properties) {
        super(properties.durability(8));
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (Helper.isPolishing(stack)) {
            player.startUsingItem(hand);
            return InteractionResult.PASS;
        }

        InteractionHand otherHand =
                hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack itemInOtherHand = player.getItemInHand(otherHand);
        if (Helper.canPolish(itemInOtherHand)) {
            ItemStack item = itemInOtherHand.copy();
            ItemStack toPolish = item.split(1);


            player.startUsingItem(hand);
            stack.set(ModDataComponents.sandPaperPolishing.value(), new SandPaperItemComponent(toPolish));
            player.setItemInHand(otherHand, item);
            return InteractionResult.SUCCESS;
        }

        Vec3 hitVec = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE).getLocation();

        AABB bb = new AABB(hitVec, hitVec).inflate(1f);
        ItemEntity pickUp = null;
        for (ItemEntity itemEntity : level.getEntitiesOfClass(ItemEntity.class, bb)) {
            if (itemEntity.isAlive() || itemEntity.position().distanceTo(player.position()) >= 3 || Helper.canPolish(itemEntity.getItem())) {
                pickUp = itemEntity;
                break;
            }
        }

        if (pickUp == null) {
            return InteractionResult.FAIL;
        }

        ItemStack item = pickUp.getItem()
                .copy();
        ItemStack toPolish = item.split(1);

        player.startUsingItem(hand);

        if (!level.isClientSide()) {
            stack.set(ModDataComponents.sandPaperPolishing.value(), new SandPaperItemComponent(toPolish));

            if (item.isEmpty())
                pickUp.discard();
            else
                pickUp.setItem(item);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity entity) {
        if (!(entity instanceof Player player))
            return stack;
        if (Helper.isPolishing(stack)) {
            Helper.getPolishingItem(stack).ifPresent(toPolish -> {
                ItemStack polished =
                        Helper.applyPolish(toPolish);

                if (level.isClientSide()) {
                    Helper.spawnParticles(player.getEyePosition(1).add(player.getLookAngle().scale(.5f)), stack, level);
                }

                Helper.returnToInventory(player, polished);
            });

            player.getCooldowns().addCooldown(stack, 20);

            Helper.stopPolishing(stack);
            stack.hurtAndBreak(1, player, player.getUsedItemHand());
        }

        return stack;
    }

    @Override
    public boolean releaseUsing(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player && Helper.isPolishing(stack)) {
            Helper.getPolishingItem(stack).ifPresent(toPolish -> Helper.returnToInventory(player, toPolish));

            Helper.stopPolishing(stack);
        }
        return true;
    }

    @Override
    public void onUseTick(@NonNull Level level, @NonNull LivingEntity entity, @NonNull ItemStack stack, int ticksRemaining) {
        if (Helper.isPolishing(stack)) {
            Helper.getPolishingItem(stack).ifPresent(polishing -> {
                if (!polishing.isEmpty()) {
                    entity.spawnItemParticles(polishing, 1);
                }
            });
        }

        if ((entity.getTicksUsingItem() - 6) % 7 == 0) {
            entity.playSound(ModSoundEvents.sandingShort.value(), 0.9F + 0.2F * level.getRandom().nextFloat(),
                    level.getRandom().nextFloat() * 0.2F + 0.9F);
        }
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, display, builder, tooltipFlag);

        builder.accept(Component.translatable(
                        id("sand_paper.tooltip").toLanguageKey("tooltip"))
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC)
        );
    }

    @Override
    public @NonNull ItemUseAnimation getUseAnimation(@NonNull ItemStack stack) {
        return ItemUseAnimation.EAT;
    }

    @Override
    public int getUseDuration(@NonNull ItemStack stack, @NonNull LivingEntity entity) {
        return 32;
    }

    public record SandPaperItemComponent(ItemStack item) {
        public static final Codec<SandPaperItemComponent> CODEC = RecordCodecBuilder.create(instance -> instance
                .group(ItemStack.OPTIONAL_CODEC.fieldOf("item").forGetter(i -> i.item))
                .apply(instance, SandPaperItemComponent::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, SandPaperItemComponent> STREAM_CODEC =
                StreamCodec.composite(ItemStack.OPTIONAL_STREAM_CODEC, i -> i.item, SandPaperItemComponent::new);

        @Override
        public int hashCode() {
            return Objects.hash(item.getItem(), item.getCount(), item.getComponents());
        }
    }

    private static class Helper {
        private static boolean canPolish(@NonNull ItemStack stack) {
            return stack.has(DataComponents.WEAPON);
        }

        private static boolean isPolishing(ItemStack stack) {
            return stack.has(ModDataComponents.sandPaperPolishing.value());
        }

        private static void stopPolishing(ItemStack stack) {
            stack.remove(ModDataComponents.sandPaperPolishing.value());
        }

        private static ItemStack applyPolish(@NonNull ItemStack stack) {
            stack.set(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            stack.set(ModDataComponents.charges.value(), 0);
            return stack;
        }

        private static void spawnParticles(Vec3 location, ItemStack stack, Level world) {
            for (int i = 0; i < 20; i++) {
                Vec3 motion = VecHelper.offsetRandomly(Vec3.ZERO, world.getRandom(), 1 / 8f);
                world.addParticle(new ItemParticleOption(ParticleTypes.ITEM, stack.getItem()), location.x, location.y,
                        location.z, motion.x, motion.y, motion.z);
            }
        }

        private static void returnToInventory(@NonNull Player player, ItemStack stack) {
            Inventory inv = player.getInventory();
            if (inv.getSelectedItem().isEmpty()) {
                inv.setSelectedItem(stack);
            } else if (player.getOffhandItem().isEmpty()) {
                player.setItemInHand(InteractionHand.OFF_HAND, stack);
            } else {
                inv.placeItemBackInInventory(stack);
            }
        }

        private static Optional<ItemStack> getPolishingItem(@NonNull ItemStack sandPaper) {
            return Optional.ofNullable(sandPaper.get(ModDataComponents.sandPaperPolishing.value()))
                    .map(SandPaperItemComponent::item);
        }
    }
}