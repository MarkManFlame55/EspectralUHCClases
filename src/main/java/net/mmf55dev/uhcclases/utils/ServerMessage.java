package net.mmf55dev.uhcclases.utils;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class ServerMessage {
    public static void broadcast(String message) {
        Server server = Bukkit.getServer();
        for (Player players : server.getOnlinePlayers()) {
            players.sendMessage(message);
        }
    }
    public static void multicastToOp(String message) {
        Server server = Bukkit.getServer();
        for (Player player : server.getOnlinePlayers()) {
            if (player.isOp()) {
                player.sendMessage(message);
            }
        }
    }
}
