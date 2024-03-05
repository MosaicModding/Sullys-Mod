package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.extensions.IForgeItem;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ArtifactHelmetItem extends ArmorItem implements IForgeItem {
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
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
                if (customModel != null && slot == EquipmentSlot.HEAD) {
                    model = customModel;
                    model.head.copyFrom(properties.head);
                    return model;
                }
                return properties;
            }

            @Override
            public void renderHelmetOverlay(ItemStack stack, Player player, int width, int height, float partialTick) {
                if (!stack.is(SMItems.SMALL_DENTED_HELMET.get())) return;

                Minecraft minecraft = Minecraft.getInstance();
                Gui gui = minecraft.gui;
                GuiGraphics guiGraphics = new GuiGraphics(minecraft, minecraft.renderBuffers().bufferSource());

                gui.renderTextureOverlay(guiGraphics, SullysMod.modPrefix("textures/misc/tinyhelmetblur.png"), 1.0F);
            }
        });
    }
}
