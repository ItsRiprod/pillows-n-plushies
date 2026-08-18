package com.oruuke.pillow.components;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class PlushEmoteComponent implements Component<EntityStore> {
    private String emoteId = "Breakdance";

    private static ComponentType<EntityStore, PlushEmoteComponent> TYPE;

    public static void setComponentType(ComponentType<EntityStore, PlushEmoteComponent> type) {
        TYPE = type;
    }

    public static ComponentType<EntityStore, PlushEmoteComponent> getComponentType() {
        return TYPE;
    }

    public static final BuilderCodec<PlushEmoteComponent> CODEC = BuilderCodec
            .builder(PlushEmoteComponent.class, PlushEmoteComponent::new)
            .append(
                    new KeyedCodec<>("EmoteId",  Codec.STRING),
                    (component, value) -> component.emoteId = value,
                    component -> component.emoteId
            ).add()
            .build();

    public PlushEmoteComponent(){}

    public PlushEmoteComponent(String emoteId) {
        this.emoteId = emoteId;
    }

    public String getEmoteId() {
        return emoteId;
    }

    public void setEmoteId(String emoteId) {
        this.emoteId = emoteId;
    }

    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        return new PlushEmoteComponent(this.emoteId);
    }
}
