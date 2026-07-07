package net.ps.weaponinfusion.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.ps.weaponinfusion.item.ModItems;
import net.ps.weaponinfusion.potion.ModPotions;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.vavr.API.*;

@Mixin(ItemStack.class)
public abstract class TinctureInitMixin {
    @Shadow
    @Final
    private PatchedDataComponentMap components;

    @Shadow
    public abstract <T> T set(DataComponentType<T> type, @Nullable T value);

    @Inject(method = "<init>(Lnet/minecraft/core/Holder;ILnet/minecraft/core/component/PatchedDataComponentMap;)V", at = @At(value = "TAIL"))
    public void init(Holder<Item> item, int count, PatchedDataComponentMap components, CallbackInfo ci) {
        ItemStack stack = (ItemStack) (Object)this;

        if (stack.is(ModItems.tincture.asItem())) {
            PotionContents contents = this.components.get(DataComponents.POTION_CONTENTS);
            assert contents != null;
            Holder<Potion> potion = contents.potion().get();

            if (potion != null) {
                potion = Match(potion).of(
                        Case($(p -> p == Potions.NIGHT_VISION), ModPotions.nightVision),
                        Case($(p -> p == Potions.LONG_NIGHT_VISION), ModPotions.longNightVision),

                        Case($(p -> p == Potions.INVISIBILITY), ModPotions.invisibility),
                        Case($(p -> p == Potions.LONG_INVISIBILITY), ModPotions.longInvisibility),

                        Case($(p -> p == Potions.LEAPING), ModPotions.leaping),
                        Case($(p -> p == Potions.LONG_LEAPING), ModPotions.longLeaping),
                        Case($(p -> p == Potions.STRONG_LEAPING), ModPotions.strongLeaping),

                        Case($(p -> p == Potions.FIRE_RESISTANCE), ModPotions.fireResistance),
                        Case($(p -> p == Potions.LONG_FIRE_RESISTANCE), ModPotions.longFireResistance),

                        Case($(p -> p == Potions.SWIFTNESS), ModPotions.swiftness),
                        Case($(p -> p == Potions.LONG_SWIFTNESS), ModPotions.longSwiftness),
                        Case($(p -> p == Potions.STRONG_SWIFTNESS), ModPotions.strongSwiftness),

                        Case($(p -> p == Potions.SLOWNESS), ModPotions.slowness),
                        Case($(p -> p == Potions.LONG_SLOWNESS), ModPotions.longSlowness),
                        Case($(p -> p == Potions.STRONG_SLOWNESS), ModPotions.strongSlowness),

                        Case($(p -> p == Potions.TURTLE_MASTER), ModPotions.turtleMaster),
                        Case($(p -> p == Potions.LONG_TURTLE_MASTER), ModPotions.longTurtleMaster),
                        Case($(p -> p == Potions.STRONG_TURTLE_MASTER), ModPotions.strongTurtleMaster),

                        Case($(p -> p == Potions.WATER_BREATHING), ModPotions.waterBreathing),
                        Case($(p -> p == Potions.LONG_WATER_BREATHING), ModPotions.longWaterBreathing),

                        Case($(p -> p == Potions.HEALING), ModPotions.healing),
                        Case($(p -> p == Potions.STRONG_HEALING), ModPotions.strongHealing),

                        Case($(p -> p == Potions.HARMING), ModPotions.harming),
                        Case($(p -> p == Potions.STRONG_HARMING), ModPotions.strongHarming),

                        Case($(p -> p == Potions.POISON), ModPotions.poison),
                        Case($(p -> p == Potions.LONG_POISON), ModPotions.longPoison),
                        Case($(p -> p == Potions.STRONG_POISON), ModPotions.strongPoison),

                        Case($(p -> p == Potions.REGENERATION), ModPotions.regeneration),
                        Case($(p -> p == Potions.LONG_REGENERATION), ModPotions.longRegeneration),
                        Case($(p -> p == Potions.STRONG_REGENERATION), ModPotions.strongRegeneration),

                        Case($(p -> p == Potions.STRENGTH), ModPotions.strength),
                        Case($(p -> p == Potions.LONG_STRENGTH), ModPotions.longStrength),
                        Case($(p -> p == Potions.STRONG_STRENGTH), ModPotions.strongStrength),

                        Case($(p -> p == Potions.WEAKNESS), ModPotions.weakness),
                        Case($(p -> p == Potions.LONG_WEAKNESS), ModPotions.longWeakness),

                        Case($(p -> p == Potions.LUCK), ModPotions.luck),

                        Case($(p -> p == Potions.SLOW_FALLING), ModPotions.slowFalling),
                        Case($(p -> p == Potions.LONG_SLOW_FALLING), ModPotions.longSlowFalling),

                        Case($(p -> p == Potions.WIND_CHARGED), ModPotions.windCharged),
                        Case($(p -> p == Potions.WEAVING), ModPotions.weaving),
                        Case($(p -> p == Potions.OOZING), ModPotions.oozing),
                        Case($(p -> p == Potions.INFESTED), ModPotions.infested),

                        Case($(), potion)
                );
            }

            this.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
        }
    }
}
