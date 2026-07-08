package net.ps.weaponinfusion.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;
import net.ps.weaponinfusion.potion.ModPotions;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class TinctureItem extends PotionItem {
    public TinctureItem(Properties properties) {
        super(properties.stacksTo(16).usingConvertsTo(Items.GLASS_BOTTLE));
    }

    @Override
    public @NonNull ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        PotionContents contents = Objects.requireNonNull(stack.get(DataComponents.POTION_CONTENTS));
        contents.potion().ifPresent(potion -> {
            potion = ModPotions.getPotionMapping().getOrDefault(potion, potion);
            stack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
        });
        return stack;
    }
}
