package net.ps.weaponinfusion.item.custom;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;

public class TinctureItem extends PotionItem {
    public TinctureItem(Properties properties) {
        properties.stacksTo(16);
        properties.usingConvertsTo(Items.GLASS_BOTTLE);
        super(properties);
    }
}
