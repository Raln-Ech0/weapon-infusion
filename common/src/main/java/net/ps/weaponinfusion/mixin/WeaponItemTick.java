package net.ps.weaponinfusion.mixin;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.ps.weaponinfusion.data.ModDataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(Item.class)
public abstract class WeaponItemTick {
    @Inject(method = "inventoryTick", at = @At(value = "HEAD"))
    private void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, EquipmentSlot slot, CallbackInfo ci) {
        if (itemStack.has(DataComponents.WEAPON) && Objects.requireNonNull(itemStack.get(ModDataComponents.charges.value())).equals(0)) {
                itemStack.set(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        }
    }
}
