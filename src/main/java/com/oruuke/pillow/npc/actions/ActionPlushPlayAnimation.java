package com.oruuke.pillow.npc.actions;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.AnimationSlot;
import com.hypixel.hytale.server.core.entity.group.EntityGroup;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.flock.FlockMembership;
import com.hypixel.hytale.server.npc.asset.builder.BuilderSupport;
import com.hypixel.hytale.server.npc.corecomponents.ActionBase;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import com.hypixel.hytale.server.npc.role.Role;
import com.hypixel.hytale.server.npc.sensorinfo.InfoProvider;
import com.oruuke.pillow.components.PlushEmoteComponent;
import com.oruuke.pillow.npc.actions.builders.BuilderActionPlushPlayAnimation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class ActionPlushPlayAnimation extends ActionBase {

   @Nullable
   protected String animationId;

   public ActionPlushPlayAnimation(@Nonnull BuilderActionPlushPlayAnimation builderActionPlayAnimation, @Nonnull BuilderSupport support) {
      super(builderActionPlayAnimation);
      this.animationId = builderActionPlayAnimation.getAnimationId(support);
   }

   @Override
   public boolean execute(@Nonnull Ref<EntityStore> ref, @Nonnull Role role, @Nullable InfoProvider sensorInfo, double dt, @Nonnull Store<EntityStore> store) {
      super.execute(ref, role, sensorInfo, dt, store);
      NPCEntity npcComponent = store.getComponent(ref, Objects.requireNonNull(NPCEntity.getComponentType()));
      if (npcComponent == null) return false;

      FlockMembership npcMembership = store.getComponent(ref, FlockMembership.getComponentType());
      if (npcMembership == null) { return false; }

      EntityGroup group;
      Ref<EntityStore> flockReference = npcMembership.getFlockRef();

      if (flockReference == null || !flockReference.isValid()) return false;

      group = store.getComponent(flockReference, EntityGroup.getComponentType());
      if (group == null) return false;

      Ref<EntityStore> flockLeaderRef = group.getLeaderRef();
      if (flockLeaderRef == null) return false;

      PlushEmoteComponent plushEmoteComponent = store.getComponent(flockLeaderRef, PlushEmoteComponent.getComponentType());
      if (plushEmoteComponent != null && plushEmoteComponent.getEmoteId() != null) {
         npcComponent.playAnimation(ref, AnimationSlot.Action, plushEmoteComponent.getEmoteId(), store);
      } else {
         npcComponent.playAnimation(ref, AnimationSlot.Action, this.animationId, store);
      }

      return true;
   }
}
