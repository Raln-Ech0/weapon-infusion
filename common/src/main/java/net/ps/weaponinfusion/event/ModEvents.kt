package net.ps.weaponinfusion.event;

import net.blay09.mods.balm.platform.event.callback.PlayerCallback;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.ps.weaponinfusion.data.ModDataComponents;
import net.ps.weaponinfusion.item.ModItems;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ModEvents {
    private ModEvents() {
        /* This utility class should not be instantiated */
    }

    public static void initialize() {
        PlayerCallback.Attack.Before.EVENT.register((player, target) -> {
            onAttack(player, target);
            return true;
        });
    }

    public static void onAttack(Player player, Entity rawTarget) {
        ItemStack weapon = Objects.requireNonNull(player.getWeaponItem());

        if (rawTarget instanceof LivingEntity target && weapon.has(DataComponents.WEAPON)) {
            Integer charges = Objects.requireNonNull(weapon.get(ModDataComponents.charges.value()));

            if (charges.equals(0)) {
                ItemStack tincture = getTincture(player);

                if (tincture.isEmpty()) return;

                PotionContents tinctureContents = Objects.requireNonNull(tincture.get(DataComponents.POTION_CONTENTS));

                modifyDuration(tinctureContents, weapon);

                if (!player.isCreative()) tincture.setCount(tincture.count() - 1);
                weapon.set(ModDataComponents.charges.value(), 12);
            }

            PotionContents contents = Objects.requireNonNull(weapon.get(DataComponents.POTION_CONTENTS));
            charges = Objects.requireNonNull(weapon.get(ModDataComponents.charges.value()));

            for (MobEffectInstance effect : contents.getAllEffects()) {
                target.addEffect(new MobEffectInstance(effect));
            }
            if (!player.isCreative()) weapon.set(ModDataComponents.charges.value(), charges - 1);
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

    private static void modifyDuration(PotionContents initialContents, ItemStack weapon)  {
        initialContents.potion().ifPresent(p -> {
            int duration = switch(weapon) {
                case ItemStack v when v.is(ItemTags.SWORDS) -> 60;
                case ItemStack v when v.is(ItemTags.AXES) -> 100;
                case ItemStack v when v.is(ItemTags.MACE_ENCHANTABLE) -> 120;
                case ItemStack v when v.is(ItemTags.TRIDENT_ENCHANTABLE) -> 80;

                case ItemStack v when v.is(ItemTags.SPEARS) -> switch (weapon) {
                    case ItemStack i when i.is(Items.DIAMOND_SPEAR) -> 70;
                    case ItemStack i when i.is(Items.STONE_SPEAR) -> 50;
                    case ItemStack i when i.is(Items.GOLDEN_SPEAR) -> 60;
                    case ItemStack i when i.is(Items.NETHERITE_SPEAR) -> 80;
                    case ItemStack i when i.is(Items.WOODEN_SPEAR) -> 40;
                    case ItemStack i when i.is(Items.IRON_SPEAR) -> 60;
                    case ItemStack i when i.is(Items.COPPER_SWORD) -> 60;

                    default -> throw new IllegalStateException("Unexpected value: " + weapon);
                };

                default -> throw new IllegalStateException("Unexpected value: " + weapon);
            };

            List<MobEffectInstance> allEffects = p.value().getEffects().stream()
                    .map(effect -> new MobEffectInstance(
                            effect.getEffect(),
                            effect.getEffect().value().isInstantaneous() ? effect.getDuration() : duration,
                            effect.getAmplifier()))
                    .toList();

            PotionContents contents = new PotionContents(Optional.empty(),
                    initialContents.customColor(), allEffects, initialContents.customName());

            weapon.set(DataComponents.POTION_CONTENTS, contents);
        });

    }
}
