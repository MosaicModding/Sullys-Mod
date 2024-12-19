package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.extensions.IForgeItem;

import java.util.function.Consumer;

public class ArtifactHelmetItem extends ArmorItem implements IForgeItem {
    public ArtifactHelmetItem(ArmorMaterial material, Properties pProperties) {
        super(material, Type.HELMET, pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().getBlockState(pContext.getClickedPos()).is(SMBlocks.ITEM_STAND.get())) {
            return InteractionResult.PASS;
        }
        return super.useOn(pContext);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
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
