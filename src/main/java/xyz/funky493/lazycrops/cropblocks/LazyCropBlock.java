package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import xyz.funky493.lazycrops.LazyCrops;

public class LazyCropBlock extends CropBlock {
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D)
    };

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[state.get(this.getAgeProperty())];
    }

    public String cropId;
    public Item seedsItem;
    public String seedsId;
    public int level;

    public LazyCropBlock(String cropId, String seedsId, int level) {
        super(Settings.copy(net.minecraft.block.Blocks.WHEAT).nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(net.minecraft.sound.BlockSoundGroup.CROP));
        this.cropId = cropId;
        this.seedsId = seedsId;
        this.seedsItem = new AliasedBlockItem(this, new Item.Settings());
        this.level = level;
    }

    public LazyCropBlock(String id, int level) {
        this(id + "_crop", id + "_seeds", level);
    }

    public ItemConvertible getSeedsItem() {
        return seedsItem;
    }
    public IntProperty getAgeProperty() {
        return AGE;
    }
    public int getLevel() {
        return level;
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        if (!world.getGameRules().getBoolean(LazyCrops.CAN_FERTILIZE_LAZYCROPS)) {
            return;
        }
        super.applyGrowth(world, pos, state);
    }
}
