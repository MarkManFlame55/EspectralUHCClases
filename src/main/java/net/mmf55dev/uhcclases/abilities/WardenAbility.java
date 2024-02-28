package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.items.SonicBoomItem;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class WardenAbility {
    public static void init(Player player) {
        PlayerStats playerData = PlayerData.get(player.getUniqueId());
        if (playerData.isActive() && playerData.getUhcClass().equals(UhcClass.WARDEN)) {
            SonicBoomItem.give(player);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (playerData.isActive()) {
                        if (player.isOnline()) {
                            //player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 20, 0, false, false, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 20, 4, false, false, false));
                        }
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(EspectralClassUHC.getPlugin(EspectralClassUHC.class), 0 ,1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 10, false, false ,false));
        }
    }
}
