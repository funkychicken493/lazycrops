package xyz.funky493.lazycrops.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import xyz.funky493.lazycrops.LazyCrops;
import xyz.funky493.lazycrops.blocks.LazyBlocks;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlock;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlocks;

import java.nio.file.Files;
import java.nio.file.Path;

import static xyz.funky493.lazycrops.LazyCrops.MODID;

public class LanguageEnglishAmericanGeneration extends FabricLanguageProvider {
    private final Path existingFilePath;
    public LanguageEnglishAmericanGeneration(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
        try {
            existingFilePath = dataOutput.getModContainer().findPath("assets/lazycrops/lang/existing/en_us.json").get();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find existing language file!", e);
        }
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

    private boolean alreadyExists(String langKey){
        try {
            return Files.readString(existingFilePath).contains(langKey);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read existing language file!", e);
        }
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

        LazyCrops.LOGGER.info(existingFilePath.toFile().toString());

        for (LazyCropBlock cropBlock : LazyCropBlocks.CROP_BLOCKS) {
            if (alreadyExists(cropBlock.cropId)) {
                continue;
            }
            translationBuilder.add(cropBlock, snakeToTitle(cropBlock.cropId));
            translationBuilder.add(cropBlock.seedsItem, snakeToTitle(cropBlock.seedsId));
        }

        translationBuilder.add(LazyCrops.ITEM_GROUP, "Lazy Crops");

        //#region Core items and other blocks

        translationBuilder.add("item." + MODID + ".lazy_seeds", "Lazy Seeds");
        translationBuilder.add("item." + MODID + ".lazier_seeds", "Lazier Seeds");
        translationBuilder.add("item." + MODID + ".laziest_seeds", "Laziest Seeds");
        translationBuilder.add("block." + MODID + ".invincible_farmland", "Invincible Farmland");
        translationBuilder.add("item." + MODID + ".invincible_farmland", "Invincible Farmland");

        //#endregion

        //#region Advancements

        advancement(translationBuilder, "root", "Lazy Crops", "Obtain lazy seeds");
        advancement(translationBuilder, "lazier_seeds", "Lazier Seeds", "Obtain lazier seeds to be more lazy");
        advancement(translationBuilder, "laziest_seeds", "Laziest Seeds", "I am become lazy, the doer of nothing");

        //#endregion

        try {
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
