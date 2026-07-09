package net.ps.weaponinfusion.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.alchemy.Potion;
import net.ps.weaponinfusion.WeaponInfusion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Mixin(CreativeModeTabs.class)
public abstract class DrinkTabFix {
    private DrinkTabFix() {
        /* This utility class should not be instantiated */
    }

    @WrapOperation(method = "generatePotionEffectTypes", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;"))
    private static Stream<Holder.Reference<Potion>> removeTincturePotionTypes(Stream<Holder.Reference<Potion>> instance, Predicate<? super Holder.Reference<Potion>> predicate, Operation<Stream<Holder.Reference<Potion>>> original) {
        return original.call(instance, predicate).filter(
                potion -> !potion.key().identifier().getNamespace().equals(WeaponInfusion.MOD_ID)
        );
    }

}
