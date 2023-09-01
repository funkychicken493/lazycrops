package xyz.funky493.lazycrops;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlock;
import xyz.funky493.lazycrops.cropblocks.LazyItemCropBlock;
import xyz.funky493.lazycrops.cropblocks.LazyCropBlocks;

public class LazyCropsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		for (LazyCropBlock cropBlock : LazyCropBlocks.CROP_BLOCKS) {
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), cropBlock);
		}
	}
}