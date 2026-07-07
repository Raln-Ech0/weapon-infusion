package net.ps.weaponinfusion.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.ps.weaponinfusion.data.ModDataComponents;
import net.ps.weaponinfusion.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.vavr.API.*;


@Mixin(Player.class)
public abstract class SpearMixin {
    @Inject(method = "stabAttack", at = @At("HEAD"))
    private void onStabAttack(EquipmentSlot slot, Entity target0, float baseDamage, boolean dealsDamage, boolean dealsKnockback, boolean dismounts, CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player) (Object) this;
        ItemStack weapon = player.getWeaponItem();

        if (target0 instanceof LivingEntity target) {
            if (!player.isUsingItem()) {
                if (Objects.requireNonNull(weapon.get(ModDataComponents.charges.value())) == 0) {
                    ItemStack tincture = ItemStack.EMPTY;
                    int minCount = ModItems.tincture.asItem().getDefaultMaxStackSize() + 1;

                    for (ItemStack stack : player.getInventory().getNonEquipmentItems()) {
                        if (stack.is(ModItems.tincture.asItem()) && stack.getCount() < minCount) {
                            minCount = stack.getCount();
                            tincture = stack;
                        }
                    }

                    if (tincture.isEmpty())
                        return;
                    else {
                        PotionContents contents = tincture.get(DataComponents.POTION_CONTENTS);
                        assert contents != null;
                        Optional<Holder<Potion>> potion = contents.potion();

                        assert potion.isPresent();
                        List<MobEffectInstance> allEffects = potion.get().value().getEffects().stream()
                                .map(effect -> new MobEffectInstance(
                                        effect.getEffect(),
                                        !effect.getEffect().value().isInstantaneous() ?
                                                Match(weapon).of(
                                                        Case($(_ -> weapon.is(Items.DIAMOND_SPEAR)), 70),
                                                        Case($(_ -> weapon.is(Items.STONE_SPEAR)), 50),
                                                        Case($(_ -> weapon.is(Items.GOLDEN_SPEAR)), 60),
                                                        Case($(_ -> weapon.is(Items.NETHERITE_SPEAR)), 80),
                                                        Case($(_ -> weapon.is(Items.WOODEN_SPEAR)), 40),
                                                        Case($(_ -> weapon.is(Items.IRON_SPEAR)), 60),
                                                        Case($(_ -> weapon.is(Items.COPPER_SWORD)), 60)
                                                ) : effect.getDuration(),
                                        effect.getAmplifier()))
                                .toList();

                        weapon.set(DataComponents.POTION_CONTENTS, new PotionContents(Optional.empty(), contents.customColor(), allEffects, contents.customName()));
                        tincture.setCount(tincture.count() - 1);
                        weapon.set(ModDataComponents.charges.value(), 12);
                    }
                }

                for (MobEffectInstance effect : Objects.requireNonNull(weapon.get(DataComponents.POTION_CONTENTS)).getAllEffects()) {
                    target.addEffect(new MobEffectInstance(effect));
                }
                if (!player.isCreative()) {
                    weapon.set(ModDataComponents.charges.value(), Objects.requireNonNull(weapon.get(ModDataComponents.charges.value())) - 1);
                }
            }
        }
    }
}
