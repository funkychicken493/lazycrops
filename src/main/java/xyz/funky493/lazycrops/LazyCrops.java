package xyz.funky493.lazycrops;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.funky493.lazycrops.cropblocks.CoreItems;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlock;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlocks;

public class LazyCrops implements ModInitializer {
	public static final String MODID = "lazycrops";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MODID, "main_group"));

	@Override
	public void onInitialize() {

		LOGGER.info("Initializing Lazy Crops...");

		Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
				.displayName(Text.translatable("itemGroup." + MODID + ".main_group"))
				.icon(() -> new ItemStack(CoreItems.LAZY_SEEDS))
				.build()
		);

		LOGGER.info("Registering core items...");
		for (int i = 0; i < CoreItems.ITEMS.size(); i++) {
			Item item = CoreItems.ITEMS.keySet().toArray(new Item[0])[i];
			String itemId = CoreItems.ITEMS.values().toArray(new String[0])[i];
			Registry.register(Registries.ITEM, new Identifier(MODID, itemId), item);
			LOGGER.info("- Registered " + itemId + ".");
		}
		ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> {
			content.add(CoreItems.LAZY_SEEDS);
			content.add(CoreItems.LAZIER_SEEDS);
			content.add(CoreItems.LAZIEST_SEEDS);
		});
		LOGGER.info("Registered core items.");

		LOGGER.info("Registering crop blocks and seeds...");
		for (LazyCropBlock cropBlock : LazyCropBlocks.CROP_BLOCKS) {
			Registry.register(Registries.BLOCK, new Identifier(MODID, cropBlock.cropId), cropBlock);
			Registry.register(Registries.ITEM, new Identifier(MODID, cropBlock.seedsId), cropBlock.seedsItem);
			ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> {
				content.add(cropBlock.seedsItem);
			});
			LOGGER.info("- Registered " + cropBlock.cropId + " and " + cropBlock.seedsId + ".");
		}
		LOGGER.info("Registered crop blocks and seeds.");

		LOGGER.info("Modifying loot tables...");
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, supplier, setter) -> {
			if (id.getNamespace().equals("minecraft") && (id.getPath().contains("desert_pyramid") || id.getPath().contains("jungle_pyramid"))) {
				LootPool.Builder pool = new LootPool.Builder()
						.with(ItemEntry.builder(CoreItems.LAZY_SEEDS)).conditionally(RandomChanceLootCondition.builder(0.3f))
						.with(ItemEntry.builder(CoreItems.LAZIER_SEEDS)).conditionally(RandomChanceLootCondition.builder(0.05f))
						.with(ItemEntry.builder(CoreItems.LAZIER_SEEDS)).conditionally(RandomChanceLootCondition.builder(0.01f));

				supplier.pool(pool);
			}
		});
		LOGGER.info("Modified loot tables.");

	}
}