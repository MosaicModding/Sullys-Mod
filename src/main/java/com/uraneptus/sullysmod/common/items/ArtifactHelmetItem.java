package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ArtifactHelmetItem extends ArmorItem {
    @Nullable
    private HumanoidModel<?> customModel;

    public ArtifactHelmetItem(ArmorMaterial material, Properties pProperties) {
        super(material, Type.HELMET, pProperties);
    }

    public ArtifactHelmetItem(ArmorMaterial material, Properties pProperties, HumanoidModel<?> customModel) {
        super(material, Type.HELMET, pProperties);
        this.customModel = customModel;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().getBlockState(pContext.getClickedPos()).is(SMBlocks.ITEM_STAND.get())) {
            return InteractionResult.PASS;
        }
        return super.useOn(pContext);
    }

    @Nullable
    public HumanoidModel<?> getCustomModel() {
        return this.customModel;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            HumanoidModel<?> model;
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
                if (customModel != null && slot == EquipmentSlot.HEAD) {
                    model = customModel;
                    model.head.copyFrom(properties.head);
                    return model;
                }
                return properties;
            }
        });
    }
}
