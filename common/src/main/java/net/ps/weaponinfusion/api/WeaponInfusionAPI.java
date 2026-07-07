package net.ps.weaponinfusion.api;

import java.lang.reflect.InvocationTargetException;

public class WeaponInfusionAPI {

    public static final String MOD_ID = "weaponinfusion";

    private static final InternalMethods __internalMethods;

    static {
        try {
            __internalMethods = (InternalMethods) Class.forName("net.ps.weaponinfusion.InternalMethodsImpl").getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
