package com.mosaicmodding.sullysmod.client;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.registry.SMArtifacts;
import com.mosaicmodding.sullysmod.registry.SMItems;
import com.mosaicmodding.sullysmod.core.SMUtils;
import com.mosaicmodding.tessera.datagen.ItemModelSmithTypes;
import com.mosaicmodding.tessera.datagen.providers.item.TesseraItemModelSystem;
import com.mosaicmodding.tessera.datagen.smith.itemmodel.ItemModelSmith;
import com.mosaicmodding.tessera.datagen.smith.itemmodel.data.ItemModelSystemData;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class SMItemModelGen extends TesseraItemModelSystem {

    public SMItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SullysMod.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        var data = new ItemModelSystemData(this, SMUtils.getAllModItems());

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

        setTexturePath("artifacts");
        SMArtifacts.MOD_ARTIFACTS.forEach(i -> {
            if (i.is(SMArtifacts.BROKEN_BOTTLE.get())) {
                brokenBottle(data);
            } else {
                ItemModelSmithTypes.GENERATED_ITEM.act(data, i.get());
            }
        });

        setTexturePath("petrified");//TODO the auto item gen in the block model should automatically use this path
    }

    public void brokenBottle(ItemModelSystemData data) {
        var smith = ItemModelSmithTypes.HANDHELD_ITEM.modifyResult(result -> result.builder().transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(180, -90, 0).translation(0, 4, 0.5F).scale(0.85F, 0.85F, 0.85F).end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(-180, 90, 0).translation(0, 4, 0.5F).scale(0.85F, 0.85F, 0.85F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(180, -90, 0).translation(1.13F, 3.2F, 1.13F).scale(0.68F, 0.68F, 0.68F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(-180, 90, 0).translation(1.13F, 3.2F, 1.13F).scale(0.68F, 0.68F, 0.68F).end()
                .end());
        smith.act(data, SMArtifacts.BROKEN_BOTTLE.get());
    }
}
