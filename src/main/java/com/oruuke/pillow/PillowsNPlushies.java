package com.oruuke.pillow;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.oruuke.pillow.states.TintBlockState;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class PillowsNPlushies extends JavaPlugin {
    private static PillowsNPlushies instance;
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private ComponentType<ChunkStore, TintBlockState> tintBlockComponentType;

    public static PillowsNPlushies get() {
        return instance;
    }

    public PillowsNPlushies(@Nonnull JavaPluginInit init) {
        super(init);
        //LOGGER.atInfo().log("welcome to " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    @Override
    protected void start() {
        LOGGER.at(Level.INFO).log("starting pillows n' plushies!");
        //this.getChunkStoreRegistry().registerSystem(new TintSystem());
        //this.getChunkStoreRegistry().registerSystem(new TintInitializer());
    }
    @Override
    protected void setup() {
        instance = this;
        LOGGER.at(Level.INFO).log("setting up pillows n' plushies!");
        //this.getCodecRegistry(Interaction.CODEC).register("Tint_Interaction", TintInteraction.class, TintInteraction.CODEC);
        //this.tintBlockComponentType = this.getChunkStoreRegistry().registerComponent(TintBlockState.class, "TintBlockState", TintBlockState.CODEC);
    }
    @Override
    protected void shutdown() {
        LOGGER.at(Level.INFO).log("shutting down pillows n' plushies!");
    }

    public ComponentType<ChunkStore, TintBlockState> getTintBlockComponentType() {
        return this.tintBlockComponentType;
    }
}
