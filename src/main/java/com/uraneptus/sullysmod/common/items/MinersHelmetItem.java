package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.client.model.MinersHelmetModel;
import com.uraneptus.sullysmod.core.other.SMArmorMaterials;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MinersHelmetItem extends ArtifactHelmetItem {

    public MinersHelmetItem(Properties pProperties) {
        super(SMArmorMaterials.MINERS_HELMET, pProperties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            HumanoidModel<?> model;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
                if (slot == EquipmentSlot.HEAD) {
                    model = MinersHelmetModel.INSTANCE;
                    model.head.copyFrom(properties.head);
                    return model;
                }
                return properties;
            }
        });
    }
}
