package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// Class specifically for when the crop doesn't drop its product when broken
// Used for special cases like the TNT crop
public class LazySpecialCropBlock extends LazyItemCropBlock {
    public LazySpecialCropBlock(String cropId, String seedsId, Item product, int level) {
        super(cropId, seedsId, product, level, true);
    }

    public LazySpecialCropBlock(String id, Item product, int level) {
        super(id, product, level, true);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
