package com.uraneptus.sullysmod.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.renderer.bewlr.JadeShieldRenderer;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

import java.util.Optional;

public class SMSpriteSourceProvider extends SpriteSourceProvider {

    public SMSpriteSourceProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper, SullysMod.MOD_ID);
    }

    @Override
    protected void addSources() {
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new SingleFile(JadeShieldRenderer.JADE_SHIELD_TEXTURE.texture(), Optional.empty()));
    }
}
