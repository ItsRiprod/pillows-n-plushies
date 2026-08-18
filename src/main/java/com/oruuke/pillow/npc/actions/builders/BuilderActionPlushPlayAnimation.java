package com.oruuke.pillow.npc.actions.builders;

import com.google.gson.JsonElement;
import com.hypixel.hytale.server.npc.asset.builder.BuilderDescriptorState;
import com.hypixel.hytale.server.npc.asset.builder.BuilderSupport;
import com.hypixel.hytale.server.npc.asset.builder.holder.StringHolder;
import com.hypixel.hytale.server.npc.corecomponents.builders.BuilderActionBase;
import com.hypixel.hytale.server.npc.util.expression.ExecutionContext;
import com.hypixel.hytale.server.npc.validators.NPCLoadTimeValidationHelper;
import com.oruuke.pillow.npc.actions.ActionPlushPlayAnimation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BuilderActionPlushPlayAnimation extends BuilderActionBase {
    protected final StringHolder animationId = new StringHolder();

    public BuilderActionPlushPlayAnimation() {
    }

    public ActionPlushPlayAnimation build(@Nonnull BuilderSupport builderSupport) {
        return new ActionPlushPlayAnimation(this, builderSupport);
    }

    @Nonnull
    @Override
    public String getShortDescription() {
        return "Play an animation";
    }

    @Nonnull
    @Override
    public String getLongDescription() {
        return "Play an animation";
    }

    @Nonnull
    @Override
    public BuilderDescriptorState getBuilderDescriptorState() {
        return BuilderDescriptorState.Experimental;
    }

    @Nonnull
    public BuilderActionPlushPlayAnimation readConfig(@Nonnull JsonElement data) {
        this.getString(data, "Animation", this.animationId, null, null, BuilderDescriptorState.Stable, "The animation ID to play", null);
        return this;
    }

    @Override
    protected void runLoadTimeValidationHelper0(
            String configName, @Nonnull NPCLoadTimeValidationHelper loadTimeValidationHelper, ExecutionContext context, List<String> errors
    ) {
        loadTimeValidationHelper.validateAnimation(this.animationId.get(context));
    }

    @Nullable
    public String getAnimationId(@Nonnull BuilderSupport support) {
        String anim = this.animationId.get(support.getExecutionContext());
        return anim != null && !anim.isEmpty() ? anim : null;
    }
}
