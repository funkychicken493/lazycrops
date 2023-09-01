package xyz.funky493.lazycrops.datagen.advancements;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;

import java.util.function.Consumer;

public class AdvancementGeneration extends FabricAdvancementProvider {
    public AdvancementGeneration(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        LazyAdvancements lazyAdvancements = new LazyAdvancements();
        lazyAdvancements.accept(consumer);
    }
}
