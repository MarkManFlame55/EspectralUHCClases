package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class BlazeAbility implements Listener {

    @EventHandler
    public static void onPlayerConsume(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (itemStack.getType().equals(Material.MILK_BUCKET) && playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
        }
    }

    public static void init(Player player) {
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
        if (playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (playerStats.isActive() && player.isOnline()) {
                        player.setFireTicks(10);
                        if (player.isInWater() && !player.isInsideVehicle()) {
                            player.damage(2);
                        }
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(EspectralClassUHC.getPlugin(EspectralClassUHC.class), 0, 10);
        }
    }
}
