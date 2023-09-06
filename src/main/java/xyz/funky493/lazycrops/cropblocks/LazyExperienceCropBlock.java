package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LazyExperienceCropBlock extends LazyItemCropBlock {
    public LazyExperienceCropBlock(String cropId, String seedsId, int level) {
        super(cropId, seedsId, Items.EXPERIENCE_BOTTLE, level, true);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
        if ((!(newState.getBlock() instanceof LazyExperienceCropBlock) && state.get(this.getAgeProperty()) == 7) || (newState.getBlock() instanceof LazyExperienceCropBlock && newState.get(this.getAgeProperty()) == 1) && state.get(this.getAgeProperty()) == 7) {
            world.spawnEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY(), pos.getZ(), 3));
        }
    }
}
