package xyz.funky493.lazycrops.cropblocks;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class CoreItems {
    public static final Item LAZY_SEEDS = new Item(new Item.Settings());
    public static final Item LAZIER_SEEDS = new Item(new Item.Settings());
    public static final Item LAZIEST_SEEDS = new Item(new Item.Settings());

    public static Item getItemFromCropLevel(int cropLevel) {
        switch (cropLevel) {
            case 0:
                return LAZY_SEEDS;
            case 1:
                return LAZIER_SEEDS;
            case 2:
                return LAZIEST_SEEDS;
            default:
                return null;
        }
    }

    public static final Map<Item, String> ITEMS = new HashMap<>() {{
        put(LAZY_SEEDS, "lazy_seeds");
        put(LAZIER_SEEDS, "lazier_seeds");
        put(LAZIEST_SEEDS, "laziest_seeds");
    }};
}
