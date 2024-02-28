package net.mmf55dev.uhcclases.classes;

import net.mmf55dev.uhcclases.abilities.*;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class ClassManager {
    public static void initClasses() {
        Server server = Bukkit.getServer();

        for (Player player : server.getOnlinePlayers()) {
            UhcClass playerClass = PlayerData.get(player.getUniqueId()).getUhcClass();
            PlayerStats playerStats = PlayerData.get(player.getUniqueId());
            if (playerClass != null) {
                playerStats.setActive(true);
                if (playerClass.equals(UhcClass.ASSASSIN)) {
                    //Nada que activar con los asesinos
                }
                if (playerClass.equals(UhcClass.WARDEN)) {
                    WardenAbility.init(player);
                }
                if (playerClass.equals(UhcClass.BLAZE)) {
                    BlazeAbility.init(player);
                }
                if (playerClass.equals(UhcClass.IRON_GOLEM)) {
                    IronGolemAbility.init(player);
                }
                if (playerClass.equals(UhcClass.DOLPHIN)) {
                    DolphinAbility.init(player);
                }
                if (playerClass.equals(UhcClass.SLEEPY)) {
                    SleepyAbiility.init(player);
                }
                if (playerClass.equals(UhcClass.ARCHER)) {
                    ArcherAbility.init(player);
                }
                if (playerClass.equals(UhcClass.WITCH)) {
                    WitchAbility.init(player);
                }
            }
        }
        ServerMessage.multicastToOp(ChatColor.GREEN + "Clases Iniciadas");
    }
    public static void restartClasses() {
        Server server = Bukkit.getServer();
        for (Player serverPlayers : server.getOnlinePlayers()) {
            PlayerData.get(serverPlayers.getUniqueId()).setActive(false);
            PlayerData.get(serverPlayers.getUniqueId()).setUhcClass(null);
        }
        ServerMessage.multicastToOp(ChatColor.GREEN + "Clases Reiniciadas");
    }

}
