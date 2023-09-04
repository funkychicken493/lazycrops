package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LazyEntityCropBlock extends LazyCropBlock {

    public EntityType product;
    public Item craftItem;

    public LazyEntityCropBlock(String cropId, String seedsId, int level, EntityType product, Item craftItem) {
        super(cropId, seedsId, level);
        this.product = product;
        this.craftItem = craftItem;
    }

    public void summonEntity(ServerWorld world, BlockPos pos, BlockState state) {
        if (!world.isClient() && state.get(this.getAgeProperty()) == 7) {
            product.spawn(world, pos, SpawnReason.CONVERSION);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
        if (!(newState.getBlock() instanceof LazyCropBlock) || (newState.getBlock() instanceof LazyEntityCropBlock && newState.get(this.getAgeProperty()) == 1)) {
            summonEntity((ServerWorld) world, pos, state);
        }
    }
}
