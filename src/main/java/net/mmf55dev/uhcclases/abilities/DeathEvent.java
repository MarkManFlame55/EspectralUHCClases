package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;

public class DeathEvent implements Listener {
    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        Server server = player.getServer();
        if (playerStats.getUhcClass() != null) {
            playerStats.setUhcClass(null);
            for (PotionEffect potionEffectType : player.getActivePotionEffects()) {
                player.removePotionEffect(potionEffectType.getType());
            }
            for (Player player1 : server.getOnlinePlayers()) {
                player1.playSound(player1, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.2f);
            }
        }
    }
    @EventHandler
    public static void onPlayerRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        ServerMessage.unicastTo(player, "Esperamos que hayas tenido una buena experiencia en la partida. Te recordamos que existe un hilo dentro del canal " + ChatColor.GREEN + ChatColor.ITALIC + "#sugerencias" + ChatColor.RESET + " de nuestro discord donde puede dejar tu feedback y opiniones sobre estas partidas. Cualquier comentario es bien recibido siempre que sea desde el respeto.");
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.2f);
    }
}
