package net.mmf55dev.uhcclases.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WitchWandItem {
    public static ItemStack giveItem() {
        ItemStack itemStack = new ItemStack(Material.STICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Varita de Bruja");
        itemMeta.setLocalizedName("witch_wand");
        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }
}
