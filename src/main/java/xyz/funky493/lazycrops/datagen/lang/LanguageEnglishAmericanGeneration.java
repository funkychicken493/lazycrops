package xyz.funky493.lazycrops.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import xyz.funky493.lazycrops.LazyCrops;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlock;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlocks;

import java.nio.file.Path;

import static xyz.funky493.lazycrops.LazyCrops.MODID;

public class LanguageEnglishAmericanGeneration extends FabricLanguageProvider {
    public LanguageEnglishAmericanGeneration(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    private String snakeToTitle(String input) {
        String[] words = input.split("_");
        StringBuilder output = new StringBuilder();
        for (String word : words) {
            output.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        return output.toString().trim();
    }

    private void advancement(TranslationBuilder translationBuilder, String advancementId, String title, String description) {
        translationBuilder.add("advancements." + MODID + "." + advancementId + ".title", title);
        translationBuilder.add("advancements."  + MODID + "." + advancementId + ".description", description);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

        for (LazyCropBlock cropBlock : LazyCropBlocks.CROP_BLOCKS) {
            translationBuilder.add(cropBlock, snakeToTitle(cropBlock.cropId));
            translationBuilder.add(cropBlock.seedsItem, snakeToTitle(cropBlock.seedsId));
        }

        translationBuilder.add(LazyCrops.ITEM_GROUP, "Lazy Crops");

        //#region Core items

        translationBuilder.add("item.lazycrops.lazy_seeds", "Lazy Seeds");
        translationBuilder.add("item.lazycrops.lazier_seeds", "Lazier Seeds");
        translationBuilder.add("item.lazycrops.laziest_seeds", "Laziest Seeds");

        //#endregion

        //#region Advancements

        advancement(translationBuilder, "root", "Lazy Crops", "Obtain lazy seeds");
        advancement(translationBuilder, "lazier_seeds", "Lazier Seeds", "Obtain lazier seeds to be more lazy");
        advancement(translationBuilder, "laziest_seeds", "Laziest Seeds", "I am become lazy, the doer of nothing");

        //#endregion

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/lazycrops/lang/existing/en_us.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
