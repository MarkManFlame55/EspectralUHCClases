package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.DelayedTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
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
        player.sendMessage(itemStack.getType().toString());
        if (playerStats.getUhcClass() != null) {
            if (itemStack.getType().equals(Material.MILK_BUCKET) && playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
                new DelayedTask(() -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                }, 1);
            }
        }
    }

    public static void init(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
        if (PlayerData.get(player.getUniqueId()).wantToSeeFire()) {
            player.setVisualFire(true);
        }
    }
    public static void checkForPlayersInWater() {
        Server server = Bukkit.getServer();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : server.getOnlinePlayers()) {
                    PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                    if (playerStats.getUhcClass() != null) {
                        if (playerStats.getUhcClass().equals(UhcClass.BLAZE) && playerStats.isActive()) {
                            if (player.isInWater() && !player.isInsideVehicle()) {
                                player.damage(2);
                            }
                        } else {
                            player.setVisualFire(false);
                        }
                    }
                }
            }
        }.runTaskTimer(EspectralClassUHC.getPlugin(EspectralClassUHC.class), 0, 20);
    }
}
