package xyz.funky493.lazycrops.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;
import xyz.funky493.lazycrops.cropblocks.*;

import java.util.function.Consumer;

public class RecipeGeneration extends FabricRecipeProvider {
    public RecipeGeneration(FabricDataOutput output) {
        super(output);
    }

    private void donut(Consumer<RecipeJsonProvider> exporter, Item middle, Item surrounding, Item output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
                .pattern("sss")
                .pattern("sms")
                .pattern("sss")
                .input('s', surrounding)
                .input('m', middle)
                .criterion(FabricRecipeProvider.hasItem(middle), FabricRecipeProvider.conditionsFromItem(middle))
                .criterion(FabricRecipeProvider.hasItem(surrounding), FabricRecipeProvider.conditionsFromItem(surrounding))
                .offerTo(exporter);

    }

    private void threeByThree(Consumer<RecipeJsonProvider> exporter, Item fill, Item output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
                .pattern("fff")
                .pattern("fff")
                .pattern("fff")
                .input('f', fill)
                .criterion(FabricRecipeProvider.hasItem(fill), FabricRecipeProvider.conditionsFromItem(fill))
                .offerTo(exporter);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        for (LazyCropBlock cropBlock : LazyCropBlocks.CROP_BLOCKS) {
            if (cropBlock instanceof LazyItemCropBlock) {
                donut(exporter, CoreItems.getItemFromCropLevel(cropBlock.getLevel()), ((LazyItemCropBlock) cropBlock).product, cropBlock.seedsItem);
            } else if (cropBlock instanceof LazyEntityCropBlock) {
                donut(exporter, CoreItems.getItemFromCropLevel(cropBlock.getLevel()), ((LazyEntityCropBlock) cropBlock).craftItem, cropBlock.seedsItem);
            }
        }
        threeByThree(exporter, CoreItems.LAZY_SEEDS, CoreItems.LAZIER_SEEDS);
        threeByThree(exporter, CoreItems.LAZIER_SEEDS, CoreItems.LAZIEST_SEEDS);
    }
}
