package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LazyTntCropBlock extends LazySpecialCropBlock {
    public LazyTntCropBlock(String cropId, String seedsId, int level) {
        super(cropId, seedsId, Items.TNT, level);
    }

    public LazyTntCropBlock(String id, int level) {
        super(id, Items.TNT, level);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
        if ((newState.getBlock() instanceof LazyTntCropBlock && newState.get(this.getAgeProperty()) == 7 && state.get(this.getAgeProperty()) < 7)) {
            world.spawnEntity(new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), null));
        }
    }
}
