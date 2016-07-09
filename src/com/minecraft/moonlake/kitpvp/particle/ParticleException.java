package com.minecraft.moonlake.kitpvp.particle;

/**
 * Created by MoonLake on 2016/6/8.
 */
public class ParticleException extends RuntimeException {

    public ParticleException() {

        super("The MMORPG ParticleManager Exception.");
    }

    public ParticleException(String message) {

        super(message);
    }

    public ParticleException(String message, Throwable cause) {

        super(message, cause);
    }
}
