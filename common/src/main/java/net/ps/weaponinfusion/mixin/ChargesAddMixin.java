package net.ps.weaponinfusion.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.ps.weaponinfusion.data.ModDataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ChargesAddMixin {
    @WrapOperation(method = "addDetailsToTooltip", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    private void addChargesTooltip(Consumer<Component> builder, Object component0, Operation<Void> original) {
        ItemStack weapon = (ItemStack) (Object)this;
        String id = BuiltInRegistries.ITEM.getKey(weapon.getItem()).toString();

        MutableComponent component = (MutableComponent) component0;

        if (Objects.equals(component.getString(), id) && (weapon.is(ItemTags.SWORDS) ||
                            weapon.is(ItemTags.AXES) ||
                            weapon.is(ItemTags.SPEARS) ||
                            weapon.is(ItemTags.MACE_ENCHANTABLE) ||
                            weapon.is(ItemTags.TRIDENT_ENCHANTABLE))
            ) {
                builder.accept(Component.translatable("item.charges", Objects.requireNonNull(weapon.get(ModDataComponents.INSTANCE.getCharges().value()))));
            }


        original.call(builder, component);
    }
}
