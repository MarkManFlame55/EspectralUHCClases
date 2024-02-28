package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DolphinAbility {

    @EventHandler
    public static void onPlayerConsume(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();
        World world = player.getWorld();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (itemStack.getType().equals(Material.MILK_BUCKET) && playerStats.getUhcClass().equals(UhcClass.DOLPHIN)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0 ,false, false, false));
            if (world.getEnvironment().equals(World.Environment.NETHER)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20, 1, false, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 1, false, false, false));
            }
        }
    }
    @EventHandler
    public static void onPortalEnter(PlayerPortalEvent e) {
        Player player = e.getPlayer();
        World world = e.getTo().getWorld();
        if (world != null && world.getEnvironment().equals(World.Environment.NETHER)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20, 1, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 1, false, false, false));
        }
    }
    public static void init(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
    }
}
