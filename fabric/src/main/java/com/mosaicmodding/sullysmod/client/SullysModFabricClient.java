package com.mosaicmodding.sullysmod.client;

import com.mosaicmodding.sullysmod.core.SMUtils;
import com.mosaicmodding.tessera.TesseraBlockProperties;
import com.mosaicmodding.tessera.api.TesseraFabricClientUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;

public class SullysModFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        defineBlockRenderTypes();
    }

    //TODO for some reason this only works sometimes... Maybe thread problem?
    private void defineBlockRenderTypes() {
        for (var block : SMUtils.getAllModBlocks()) {
            if (block.get().properties() instanceof TesseraBlockProperties properties) {
                if (properties.blockRenderType == null || properties.blockRenderType.equals(TesseraBlockProperties.BlockRenderType.SOLID)) continue;
                var renderType = TesseraFabricClientUtil.parseRenderType(properties.blockRenderType);
                BlockRenderLayerMap.INSTANCE.putBlock(block.get(), renderType);
            }
        }
    }
}
