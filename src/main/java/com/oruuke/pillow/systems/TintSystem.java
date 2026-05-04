package com.oruuke.pillow.systems;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.math.util.ChunkUtil;
import com.hypixel.hytale.server.core.asset.type.blocktick.BlockTickStrategy;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.chunk.BlockComponentChunk;
import com.hypixel.hytale.server.core.universe.world.chunk.WorldChunk;
import com.hypixel.hytale.server.core.universe.world.chunk.section.BlockSection;
import com.hypixel.hytale.server.core.universe.world.chunk.section.ChunkSection;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.oruuke.pillow.states.TintBlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TintSystem extends EntityTickingSystem {

    public void tick(float dt, int index, @Nonnull ArchetypeChunk archetypeChunk, @Nonnull Store store, @Nonnull CommandBuffer commandBuffer) {
        BlockSection blocks = (BlockSection) archetypeChunk.getComponent(index, BlockSection.getComponentType());

        assert blocks != null;

        if (blocks.getTickingBlocksCountCopy() != 0) {
            ChunkSection section = (ChunkSection) archetypeChunk.getComponent(index, ChunkSection.getComponentType());

            assert section != null;

            BlockComponentChunk blockComponentChunk = (BlockComponentChunk) commandBuffer.getComponent(section.getChunkColumnReference(), BlockComponentChunk.getComponentType());

            assert blockComponentChunk != null;

            blocks.forEachTicking(blockComponentChunk, commandBuffer, section.getY(), (blockComponentChunk1, commandBuffer1, localX, localY, localZ, blockId) -> {
                Ref<ChunkStore> blockRef = blockComponentChunk.getEntityReference(ChunkUtil.indexBlockInColumn(localX, localY, localZ));
                if (blockRef == null) {
                    return BlockTickStrategy.IGNORED;
                } else {
                    TintBlockState tintBlockState = (TintBlockState) commandBuffer1.getComponent(blockRef, TintBlockState.getComponentType());
                    if (tintBlockState != null) {
                        WorldChunk worldChunk = (WorldChunk) commandBuffer.getComponent(section.getChunkColumnReference(), WorldChunk.getComponentType());
                        World world = worldChunk.getWorld();

                        int globalX = localX + (worldChunk.getX() * 32);
                        int globalZ = localZ + (worldChunk.getZ() * 32);

                        // We need to execute setBlock on the world thread as you cannot call store functions from a system
                        // This is because of the architecture of the server, depending on your needs you can also use the CommandBuffer
                        world.execute(() -> {
                            world.setBlock(globalX + 1, localY, globalZ, "Rock_Ice");
                        });

                        return BlockTickStrategy.CONTINUE;

                    } else {
                        return BlockTickStrategy.IGNORED;
                    }
                }
            });
        }
    }

    @Nullable
    public Query getQuery() {
        return Query.and(BlockSection.getComponentType(), ChunkSection.getComponentType());
    }
}