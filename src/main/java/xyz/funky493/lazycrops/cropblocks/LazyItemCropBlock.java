package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.item.Item;

public class LazyItemCropBlock extends LazyCropBlock {
    public Item product;
    public boolean noDrop = false;

    public LazyItemCropBlock(String cropId, String seedsId, Item product, int level) {
        super(cropId, seedsId, level);
        this.product = product;
    }

    public LazyItemCropBlock(String cropId, String seedsId, Item product, int level, boolean noDrop) {
        super(cropId, seedsId, level);
        this.product = product;
        this.noDrop = noDrop;
    }
}
