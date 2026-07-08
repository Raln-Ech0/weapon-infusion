package net.ps.weaponinfusion.item;

import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.component.Consumables;
import net.ps.weaponinfusion.WeaponInfusion;
import net.ps.weaponinfusion.item.custom.TinctureItem;
import org.jspecify.annotations.NonNull;

import static net.ps.weaponinfusion.WeaponInfusion.id;

public class ModItems {
    private ModItems() {
        /* This utility class should not be instantiated */
    }

    public static DeferredItem tincture;

    public static void initialize(@NonNull BalmItemRegistrar items) {
        tincture = items.register("tincture", TinctureItem::new, properties ->  properties
                .component(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER))
                .component(DataComponents.CONSUMABLE, Consumables.defaultDrink().consumeSeconds(0.8F).build())
        ).asDeferredItem();
    }

    public static void initialize(@NonNull BalmCreativeModeTabRegistrar creativeModeTabs) {
        creativeModeTabs.register(WeaponInfusion.MOD_ID, builder ->
                builder.title(Component.translatable(id("tincture_items").toLanguageKey("itemGroup")))
                        .icon(() -> {
                            ItemStack stack = tincture.createStack();
                            stack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.POISON));
                            return stack;
                        })
                        .displayItems((displayParameters, output) ->
                                generatePotionEffectTypes(displayParameters, output, tincture.asItem()))
        );
    }

    private static void generatePotionEffectTypes(CreativeModeTab.ItemDisplayParameters displayParameters, CreativeModeTab.Output output, Item item) {
        displayParameters.holders().lookup(Registries.POTION).ifPresent(potions ->
                potions.listElements()
                        .filter(potion -> potion.key().identifier().getNamespace().equals(WeaponInfusion.MOD_ID)
                                || potion.value().getEffects().toArray().length == 0)
                        .filter(potion -> potion.value().isEnabled(displayParameters.enabledFeatures()))
                        .map(potion -> PotionContents.createItemStack(item, potion))
                        .forEach(output::accept));
    }
}
