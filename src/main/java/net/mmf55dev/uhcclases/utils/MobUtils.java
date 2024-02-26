package net.mmf55dev.uhcclases.utils;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;

public class MobUtils {
    public static void setMaxHealth(LivingEntity entity, double maxHealth) {
        AttributeInstance health = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (health != null) {
            health.setBaseValue(maxHealth);
        }
    }
}
