package net.ps.weaponinfusion.sound

import net.blay09.mods.balm.core.BalmRegistrar
import net.minecraft.core.Holder
import net.minecraft.sounds.SoundEvent

object ModSoundEvents {
    lateinit var sandingShort: Holder<SoundEvent>

    fun initialize(soundEvents: BalmRegistrar.Scoped<SoundEvent>) {
        sandingShort = soundEvents.register("sanding_short",
            SoundEvent::createVariableRangeEvent)
    }
}
