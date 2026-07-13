package net.ps.weaponinfusion.data

import com.mojang.serialization.Codec
import net.blay09.mods.balm.core.component.BalmDataComponentTypeRegistrar
import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponentType
import net.minecraft.network.codec.ByteBufCodecs
import net.ps.weaponinfusion.item.custom.SandPaperItem

object ModDataComponents {
    lateinit var charges: Holder<DataComponentType<Int>>
    lateinit var sandPaperPolishing: Holder<DataComponentType<SandPaperItem.SandPaperItemComponent>>

    fun initialize(dataComponentTypes: BalmDataComponentTypeRegistrar) {
        charges = dataComponentTypes.register("charges", Codec.INT, ByteBufCodecs.INT).asHolder()
        sandPaperPolishing = dataComponentTypes.register(
            "sand_paper_polishing",
            SandPaperItem.SandPaperItemComponent.codec,
            SandPaperItem.SandPaperItemComponent.streamCodec
        ).asHolder()
    }
}
