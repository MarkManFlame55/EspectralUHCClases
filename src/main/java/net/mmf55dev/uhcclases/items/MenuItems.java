package net.mmf55dev.uhcclases.items;

import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuItems {

    private static List<String> lore() {
        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add("---> Click para Seleccionar Clase <---");
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para ver que hace esta clase");
        itemLore.add("");
        return itemLore;
    }

    public static ItemStack AssassinItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Asesino");
        itemMeta.setLore(lore());
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.ASSASSIN)) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1 ,false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack WardenItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.ECHO_SHARD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Warden");
        itemMeta.setLore(lore());
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.WARDEN)) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1 ,false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack BlazeItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Blaze");
        itemMeta.setLore(lore());
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1 ,false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack IronGolemItem(Player player){
        ItemStack itemStack = new ItemStack(Material.IRON_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Iron Golem");
        itemMeta.setLore(lore());
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.IRON_GOLEM)) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1 ,false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack DolphinItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Delfin");
        itemMeta.setLore(lore());
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.DOLPHIN)) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1 ,false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack ClassItem() {
        ItemStack itemStack = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "¿Cual es mi clase?");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack ArcherItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.BOW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Arquero");
        itemMeta.setLore(lore());
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.ARCHER)) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1 ,false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack SleepyItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.TNT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Sleepy");
        itemMeta.setLore(lore());
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.SLEEPY)) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1 ,false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack RabbitItem() {
        ItemStack itemStack = new ItemStack(Material.RABBIT_FOOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Conejo");
        itemMeta.setLore(lore());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack WitchItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.FERMENTED_SPIDER_EYE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Bruja");
        itemMeta.setLore(lore());
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.WITCH)) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1 ,false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack FireItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.GOLD + "Mostrar/Ocultar fuego del Blaze");
        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.wantToSeeFire()) {
            itemLore.add(ChatColor.GRAY + "Fuego Visible: " + ChatColor.GREEN + ChatColor.BOLD + "ON");
        } else {
            itemLore.add(ChatColor.GRAY + "Fuego Visible: " + ChatColor.RED + ChatColor.BOLD + "OFF");
        }
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
