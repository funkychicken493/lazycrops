package xyz.funky493.lazycrops.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class LazyBlocks {
    public static final InvincibleFarmland INVINCIBLE_FARMLAND = new InvincibleFarmland(AbstractBlock.Settings.copy(Blocks.FARMLAND));
    public static final Item INVINCIBLE_FARMLAND_ITEM = new BlockItem(INVINCIBLE_FARMLAND, new Item.Settings());
}
