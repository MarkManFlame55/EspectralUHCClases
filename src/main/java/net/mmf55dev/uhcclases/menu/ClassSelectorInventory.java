package net.mmf55dev.uhcclases.menu;

import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.items.MenuItems;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class ClassSelectorInventory implements Listener {

    @EventHandler
    public static void onInventorClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Selecciona tu Clase")) {
            Player player = (Player) e.getWhoClicked();
            ItemStack itemStack = e.getCurrentItem();
            PlayerStats playerStats = PlayerData.get(player.getUniqueId());
            if (itemStack != null) {
                e.setCancelled(true);
                player.closeInventory();
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                if (itemStack.equals(MenuItems.AssassinItem())) {
                   playerStats.setUhcClass(UhcClass.ASSASSIN);
                }
                if (itemStack.equals(MenuItems.BlazeItem())) {
                    playerStats.setUhcClass(UhcClass.BLAZE);
                }
                if (itemStack.equals(MenuItems.DolphinItem())) {
                    playerStats.setUhcClass(UhcClass.DOLPHIN);
                }
                if (itemStack.equals(MenuItems.WardenItem())) {
                    playerStats.setUhcClass(UhcClass.WARDEN);
                }
                if (itemStack.equals(MenuItems.IronGolemItem())) {
                    playerStats.setUhcClass(UhcClass.IRON_GOLEM);
                }
                if (itemStack.equals(MenuItems.ArcherItem())) {
                    playerStats.setUhcClass(UhcClass.ARCHER);
                }
                if (itemStack.equals(MenuItems.SleepyItem())) {
                    playerStats.setUhcClass(UhcClass.SLEEPY);
                }
                if (itemStack.equals(MenuItems.WitchItem())) {
                    playerStats.setUhcClass(UhcClass.WITCH);
                }
                if (itemStack.equals(MenuItems.ClassItem())) {
                    UhcClass playerClass = playerStats.getUhcClass();
                    if (playerClass != null) {
                        player.sendMessage(ChatColor.GREEN + "Ahora mismo eres: " + playerClass);
                    } else {
                        player.sendMessage(ChatColor.RED + "Aun no has elegido una clase");
                    }

                } else {
                    ServerMessage.broadcast(player.getDisplayName() + ChatColor.GREEN + " ha seleccionado la clase: " + itemStack.getItemMeta().getDisplayName());
                }
            }
        }
    }


    public static Inventory open(Player player) {
        Inventory menu = Bukkit.createInventory(player, 54, ChatColor.RED + "Selecciona tu Clase");

        menu.setItem(10, MenuItems.AssassinItem());
        menu.setItem(12, MenuItems.BlazeItem());
        menu.setItem(14, MenuItems.WardenItem());
        menu.setItem(16, MenuItems.IronGolemItem());
        menu.setItem(28, MenuItems.DolphinItem());
        menu.setItem(30, MenuItems.ArcherItem());
        menu.setItem(32, MenuItems.SleepyItem());
        menu.setItem(34, MenuItems.WitchItem());
        menu.setItem(49, MenuItems.ClassItem());

        return menu;
    }
}
