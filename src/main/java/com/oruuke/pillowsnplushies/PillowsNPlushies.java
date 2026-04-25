package com.oruuke.pillowsnplushies;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import java.util.logging.Level;

public class PillowsNPlushies extends JavaPlugin {

    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public PillowsNPlushies(JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void start() {
        LOGGER.at(Level.INFO).log("Starting Pillows n' Plushies!");
    }

    @Override
    protected void setup() {
        LOGGER.at(Level.INFO).log("Setting up Pillows n' Plushies!");
    }

    @Override
    protected void shutdown() {
        LOGGER.at(Level.INFO).log("Shutting down Pillows n' Plushies!");
    }
}
