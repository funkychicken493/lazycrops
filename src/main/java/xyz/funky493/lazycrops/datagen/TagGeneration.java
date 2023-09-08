package xyz.funky493.lazycrops.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import xyz.funky493.lazycrops.cropblocks.LazyCoreItems;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlock;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlocks;

import java.util.concurrent.CompletableFuture;

public class TagGeneration extends FabricTagProvider.ItemTagProvider {
    public TagGeneration(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    //"easy common key"
    private static TagKey<Item> ezCKey(String path) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier("c", path));
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ezCKey("seeds")).add(LazyCoreItems.LAZY_SEEDS).add(LazyCoreItems.LAZIER_SEEDS).add(LazyCoreItems.LAZIEST_SEEDS).setReplace(false);
        for (LazyCropBlock cropBlock : LazyCropBlocks.CROP_BLOCKS) {
            getOrCreateTagBuilder(ezCKey("seeds")).add(cropBlock.seedsItem).setReplace(false);
            getOrCreateTagBuilder(ezCKey("seeds/" + cropBlock.cropId.split("_")[0])).add(cropBlock.seedsItem).setReplace(false);
        }
    }
}
