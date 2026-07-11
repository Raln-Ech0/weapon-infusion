package net.ps.weaponinfusion.sound;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import org.jspecify.annotations.NonNull;

public class ModSoundEvents {
    private ModSoundEvents() {
        /* This utility class should not be instantiated */
    }

    public static Holder<SoundEvent> sandingShort;

    public static void initialize(BalmRegistrar.@NonNull Scoped<SoundEvent> soundEvents) {
        sandingShort = soundEvents.register("sanding_short", SoundEvent::createVariableRangeEvent);
    }
}
