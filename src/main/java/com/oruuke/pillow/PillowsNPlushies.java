package com.oruuke.pillow;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.riprod.patchly.PatchManager;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public final class PillowsNPlushies extends JavaPlugin {
    private final PatchManager patchManager;
    private static PillowsNPlushies instance;
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public PillowsNPlushies(@Nonnull JavaPluginInit init) {
        super(init);
        patchManager = new PatchManager(this);
        //LOGGER.atInfo().log("welcome to " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    @Override
    public java.util.concurrent.CompletableFuture<Void> preLoad() {
        return super.preLoad();
    }

    @Override
    protected void setup() {
        patchManager.install();
    }

    @Override
    protected void start() {
        instance = this;
        LOGGER.at(Level.INFO).log("starting pillows n' plushies!");
    }

    public static PillowsNPlushies get() {
        return instance;
    }

    @Override
    protected void shutdown() {
        LOGGER.at(Level.INFO).log("shutting down pillows n' plushies!");
        patchManager.shutdown();
    }
}
