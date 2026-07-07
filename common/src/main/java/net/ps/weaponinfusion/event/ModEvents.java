package net.ps.weaponinfusion.event;

import net.blay09.mods.balm.platform.event.callback.PlayerCallback;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.ps.weaponinfusion.data.ModDataComponents;
import net.ps.weaponinfusion.item.ModItems;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.vavr.API.*;

public class ModEvents {
    private ModEvents() {}

    public static void initialize() {
        PlayerCallback.Attack.Before.EVENT.register((player, rawTarget) -> {
            onAttack(player, rawTarget);
            return true;
        });
    }

    private static void onAttack(Player player, Entity rawTarget) {
        ItemStack weapon = player.getWeaponItem();

        Integer charges = Objects.requireNonNull(weapon.get(ModDataComponents.charges.value()));
        PotionContents contents = Objects.requireNonNull(weapon.get(DataComponents.POTION_CONTENTS));

        if (rawTarget instanceof LivingEntity target && weapon.has(DataComponents.WEAPON)) {
            if (charges.equals(0)) {
                ItemStack tincture = getTincture(player);

                if (tincture.isEmpty())
                    return;

                Optional<Holder<Potion>> potion = contents.potion();

                assert potion.isPresent();
                List<MobEffectInstance> allEffects = potion.get().value().getEffects().stream()
                        .map(effect -> new MobEffectInstance(
                                effect.getEffect(),
                                !effect.getEffect().value().isInstantaneous() ?
                                        Match(weapon).of(
                                                Case($(_ -> weapon.is(ItemTags.SWORDS)), 60),
                                                Case($(_ -> weapon.is(ItemTags.AXES)), 100),
                                                Case($(_ -> weapon.is(ItemTags.MACE_ENCHANTABLE)), 120),
                                                Case($(_ -> weapon.is(ItemTags.TRIDENT_ENCHANTABLE)), 80)
                                        ) : effect.getDuration(),
                                effect.getAmplifier()))
                        .toList();

                weapon.set(DataComponents.POTION_CONTENTS, new PotionContents(Optional.empty(), contents.customColor(), allEffects, contents.customName()));
                tincture.setCount(tincture.count() - 1);
                weapon.set(ModDataComponents.charges.value(), 12);
            }

            for (MobEffectInstance effect : contents.getAllEffects()) {
                target.addEffect(new MobEffectInstance(effect));
            }
            if (!player.isCreative()) {
                weapon.set(ModDataComponents.charges.value(), charges - 1);
            }
        }
    }

    private static ItemStack getTincture(Player player) {
        ItemStack tincture = ItemStack.EMPTY;
        int minCount = ModItems.tincture.asItem().getDefaultMaxStackSize() + 1;

        for (ItemStack stack : player.getInventory().getNonEquipmentItems()) {
            if (stack.is(ModItems.tincture.asItem()) && stack.getCount() < minCount) {
                minCount = stack.getCount();
                tincture = stack;
            }
        }

        return tincture;
    }
}
