package com.oruuke.pillow;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.event.EventPriority;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.asset.AssetPackRegisterEvent;
import com.hypixel.hytale.server.core.asset.AssetPackUnregisterEvent;
import com.hypixel.hytale.server.core.asset.LoadAssetEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.oruuke.pillow.states.TintBlockState;
import com.riprod.patchly.PatchManager;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

public final class PillowsNPlushies extends JavaPlugin {
    private final PatchManager patchManager;
    private static PillowsNPlushies instance;
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private ComponentType<ChunkStore, TintBlockState> tintBlockComponentType;

    public static PillowsNPlushies get() {
        return instance;
    }

    public PillowsNPlushies(@Nonnull JavaPluginInit init) {
        super(init);
        this.patchManager = new PatchManager(this.getManifest());
        //LOGGER.atInfo().log("welcome to " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    @Override public CompletableFuture<Void> preLoad() {
        patchManager.preLoad();
        return super.preLoad();
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

        getEventRegistry().register(EventPriority.LAST, LoadAssetEvent.class,
                e -> patchManager.rebuildAndApply("boot"));
        getEventRegistry().register(AssetPackRegisterEvent.class, e -> {
            if (PatchManager.isSyntheticOverridePack(e.getAssetPack().getName())) return;
            patchManager.rebuildAndApply("packRegister:" + e.getAssetPack().getName());
        });
        getEventRegistry().register(AssetPackUnregisterEvent.class, e -> {
            if (PatchManager.isSyntheticOverridePack(e.getAssetPack().getName())) return;
            patchManager.rebuildAndApply("packUnregister:" + e.getAssetPack().getName());
        });

        //this.getCodecRegistry(Interaction.CODEC).register("Tint_Interaction", TintInteraction.class, TintInteraction.CODEC);
        //this.tintBlockComponentType = this.getChunkStoreRegistry().registerComponent(TintBlockState.class, "TintBlockState", TintBlockState.CODEC);
    }

    @Override
    protected void shutdown() {
        LOGGER.at(Level.INFO).log("shutting down pillows n' plushies!");
        patchManager.shutdown();
    }

    public ComponentType<ChunkStore, TintBlockState> getTintBlockComponentType() {
        return this.tintBlockComponentType;
    }
}
