package net.ps.weaponinfusion.data;

import com.mojang.serialization.Codec;
import net.blay09.mods.balm.core.component.BalmDataComponentTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;

public class ModDataComponents {
    private ModDataComponents() {
        /* This utility class should not be instantiated */
    }

    public static Holder<DataComponentType<Integer>> charges;

    public static void initialize(BalmDataComponentTypeRegistrar dataComponentTypes) {
        charges = dataComponentTypes.register("charges", Codec.INT, ByteBufCodecs.INT).asHolder();
    }
}
