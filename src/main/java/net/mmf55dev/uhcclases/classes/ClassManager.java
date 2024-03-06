package net.mmf55dev.uhcclases.classes;

import net.mmf55dev.uhcclases.abilities.*;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class ClassManager {

    private static boolean hasStarted;


    public static void initClasses() {
        if (!hasStarted) {
            Server server = Bukkit.getServer();
            for (Player player : server.getOnlinePlayers()) {
                UhcClass playerClass = PlayerData.get(player.getUniqueId()).getUhcClass();
                PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                if (playerClass != null) {
                    playerStats.setActive(true);
                    initEachClass(player, playerClass);
                } else {
                    ServerMessage.unicastTo(player, ChatColor.GRAY + "No has elegido una clase durante el tiempo establecido. Se te asignó una aleatoria.");
                    playerStats.setUhcClass(getRandomEnumValue(UhcClass.class));
                    playerClass = playerStats.getUhcClass();
                    playerStats.setActive(true);
                    initEachClass(player, playerClass);
                }
            }
            BlazeAbility.checkForPlayersInWater();
            ServerMessage.multicastToOp(ChatColor.GREEN + "Clases Iniciadas");
            hasStarted = true;
        } else {
            ServerMessage.multicastToOp(ChatColor.RED + "La Partida ya ha comenzado");
        }

    }
    public static void restartClasses() {
        Server server = Bukkit.getServer();
        hasStarted = false;
        for (Player serverPlayers : server.getOnlinePlayers()) {
            if (serverPlayers.isVisualFire()) {
                serverPlayers.setVisualFire(false);
            }
            PlayerData.get(serverPlayers.getUniqueId()).setActive(false);
            PlayerData.get(serverPlayers.getUniqueId()).setUhcClass(null);
            for (PotionEffect effect : serverPlayers.getActivePotionEffects()) {
                serverPlayers.removePotionEffect(effect.getType());
            }
        }
        ServerMessage.multicastToOp(ChatColor.GREEN + "Clases Reiniciadas");
    }
    public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
        Random random = new Random();
        int randomIndex = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[randomIndex];
    }
    private static void initEachClass(Player player, UhcClass playerClass) {
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
