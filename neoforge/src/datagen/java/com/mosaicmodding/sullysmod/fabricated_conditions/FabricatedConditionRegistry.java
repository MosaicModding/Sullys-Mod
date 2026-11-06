package com.mosaicmodding.sullysmod.fabricated_conditions;

import com.mosaicmodding.sullysmod.SullysMod;
import dev.uraneptus.fabricatedconditions.api.FabricatedConditionsAPI;

public class FabricatedConditionRegistry {
    public static final FabricatedConditionsAPI API = new FabricatedConditionsAPI(SullysMod.ID);

    static {
        API.registerFakeConditions("config", () -> DatagenConfigFlagCondition.CODEC, () -> FakeFabricConfigFlagCondition.CODEC);
    }
}
