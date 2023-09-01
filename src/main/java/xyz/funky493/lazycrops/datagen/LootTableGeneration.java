package xyz.funky493.lazycrops.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;
import xyz.funky493.lazycrops.LazyCrops;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlock;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlocks;
import xyz.funky493.lazycrops.cropblocks.LazyItemCropBlock;

public class LootTableGeneration extends FabricBlockLootTableProvider {

    protected LootTableGeneration(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        LazyCrops.LOGGER.info("Generating loot tables...");
        for (LazyCropBlock crop : LazyCropBlocks.CROP_BLOCKS) {
            if (crop instanceof LazyItemCropBlock) {
                addDrop(crop, cropDrops(crop, ((LazyItemCropBlock) crop).product, crop.seedsItem, BlockStatePropertyLootCondition.builder(crop).properties(StatePredicate.Builder.create().exactMatch(crop.getAgeProperty(), 7))));
            } else {
                addDrop(crop, cropDrops(crop, Items.AIR, crop.seedsItem, BlockStatePropertyLootCondition.builder(crop).properties(StatePredicate.Builder.create().exactMatch(crop.getAgeProperty(), 7))));
            }
            LazyCrops.LOGGER.info("- Added loot table for " + crop.cropId + ".");
        }
        LazyCrops.LOGGER.info("Generated loot tables.");
    }
}
