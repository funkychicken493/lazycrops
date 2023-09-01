package xyz.funky493.lazycrops.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import xyz.funky493.lazycrops.LazyCrops;
import xyz.funky493.lazycrops.cropblocks.CoreItems;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlock;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlocks;

public class ModelGeneration extends FabricModelProvider {
    public ModelGeneration(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        LazyCrops.LOGGER.info("Generating block state models...");
        for (LazyCropBlock crop : LazyCropBlocks.CROP_BLOCKS) {
            blockStateModelGenerator.registerCrop(crop, crop.getAgeProperty(), 0, 1, 2, 3, 4, 5, 6, 7);
            LazyCrops.LOGGER.info("- Added block state model for " + crop.cropId + ".");
        }
        LazyCrops.LOGGER.info("Generated block state models.");
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        LazyCrops.LOGGER.info("Generating item models...");
        itemModelGenerator.register(CoreItems.LAZY_SEEDS, Models.GENERATED);
        itemModelGenerator.register(CoreItems.LAZIER_SEEDS, Models.GENERATED);
        itemModelGenerator.register(CoreItems.LAZIEST_SEEDS, Models.GENERATED);
        LazyCrops.LOGGER.info("Generated item models.");
    }
}
