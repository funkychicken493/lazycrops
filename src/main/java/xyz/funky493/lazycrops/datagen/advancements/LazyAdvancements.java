package xyz.funky493.lazycrops.datagen.advancements;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.funky493.lazycrops.cropblocks.LazyCoreItems;

import java.util.function.Consumer;

import static xyz.funky493.lazycrops.LazyCrops.MODID;

public class LazyAdvancements implements Consumer<Consumer<Advancement>>{
    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {
        Advancement root = Advancement.Builder.create()
                .display(
                        LazyCoreItems.LAZY_SEEDS,
                        Text.translatable("advancements.lazycrops.root.title"),
                        Text.translatable("advancements.lazycrops.root.description"),
                        new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("obtain_lazy_seeds", InventoryChangedCriterion.Conditions.items(LazyCoreItems.LAZY_SEEDS))
                .build(advancementConsumer, MODID + "/root");
        Advancement lazier_seeds = Advancement.Builder.create()
                .parent(root)
                .display(
                        LazyCoreItems.LAZIER_SEEDS,
                        Text.translatable("advancements.lazycrops.lazier_seeds.title"),
                        Text.translatable("advancements.lazycrops.lazier_seeds.description"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("obtain_lazier_seeds", InventoryChangedCriterion.Conditions.items(LazyCoreItems.LAZIER_SEEDS))
                .build(advancementConsumer, MODID + "/lazier_seeds");
        Advancement laziest_seeds = Advancement.Builder.create()
                .parent(lazier_seeds)
                .display(
                        LazyCoreItems.LAZIEST_SEEDS,
                        Text.translatable("advancements.lazycrops.laziest_seeds.title"),
                        Text.translatable("advancements.lazycrops.laziest_seeds.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("obtain_laziest_seeds", InventoryChangedCriterion.Conditions.items(LazyCoreItems.LAZIEST_SEEDS))
                .build(advancementConsumer, MODID + "/laziest_seeds");
    }
}
