package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;

public class LazyCropBlocks {
    public static final LazyCropBlock[] CROP_BLOCKS = new LazyCropBlock[]{
            new LazyItemCropBlock("dirt_crop", "dirt_seeds", Items.DIRT, 0),
            new LazyItemCropBlock("sand_crop", "sand_seeds", Items.SAND, 0),
            new LazyItemCropBlock("gravel_crop", "gravel_seeds", Items.GRAVEL, 0),
            new LazyItemCropBlock("cobblestone_crop", "cobblestone_seeds", Items.COBBLESTONE, 0),
            new LazyItemCropBlock("oak_log_crop", "oak_log_seeds", Items.OAK_LOG, 0),
            new LazyItemCropBlock("spruce_log_crop", "spruce_log_seeds", Items.SPRUCE_LOG, 0),
            new LazyItemCropBlock("birch_log_crop", "birch_log_seeds", Items.BIRCH_LOG, 0),
            new LazyItemCropBlock("jungle_log_crop", "jungle_log_seeds", Items.JUNGLE_LOG, 0),
            new LazyItemCropBlock("acacia_log_crop", "acacia_log_seeds", Items.ACACIA_LOG, 0),
            new LazyItemCropBlock("dark_oak_log_crop", "dark_oak_log_seeds", Items.DARK_OAK_LOG, 0),
            new LazyItemCropBlock("mangrove_log_crop", "mangrove_log_seeds", Items.MANGROVE_LOG, 0),
            new LazyItemCropBlock("cherry_log_crop", "cherry_log_seeds", Items.CHERRY_LOG, 0),
            new LazyItemCropBlock("obsidian_crop", "obsidian_seeds", Items.OBSIDIAN, 0),
            new LazyItemCropBlock("diamond_crop", "diamond_seeds", Items.DIAMOND, 2),
            new LazyItemCropBlock("egg_crop", "egg_seeds", Items.EGG, 0),
            new LazyItemCropBlock("honeycomb_crop", "honeycomb_seeds", Items.HONEYCOMB, 1),
            new LazyItemCropBlock("rotten_flesh_crop", "rotten_flesh_seeds", Items.ROTTEN_FLESH, 0),
            new LazyItemCropBlock("cake_crop", "cake_seeds", Items.CAKE, 2),
            new LazyItemCropBlock("wheat_seeds_crop", "wheat_seeds_seeds", Items.WHEAT_SEEDS, 0),
            new LazyEntityCropBlock("cow_crop", "cow_seeds", 0, EntityType.COW, Items.BEEF),
            new LazyEntityCropBlock("pig_crop", "pig_seeds", 0, EntityType.PIG, Items.PORKCHOP),
            new LazyEntityCropBlock("chicken_crop", "chicken_seeds", 0, EntityType.CHICKEN, Items.CHICKEN),
            new LazyExperienceCropBlock("experience_crop", "experience_seeds", 2),
            new LazyTntCropBlock("tnt_crop", "tnt_seeds", 0),

            //#region Metals
            new LazyItemCropBlock("iron_crop", "iron_seeds", Items.RAW_IRON, 1),
            new LazyItemCropBlock("gold_crop", "gold_seeds", Items.RAW_GOLD, 1),
            new LazyItemCropBlock("copper_crop", "copper_seeds", Items.RAW_COPPER, 1)
            //#endregion
    };

}
