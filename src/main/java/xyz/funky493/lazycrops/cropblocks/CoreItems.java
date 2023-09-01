package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class CoreItems {
    public static final Item LAZY_SEEDS = new Item(new Item.Settings());
    public static final Item LAZIER_SEEDS = new Item(new Item.Settings());
    public static final Item LAZIEST_SEEDS = new Item(new Item.Settings());

    public static Item getItemFromCropLevel(int cropLevel) {
        return switch (cropLevel) {
            case 0 -> LAZY_SEEDS;
            case 1 -> LAZIER_SEEDS;
            case 2 -> LAZIEST_SEEDS;
            default -> null;
        };
    }

    public static final Map<Item, String> ITEMS = new HashMap<>() {{
        put(LAZY_SEEDS, "lazy_seeds");
        put(LAZIER_SEEDS, "lazier_seeds");
        put(LAZIEST_SEEDS, "laziest_seeds");
    }};
}
