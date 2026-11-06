package com.mosaicmodding.sullysmod;

import com.mosaicmodding.sullysmod.client.SullysModClient;
import com.mosaicmodding.sullysmod.core.SMConstants;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.registry.*;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public class SullysMod {
    public static final String ID = "sullysmod";
    public static final String NAME = "Sully's Mod";
    public static final Logger LOG = LoggerFactory.getLogger(SullysMod.NAME);

    public static void init() {
        SMCommonConfig.init();
        SMConstants.Text.init();

        SMItems.init();
        SMBlocks.init();
        SMBlockEntityTypes.init();
        SMEntityAttachments.init();
        SMEntityTypes.init();
        SMSoundEvents.init();
        SMCreativeTabs.init();
        SMMobEffects.init();
        SMParticleTypes.init();

        PlatHelper.getPhysicalSide().ifClient(SullysModClient::init);
    }

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
