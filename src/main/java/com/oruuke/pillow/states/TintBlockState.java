package com.oruuke.pillow.states;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.oruuke.pillow.PillowsNPlushies;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

public class TintBlockState implements Component<ChunkStore> {
    public static final BuilderCodec CODEC = BuilderCodec.builder(TintBlockState.class, TintBlockState::new).build();

    public TintBlockState() { }

    public static ComponentType getComponentType() {
        return PillowsNPlushies.get().getTintBlockComponentType();
    }

    @Nullable
    public Component<ChunkStore> clone() {
        Component state = new TintBlockState();
        Field[] tint = state.getClass().getFields();
        //System.out.println(tint);
        return state;
    }
}