package xyz.funky493.lazycrops.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import xyz.funky493.lazycrops.cropblocks.*;

import java.util.function.Consumer;

public class RecipeGeneration extends FabricRecipeProvider {
    public RecipeGeneration(FabricDataOutput output) {
        super(output);
    }

    private void donut(Consumer<RecipeJsonProvider> exporter, Item middle, Item surrounding, Item output, Identifier recipeId) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
                .pattern("sss")
                .pattern("sms")
                .pattern("sss")
                .input('s', surrounding)
                .input('m', middle)
                .criterion(FabricRecipeProvider.hasItem(middle), FabricRecipeProvider.conditionsFromItem(middle))
                .criterion(FabricRecipeProvider.hasItem(surrounding), FabricRecipeProvider.conditionsFromItem(surrounding))
                .offerTo(exporter, recipeId);
    }

    private void threeByThree(Consumer<RecipeJsonProvider> exporter, Item fill, Item output, Identifier recipeId) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
                .pattern("fff")
                .pattern("fff")
                .pattern("fff")
                .input('f', fill)
                .criterion(FabricRecipeProvider.hasItem(fill), FabricRecipeProvider.conditionsFromItem(fill))
                .offerTo(exporter, recipeId);
    }

    private void reverseThreeByThree(Consumer<RecipeJsonProvider> exporter, Item input, Item output, Identifier recipeId) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, 9)
                .input(input)
                .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, recipeId);
    }

    private void inputOutput(Consumer<RecipeJsonProvider> exporter, Item input, Item output, Identifier recipeId) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output)
                .input(input)
                .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, recipeId);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        for (LazyCropBlock cropBlock : LazyCropBlocks.CROP_BLOCKS) {
            if (cropBlock instanceof LazyItemCropBlock) {
                donut(exporter, CoreItems.getItemFromCropLevel(cropBlock.getLevel()), ((LazyItemCropBlock) cropBlock).product, cropBlock.seedsItem, new Identifier("lazycrops", cropBlock.seedsId + "_from_donut"));
                inputOutput(exporter, cropBlock.seedsItem, ((LazyItemCropBlock) cropBlock).product, new Identifier("lazycrops", cropBlock.seedsId + "_from_input_output"));
            } else if (cropBlock instanceof LazyEntityCropBlock) {
                donut(exporter, CoreItems.getItemFromCropLevel(cropBlock.getLevel()), ((LazyEntityCropBlock) cropBlock).craftItem, cropBlock.seedsItem, new Identifier("lazycrops", cropBlock.seedsId + "_from_donut"));
                inputOutput(exporter, cropBlock.seedsItem, ((LazyEntityCropBlock) cropBlock).craftItem, new Identifier("lazycrops", cropBlock.seedsId + "_from_input_output"));
            }
        }
        threeByThree(exporter, CoreItems.LAZY_SEEDS, CoreItems.LAZIER_SEEDS, new Identifier("lazycrops", "lazier_seeds_from_three_by_three"));
        reverseThreeByThree(exporter, CoreItems.LAZIER_SEEDS, CoreItems.LAZY_SEEDS, new Identifier("lazycrops", "lazy_seeds_from_lazier_seeds"));
        threeByThree(exporter, CoreItems.LAZIER_SEEDS, CoreItems.LAZIEST_SEEDS, new Identifier("lazycrops", "laziest_seeds_from_three_by_three"));
        reverseThreeByThree(exporter, CoreItems.LAZIEST_SEEDS, CoreItems.LAZIER_SEEDS, new Identifier("lazycrops", "lazier_seeds_from_laziest_seeds"));
    }
}
