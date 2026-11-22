package com.mosaicmodding.sullysmod.content.artifacts;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ArtifactWeaponItem extends Item {
    @Nullable
    private final Supplier<SoundEvent> customBreakSound;

    public ArtifactWeaponItem(int damage, float speed, @Nullable Supplier<SoundEvent> customBreakSound, Properties properties) {
        super(properties.attributes(ArtifactWeaponItem.createAttributes(damage, speed)));
        this.customBreakSound = customBreakSound;
    }


    public static ItemAttributeModifiers createAttributes(int attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID, (float)attackDamage, AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.level() instanceof ServerLevel serverlevel) {
            stack.hurtAndBreak(1,
                    serverlevel,
                    attacker instanceof ServerPlayer serverplayer ? serverplayer : null,
                    (item) -> {
                        if (customBreakSound == null) {
                            attacker.onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                        } else {
                            attacker.level().playSound(null, attacker.getOnPos(), customBreakSound.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
                        }
                    }
            );
        }

    }
}
