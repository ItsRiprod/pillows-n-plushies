package com.oruuke.pillow.states;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.oruuke.pillow.Pillow;

import javax.annotation.Nullable;

public class TintBlockState implements Component<ChunkStore> {
    public static final BuilderCodec CODEC = BuilderCodec.builder(TintBlockState.class, TintBlockState::new).build();

    public TintBlockState() { }

    public static ComponentType getComponentType() {
        return Pillow.get().getTintBlockComponentType();
    }

    @Nullable
    public Component<ChunkStore> clone() {
        return new TintBlockState();
    }
}