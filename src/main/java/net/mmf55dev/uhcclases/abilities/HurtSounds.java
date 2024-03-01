package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class HurtSounds implements Listener {
    @EventHandler
    public static void onPlayerHurt(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player player) {
            PlayerStats playerStats = PlayerData.get(player.getUniqueId());
            World world = player.getWorld();
            Location pos = player.getLocation();
            if (playerStats.isActive() && playerStats.getUhcClass() != null) {
                if (playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
                    world.playSound(pos, Sound.ENTITY_BLAZE_HURT, 1.0f, 1.0f);
                }
                if (playerStats.getUhcClass().equals(UhcClass.WARDEN)) {
                    world.playSound(pos, Sound.ENTITY_WARDEN_HURT, 1.0f, 1.0f);
                }
                if (playerStats.getUhcClass().equals(UhcClass.DOLPHIN)) {
                    world.playSound(pos, Sound.ENTITY_DOLPHIN_HURT, 1.0f, 1.0f);
                }
                if (playerStats.getUhcClass().equals(UhcClass.WITCH)) {
                    world.playSound(pos, Sound.ENTITY_WITCH_HURT, 1.0f, 1.0f);
                }
                if (playerStats.getUhcClass().equals(UhcClass.IRON_GOLEM)) {
                    world.playSound(pos, Sound.ENTITY_IRON_GOLEM_HURT, 1.0f, 1.0f);
                }
            }
        }
    }
}
