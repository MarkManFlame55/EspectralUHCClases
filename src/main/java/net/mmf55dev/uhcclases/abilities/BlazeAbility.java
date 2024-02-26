package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class BlazeAbility {
    public static void init(Player player) {
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (playerStats.isActive()) {
                        if (player.isOnline()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 0, false, false, false));
                            Location pos = player.getLocation();
                            player.setFireTicks(2);
                            if (pos.getBlock().getType().equals(Material.WATER)) {
                                player.damage(2);
                            }
                        }
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(EspectralClassUHC.getPlugin(EspectralClassUHC.class), 0, 1);
        }
    }
}
