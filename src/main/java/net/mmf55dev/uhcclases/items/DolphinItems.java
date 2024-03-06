package net.mmf55dev.uhcclases.items;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

public class DolphinItems {
    public static ItemStack helmet() {
        ItemStack itemStack = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        assert armorMeta != null;
        armorMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Casco de Delfin");
        armorMeta.setLocalizedName("class_item");
        armorMeta.setColor(Color.fromRGB(53,241,237));
        armorMeta.addEnchant(Enchantment.WATER_WORKER, 1, false);
        armorMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        armorMeta.addItemFlags(ItemFlag.HIDE_DYE);
        armorMeta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        ArmorMeta trimMeta = (ArmorMeta) armorMeta;
        trimMeta.setTrim(new ArmorTrim(TrimMaterial.QUARTZ, TrimPattern.RAISER));
        itemStack.setItemMeta(trimMeta);
        return itemStack;
    }
    public static ItemStack trident() {
        ItemStack itemStack = new ItemStack(Material.TRIDENT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Tridente de Delfin");
        itemMeta.setLocalizedName("class_item");
        itemMeta.addEnchant(Enchantment.LOYALTY, 3, false);
        itemMeta.addEnchant(Enchantment.DURABILITY, 2, false);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
