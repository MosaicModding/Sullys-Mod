package com.mosaicmodding.sullysmod.client;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.registry.SMItems;
import com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils;
import com.mosaicmodding.tessera.datagen.ItemModelSmithTypes;
import com.mosaicmodding.tessera.datagen.providers.item.TesseraItemModelSystem;
import com.mosaicmodding.tessera.datagen.smith.itemmodel.data.ItemModelSystemData;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class SMItemModelGen extends TesseraItemModelSystem {

    public SMItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SullysMod.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        var data = new ItemModelSystemData(this, SMRegUtils.getAllModItems());

        ItemModelSmithTypes.SPAWN_EGG_ITEM.act(data, SMItems.BOULDERING_ZOMBIE_SPAWN_EGG.get());

        ItemModelSmithTypes.GENERATED_ITEM.act(data, SMItems.MOLTEN_AMBER_BUCKET.get());

        setTexturePath("lanternfish");
        ItemModelSmithTypes.SPAWN_EGG_ITEM.act(data, SMItems.LANTERNFISH_SPAWN_EGG.get());
        ItemModelSmithTypes.GENERATED_ITEM.act(data, SMItems.LANTERNFISH_BUCKET.get());
        ItemModelSmithTypes.GENERATED_ITEM.act(data, SMItems.LANTERNFISH.get());
        ItemModelSmithTypes.GENERATED_ITEM.act(data, SMItems.COOKED_LANTERNFISH.get());

        setTexturePath("gourds");
        ItemModelSmithTypes.GENERATED_ITEM.act(data, SMItems.MINIMU_SLICE.get());
        ItemModelSmithTypes.GENERATED_ITEM.act(data, SMItems.GIGANTO_SLICE.get());
        ItemModelSmithTypes.GENERATED_ITEM.act(data, SMItems.MINIMU_SEEDS.get());
        ItemModelSmithTypes.GENERATED_ITEM.act(data, SMItems.GIGANTO_SEEDS.get());

        setTexturePath("petrified");//TODO the auto item gen in the block model should automatically use this path
    }
}
