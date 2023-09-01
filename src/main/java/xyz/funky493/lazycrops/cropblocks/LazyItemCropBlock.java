package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.item.Item;

public class LazyItemCropBlock extends LazyCropBlock {
    public Item product;

    public LazyItemCropBlock(String cropId, String seedsId, Item product, int level) {
        super(cropId, seedsId, level);
        this.product = product;
    }
}
