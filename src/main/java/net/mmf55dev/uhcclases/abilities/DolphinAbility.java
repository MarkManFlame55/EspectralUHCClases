package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.DelayedTask;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DolphinAbility implements Listener {

    @EventHandler
    public static void onPlayerConsume(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();
        World world = player.getWorld();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null) {
            if (itemStack.getType().equals(Material.MILK_BUCKET) && playerStats.getUhcClass().equals(UhcClass.DOLPHIN)) {
                new DelayedTask(() -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                    if (world.getEnvironment().equals(World.Environment.NETHER)) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, PotionEffect.INFINITE_DURATION, 1, false, false, false));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, PotionEffect.INFINITE_DURATION, 1, false, false, false));
                    }
                }, 1);
            }
        }
    }
    @EventHandler
    public static void onPortalEnter(PlayerPortalEvent e) {
        Player player = e.getPlayer();
        World world = e.getTo().getWorld();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass().equals(UhcClass.DOLPHIN) && playerStats.isActive()) {
            if (world != null && world.getEnvironment().equals(World.Environment.NETHER)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, PotionEffect.INFINITE_DURATION, 1, false, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, PotionEffect.INFINITE_DURATION, 1, false, false, false));
            } else if (world != null && world.getEnvironment().equals(World.Environment.NORMAL)) {
                player.removePotionEffect(PotionEffectType.WEAKNESS);
                player.removePotionEffect(PotionEffectType.SLOW);
            }
        }
    }
    public static void init(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0, false, false, false));
    }
}
