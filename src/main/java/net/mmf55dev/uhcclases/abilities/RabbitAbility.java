package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class RabbitAbility implements Listener {
    public static void init(Player player) {
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass().equals(UhcClass.RABBIT)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (playerStats.isActive()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 1, false, false, false));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 0, false, false, false));
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(EspectralClassUHC.getPlugin(EspectralClassUHC.class), 0, 1);
        }
    }
}
