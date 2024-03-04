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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ClassSelectorInventory implements Listener {

    @EventHandler
    public static void onInventorClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Selecciona tu Clase")) {
            Inventory inventory = e.getClickedInventory();
            Player player = (Player) e.getWhoClicked();
            ItemStack itemStack = e.getCurrentItem();
            PlayerStats playerStats = PlayerData.get(player.getUniqueId());
            assert inventory != null;
            if (e.getClick().isLeftClick()) {
                if (itemStack != null) {
                    e.setCancelled(true);
                    if (itemStack.equals(MenuItems.AssassinItem(player))) {
                        playerStats.setUhcClass(UhcClass.ASSASSIN);
                    }
                    if (itemStack.equals(MenuItems.BlazeItem(player))) {
                        playerStats.setUhcClass(UhcClass.BLAZE);
                    }
                    if (itemStack.equals(MenuItems.DolphinItem(player))) {
                        playerStats.setUhcClass(UhcClass.DOLPHIN);
                    }
                    if (itemStack.equals(MenuItems.WardenItem(player))) {
                        playerStats.setUhcClass(UhcClass.WARDEN);
                    }
                    if (itemStack.equals(MenuItems.IronGolemItem(player))) {
                        playerStats.setUhcClass(UhcClass.IRON_GOLEM);
                    }
                    if (itemStack.equals(MenuItems.ArcherItem(player))) {
                        playerStats.setUhcClass(UhcClass.ARCHER);
                    }
                    if (itemStack.equals(MenuItems.SleepyItem(player))) {
                        playerStats.setUhcClass(UhcClass.SLEEPY);
                    }
                    if (itemStack.equals(MenuItems.WitchItem(player))) {
                        playerStats.setUhcClass(UhcClass.WITCH);
                    }
                    if (itemStack.equals(MenuItems.FireItem(player))) {
                        if (playerStats.wantToSeeFire()) {
                            playerStats.setToSeeFire(false);
                            inventory.setItem(e.getSlot(), MenuItems.FireItem(player));
                            player.playSound(player, Sound.ENTITY_BLAZE_HURT, 1.0f, 1.0f);
                        } else {
                            playerStats.setToSeeFire(true);
                            inventory.setItem(e.getSlot(), MenuItems.FireItem(player));
                            player.playSound(player, Sound.ENTITY_BLAZE_HURT, 1.0f, 1.0f);
                        }
                    } else if (itemStack.equals(MenuItems.CooldownItem(player))) {
                        if (playerStats.wantToSeeCooldown()) {
                            playerStats.setSeeCooldown(false);
                            inventory.setItem(e.getSlot(), MenuItems.CooldownItem(player));
                            player.playSound(player, Sound.ITEM_LODESTONE_COMPASS_LOCK, 1.0f, 1.0f);
                        } else {
                            playerStats.setSeeCooldown(true);
                            inventory.setItem(e.getSlot(), MenuItems.CooldownItem(player));
                            player.playSound(player, Sound.ITEM_LODESTONE_COMPASS_LOCK, 1.0f, 1.0f);
                        }
                    } else {
                        player.closeInventory();
                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                        ServerMessage.broadcast(player.getDisplayName() + ChatColor.GREEN + " ha seleccionado la clase: " + itemStack.getItemMeta().getDisplayName());
                    }
                }
            } else if (e.getClick().isRightClick()) {
                if (itemStack != null) {
                    e.setCancelled(true);
                    player.closeInventory();
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    List<String> msg = new ArrayList<>();
                    if (itemStack.equals(MenuItems.AssassinItem(player))) {
                        msg.add(itemStack.getItemMeta().getDisplayName());
                        msg.add("");
                        msg.add("Los asesinos tiene la habilidad de volverse " + ChatColor.GRAY + ChatColor.ITALIC + "INVISIBLE");
                        msg.add("");
                        msg.add("Para hacerlo, los asesinos tienen que " + ChatColor.YELLOW + ChatColor.BOLD + "SHIFTEAR Y SALTAR");
                        msg.add("");
                        msg.add("Se volverán invisibles dentro de una nube, esta invisibilidad dura 30 segundos. Mientras esteis invisibles recibireis Velocidad 2");
                        msg.add("");
                        msg.add("Cuando se acaban estos 3 segundos, el jugador vuelve a ser visible tras una explosion(no hace daño), y recibe 5 segundos de Glowing ");
                        msg.add("");
                        msg.add("La habilidad tiene " + ChatColor.RED + "2 minutos " + ChatColor.RESET + "de cooldown;");
                        msg.add("");
                        for (String i : msg) {
                            player.sendMessage(i);
                        }
                    }
                    if (itemStack.equals(MenuItems.BlazeItem(player))) {
                        msg.add(itemStack.getItemMeta().getDisplayName());
                        msg.add("");
                        msg.add("Los blazes constan de " + ChatColor.GOLD + ChatColor.BOLD + "RESISTENCIA CONTRA EL FUEGO" + ChatColor.RESET + " infinita");
                        msg.add("");
                        msg.add("Los Blazes os vereis en fuego constantemente. Se recomienda algún resource pack que baje el fuego, o deshabilitar esta opcion en el menu de seleccion de clases");
                        msg.add("");
                        msg.add("Al ser monstruos del mismisimo infierno, el agua no será vuestra mejor amiga. Estar en el agua os hará recibir 1" + ChatColor.RED + "❤" + ChatColor.RESET + "/s de daño");
                        msg.add("");
                        for (String i : msg) {
                            player.sendMessage(i);
                        }
                    }
                    if (itemStack.equals(MenuItems.DolphinItem(player))) {
                        msg.add(itemStack.getItemMeta().getDisplayName());
                        msg.add("");
                        msg.add("Los Delfines contais con la habilidad de " + ChatColor.AQUA + "moveros rapido y poder respirar BAJO EL AGUA");
                        msg.add("");
                        msg.add("Sin embargo, si los delfines van al nether, serán debilitados con " + ChatColor.GRAY + ChatColor.BOLD + "Lentitud 2 y Debilidad 2");
                        msg.add("");
                        for (String i : msg) {
                            player.sendMessage(i);
                        }
                    }
                    if (itemStack.equals(MenuItems.WardenItem(player))) {
                        msg.add(itemStack.getItemMeta().getDisplayName());
                        msg.add("");
                        msg.add("Los Wardens vais a sufrir de " + ChatColor.DARK_GRAY  + ChatColor.BOLD + "OSCURIDAD PERPETUA");
                        msg.add("");
                        msg.add("Sin embargo, tendreis una " + ChatColor.RED + ChatColor.BOLD + "BARRA DE VIDA EXTRA " + ChatColor.RESET + "y recibireis un item con el que podreis invocar un " + ChatColor.DARK_AQUA + ChatColor.BOLD + "SONIC BOOM");
                        msg.add("");
                        msg.add("Este item tiene " + ChatColor.RED + "30 segundos " + ChatColor.RESET + "de cooldown;");
                        msg.add("");
                        for (String i : msg) {
                            player.sendMessage(i);
                        }
                    }
                    if (itemStack.equals(MenuItems.IronGolemItem(player))) {
                        msg.add(itemStack.getItemMeta().getDisplayName());
                        msg.add("");
                        msg.add("Los Golems sois basicmante " + ChatColor.GRAY + ChatColor.BOLD + "TANQUES");
                        msg.add("");
                        msg.add("Contais con " + ChatColor.GRAY + ChatColor.BOLD + "RESISTENCIA 2 " + ChatColor.RESET + "pero tambien con " + ChatColor.GRAY + ChatColor.BOLD + "LENTITUD 2");
                        msg.add("");
                        for (String i : msg) {
                            player.sendMessage(i);
                        }
                    }
                    if (itemStack.equals(MenuItems.ArcherItem(player))) {
                        msg.add(itemStack.getItemMeta().getDisplayName());
                        msg.add("");
                        msg.add("Los Arqueros empezais la partida con un Arco con Infinidad, pero durante toda la partida teneis " + ChatColor.GRAY + "DEBILIDAD 1" + ChatColor.RESET + " que os reduce el daño cuerpo a cuerpo");
                        msg.add("");
                        msg.add("El Arco que recibis tiene una habilidad con la que si pegais con ese arco en la mano, os quedais paralizados donde esteis, pero vuestras flechas harán " + ChatColor.GOLD + ChatColor.BOLD + "x2" + ChatColor.RESET + " de daño a los jugadores, mieentras que a los mobs les hara 50 puntos de daño");
                        msg.add("");
                        msg.add("El arco tiene " + ChatColor.RED + "1 minuto" + ChatColor.RESET + " de cooldown;");
                        msg.add("");
                        for (String i : msg) {
                            player.sendMessage(i);
                        }
                    }
                    if (itemStack.equals(MenuItems.SleepyItem(player))) {
                        msg.add(itemStack.getItemMeta().getDisplayName());
                        msg.add("");
                        msg.add("Recibiras un item de Vagoneta con TNT, al que si haces click derecho, te inmolaras con el poder de " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "2 CRISTALES DEL END");
                        msg.add("");
                        msg.add("Esta explosion te matara a ti tambien e incluso a tus compañeros. " + ChatColor.RED + "Cuidado con cuando la activas.");
                        msg.add("");
                        msg.add("Esta explosion no será instantanea segun la activas, sino que tiene un retardo de 2 segundos, en el que los jugadores cercanos podran verte activar la habilidad");
                        msg.add("");
                        for (String i : msg) {
                            player.sendMessage(i);
                        }
                    }
                    if (itemStack.equals(MenuItems.WitchItem(player))) {
                        msg.add(itemStack.getItemMeta().getDisplayName());
                        msg.add("");
                        msg.add("Las Brujas plantean un aspecto mas de Support, ya que estan recibiran una varita con la que pueden elegir entre " + ChatColor.RED + "CURAR" + ChatColor.RESET + " o " + ChatColor.DARK_RED + "DAÑAR");
                        msg.add("");
                        msg.add("Esta varita tiene efecto en un radio de 2 bloques alrededor del jugador, y todas las entidades que esten en ese rango podran recibir " + ChatColor.RED + ChatColor.BOLD + "VIDA INSTANTANEA 2" + ChatColor.RESET + " o " + ChatColor.DARK_RED + ChatColor.BOLD + "DAÑO INSTANTANEO");
                        msg.add("");
                        msg.add("La varita tiene " + ChatColor.RED + "1 minuto" + ChatColor.RESET + " de cooldown;");
                        msg.add("");
                        for (String i : msg) {
                            player.sendMessage(i);
                        }
                    }
                }
            } else {
                if (itemStack != null) {
                    e.setCancelled(true);
                }
            }
        }
    }


    public static Inventory open(Player player) {
        Inventory menu = Bukkit.createInventory(player, 54, ChatColor.RED + "Selecciona tu Clase");

        menu.setItem(10, MenuItems.AssassinItem(player));
        menu.setItem(12, MenuItems.BlazeItem(player));
        menu.setItem(14, MenuItems.WardenItem(player));
        menu.setItem(16, MenuItems.IronGolemItem(player));
        menu.setItem(28, MenuItems.DolphinItem(player));
        menu.setItem(30, MenuItems.ArcherItem(player));
        menu.setItem(32, MenuItems.SleepyItem(player));
        menu.setItem(34, MenuItems.WitchItem(player));
        menu.setItem(52, MenuItems.CooldownItem(player));
        menu.setItem(53, MenuItems.FireItem(player));

        return menu;
    }
}
