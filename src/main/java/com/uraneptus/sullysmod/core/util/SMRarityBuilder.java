package com.uraneptus.sullysmod.core.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.awt.*;
import java.util.function.UnaryOperator;


//TODO this should be moved to plaster in the future

/**
 * This builder is used to define and format custom rarities. <br>
 * It returns an EnumProxy which is required for adding a new Rarity entry to the enum through NeoForge's Extensible Enum System.
 */
public class SMRarityBuilder {
    private int id = -1;
    private TextColor color;
    private boolean italic;
    private boolean bold;
    private boolean obfuscated;
    private boolean underlined;
    private boolean strikethrough;
    private final String name;

    public SMRarityBuilder(ResourceLocation name) {
        this.name = name.toString();
    }

    public SMRarityBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public SMRarityBuilder withColor(TextColor color) {
        this.color = color;
        return this;
    }

    public SMRarityBuilder withColor(ChatFormatting color) {
        this.color = TextColor.fromLegacyFormat(color);
        return this;
    }

    public SMRarityBuilder withColor(int color) {
        this.color = TextColor.fromRgb(color);
        return this;
    }

    public SMRarityBuilder withColor(Color color) {
        this.color = TextColor.fromRgb(color.getRGB());
        return this;
    }

    public SMRarityBuilder setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }

    public SMRarityBuilder setBold(boolean bold) {
        this.bold = bold;
        return this;
    }

    public SMRarityBuilder setObfuscated(boolean obfuscated) {
        this.obfuscated = obfuscated;
        return this;
    }

    public SMRarityBuilder setUnderlined(boolean underlined) {
        this.underlined = underlined;
        return this;
    }

    public SMRarityBuilder setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
        return this;
    }

    public EnumProxy<Rarity> build() {
        return new EnumProxy<>(Rarity.class, this.id, this.name, (UnaryOperator<Style>) s -> s
                .withColor(this.color).withItalic(this.italic).withBold(this.bold)
                .withObfuscated(this.obfuscated).withUnderlined(this.underlined)
                .withStrikethrough(this.strikethrough)
        );
    }
}
