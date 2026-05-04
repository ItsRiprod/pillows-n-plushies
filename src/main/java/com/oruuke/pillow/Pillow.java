package com.oruuke.pillow;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.oruuke.pillow.Initializers.TintInitializer;
import com.oruuke.pillow.states.TintBlockState;
import com.oruuke.pillow.systems.TintSystem;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class Pillow extends JavaPlugin {
    protected static Pillow instance;
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private ComponentType tintBlockComponentType;

    public static Pillow get() {
        return instance;
    }

    public Pillow(@Nonnull JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("welcome to " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    @Override
    protected void start() {
        LOGGER.at(Level.INFO).log("Starting pillows n' plushies!");
        this.getChunkStoreRegistry().registerSystem(new TintSystem());
        this.getChunkStoreRegistry().registerSystem(new TintInitializer());
    }
    @Override
    protected void setup() {
        LOGGER.at(Level.INFO).log("setting up pillows n' plushies!");
        this.tintBlockComponentType = this.getChunkStoreRegistry().registerComponent(TintBlockState.class, "TintBlockState", TintBlockState.CODEC);
    }
    @Override
    protected void shutdown() {
        LOGGER.at(Level.INFO).log("shutting down pillows n' plushies!");
    }

    public ComponentType getTintBlockComponentType() {
        return this.tintBlockComponentType;
    }
}
