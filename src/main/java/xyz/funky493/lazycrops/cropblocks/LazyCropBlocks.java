package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;

public class LazyCropBlocks {
    public static final LazyCropBlock[] CROP_BLOCKS = new LazyCropBlock[]{
    new LazyItemCropBlock("dirt", Items.DIRT, 0),
    new LazyItemCropBlock("sand", Items.SAND, 0),
    new LazyItemCropBlock("gravel", Items.GRAVEL, 0),
    new LazyItemCropBlock("cobblestone", Items.COBBLESTONE, 0),
    new LazyItemCropBlock("oak_log", Items.OAK_LOG, 0),
    new LazyItemCropBlock("birch_log", Items.BIRCH_LOG, 0),
    new LazyItemCropBlock("spruce_log", Items.SPRUCE_LOG, 0),
    new LazyItemCropBlock("jungle_log", Items.JUNGLE_LOG, 0),
    new LazyItemCropBlock("acacia_log", Items.ACACIA_LOG, 0),
    new LazyItemCropBlock("dark_oak_log", Items.DARK_OAK_LOG, 0),
    new LazyItemCropBlock("mangrove_log", Items.MANGROVE_LOG, 0),
    new LazyItemCropBlock("cherry_log", Items.CHERRY_LOG, 0),
    new LazyItemCropBlock("obsidian", Items.OBSIDIAN, 0),
    new LazyItemCropBlock("diamond", Items.DIAMOND, 2),
    new LazyItemCropBlock("egg", Items.EGG, 0),
    new LazyItemCropBlock("honeycomb", Items.HONEYCOMB, 1),
    new LazyItemCropBlock("rotten_flesh", Items.ROTTEN_FLESH, 0),
    new LazyItemCropBlock("cake", Items.CAKE, 2),
    new LazyItemCropBlock("wheat_seeds", Items.WHEAT_SEEDS, 0),
    new LazyEntityCropBlock("cow", 0, EntityType.COW, Items.BEEF),
    new LazyEntityCropBlock("pig", 0, EntityType.PIG, Items.PORKCHOP),
    new LazyEntityCropBlock("chicken", 0, EntityType.CHICKEN, Items.CHICKEN),
    new LazyExperienceCropBlock("experience", 2),
    new LazyTntCropBlock("tnt", 0),

    //#region Metals
    new LazyItemCropBlock("iron", Items.RAW_IRON, 1),
    new LazyItemCropBlock("gold", Items.RAW_GOLD, 1),
    new LazyItemCropBlock("copper",  Items.RAW_COPPER, 1)
    //#endregion
    };

}
