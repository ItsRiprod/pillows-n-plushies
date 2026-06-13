package com.oruuke.pillow.command;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

// Command that can be run that lists the server rules in chat to the player
public class PillowCommand extends AbstractAsyncCommand {

    // Constructor
    public PillowCommand() {
        // super(<The command>, <Command description>)
        super("pillow", "pillowing");
    }

    // Run the command
    // context - info about who ran the command. Server console?, Some player?
    @Override
    protected CompletableFuture<Void> executeAsync(@Nonnull CommandContext context) {
        context.sendMessage(Message.raw("pillow."));
        return CompletableFuture.completedFuture(null);
    }
}
