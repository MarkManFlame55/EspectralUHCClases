package net.mmf55dev.uhcclases.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class ServerMessage {

    private static final String prefix = ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.AQUA + ChatColor.BOLD + "UHC Clases" + ChatColor.WHITE + ChatColor.BOLD + "] " + ChatColor.GRAY + "➤ " + ChatColor.RESET;


    public static void broadcast(String message) {
        Server server = Bukkit.getServer();
        for (Player players : server.getOnlinePlayers()) {
            players.sendMessage(prefix + message);
        }
    }
    public static void multicastToOp(String message) {
        Server server = Bukkit.getServer();
        for (Player player : server.getOnlinePlayers()) {
            if (player.isOp()) {
                player.sendMessage(prefix + message);
            }
        }
    }

    public static void unicastTo(Player player, String message) {
        player.sendMessage(prefix + message);
    }
}
