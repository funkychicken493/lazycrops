package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.block.*;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class LazyItemCropBlock extends LazyCropBlock {
    public Item product;

    public LazyItemCropBlock(String cropId, String seedsId, Item product, int level) {
        super(cropId, seedsId, level);
        this.product = product;
    }
}
