package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LazyTntCropBlock extends LazyItemCropBlock {


    public LazyTntCropBlock(String cropId, String seedsId, int level) {
        super(cropId, seedsId, Items.TNT, level);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
        if ((newState.getBlock() instanceof LazyTntCropBlock && newState.get(this.getAgeProperty()) == 7 && state.get(this.getAgeProperty()) == 6)) {
            world.spawnEntity(new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), null));
        }
    }
}
