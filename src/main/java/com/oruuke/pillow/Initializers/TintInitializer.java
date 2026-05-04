package com.oruuke.pillow.Initializers;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.RefSystem;
import com.hypixel.hytale.math.util.ChunkUtil;
import com.hypixel.hytale.server.core.modules.block.BlockModule;
import com.hypixel.hytale.server.core.universe.world.chunk.WorldChunk;
import com.oruuke.pillow.Pillow;
import com.oruuke.pillow.states.TintBlockState;

import javax.annotation.Nonnull;

public class TintInitializer extends RefSystem {

    @Override
    public void onEntityAdded(@Nonnull Ref ref, @Nonnull AddReason reason, @Nonnull Store store, @Nonnull CommandBuffer commandBuffer) {
        BlockModule.BlockStateInfo info = (BlockModule.BlockStateInfo) commandBuffer.getComponent(ref, BlockModule.BlockStateInfo.getComponentType());
        if (info == null) return;

        TintBlockState generator = (TintBlockState) commandBuffer.getComponent(ref, Pillow.get().getTintBlockComponentType());
        if (generator != null) {
            int x = ChunkUtil.xFromBlockInColumn(info.getIndex());
            int y = ChunkUtil.yFromBlockInColumn(info.getIndex());
            int z = ChunkUtil.zFromBlockInColumn(info.getIndex());

            WorldChunk worldChunk = (WorldChunk) commandBuffer.getComponent(info.getChunkRef(), WorldChunk.getComponentType());
            if (worldChunk != null) {
                worldChunk.setTicking(x, y, z, true);
            }
        }
    }

    @Override
    public void onEntityRemove(@Nonnull Ref ref, @Nonnull RemoveReason reason, @Nonnull Store store, @Nonnull CommandBuffer commandBuffer) {
    }

    @Override
    public Query getQuery() {
        return Query.and(BlockModule.BlockStateInfo.getComponentType(), Pillow.get().getTintBlockComponentType());
    }
}