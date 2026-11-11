package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.content.amber.StuckInAmberData;
import net.mehvahdjukaar.moonlight.api.misc.IAttachmentType;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.entity.LivingEntity;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;

public class SMEntityAttachments {

    public static final IAttachmentType<StuckInAmberData, LivingEntity> STUCK_DATA = RegHelper.registerDataAttachment(path("amber_stuck_data"),
            () -> RegHelper.AttachmentBuilder
                    .create(StuckInAmberData::new)
                    .persistent(StuckInAmberData.CODEC)
                    .syncWith(StuckInAmberData.STREAM_CODEC),
            LivingEntity.class
    );

    public static void init() {}
}
