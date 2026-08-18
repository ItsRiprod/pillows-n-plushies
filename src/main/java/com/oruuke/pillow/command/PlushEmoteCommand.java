package com.oruuke.pillow.command;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.oruuke.pillow.components.PlushEmoteComponent;

import javax.annotation.Nonnull;

public class PlushEmoteCommand extends AbstractPlayerCommand {
    private final RequiredArg<String> emoteId;

    public PlushEmoteCommand() {
        super("plushemote", "Save emoteId");
        this.emoteId = this.withRequiredArg("emoteId", "Emote Id", ArgTypes.STRING);
    }

    @Override
    protected void execute(
            @Nonnull CommandContext context,
            @Nonnull Store<EntityStore> store,
            @Nonnull Ref<EntityStore> ref,
            @Nonnull PlayerRef playerRef,
            @Nonnull World world
    ) {
        PlushEmoteComponent plushEmoteComponent = store.getComponent(ref, PlushEmoteComponent.getComponentType());
        if (plushEmoteComponent == null) return;

        plushEmoteComponent.setEmoteId(emoteId.get(context));
    }
}
