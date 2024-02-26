package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DolphinAbility {
    public static void init(Player player) {
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.isActive()) {
            if (playerStats.getUhcClass().equals(UhcClass.DOLPHIN)) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (playerStats.isActive()) {
                            World world = player.getWorld();
                            if (player.isOnline()) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 20, 0, false, false, false));
                                player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 20, 0 ,false, false, false));
                                if (world.getEnvironment().equals(World.Environment.NETHER)) {
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20, 1, false, false, false));
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 1, false, false, false));
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
}
