package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.DelayedTask;
import net.mmf55dev.uhcclases.utils.PlayerJumpEvent;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import net.mmf55dev.uhcclases.utils.Time;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class BlazeAbility implements Listener {

    private final HashMap<UUID, Long> cooldown;
    public BlazeAbility() {this.cooldown = new HashMap<>();}

    @EventHandler
    public static void onPlayerConsume(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null) {
            if (itemStack.getType().equals(Material.MILK_BUCKET) && playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
                new DelayedTask(() -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                }, 1);
            }
        }
    }
    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e) {
        Player player = e.getPlayer();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null) {
            if (playerStats.isActive() && playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
                if (player.isSneaking()) {
                    if (!this.cooldown.containsKey(player.getUniqueId())) {
                        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        performAbility(player);
                    } else {
                        long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                        if (timeElapsed >= Time.minutes(2)) {
                            this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                            performAbility(player);
                        } else {
                            if (playerStats.wantToSeeCooldown()) {
                                ServerMessage.unicastTo(player, ChatColor.RED + "Tu habilidad sigue en cooldown!" + ChatColor.GRAY + " (" + Time.getRemainTime(timeElapsed, Time.minutes(2)) + "s)");
                            }
                        }
                    }
                }
            }
        }
    }

    private void performAbility(Player player) {
        World world = player.getWorld();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        player.setVisualFire(true);
        playerStats.setBlazeActive(true);
        world.spawnParticle(Particle.FLAME, player.getLocation(), 20, 1, 1, 1);
        world.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f);
        new DelayedTask(() -> {
            if (playerStats.wantToSeeFire()) {
                player.setVisualFire(false);
            }
            playerStats.setBlazeActive(false);
        }, Time.secondsToTicks(10));
    }

    @EventHandler
    public static void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player player) {
            PlayerStats playerStats = PlayerData.get(player.getUniqueId());
            if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
                if (playerStats.isBlazeActive()) {
                    LivingEntity entity = (LivingEntity) e.getEntity();
                    entity.setFireTicks(80);
                }
            }
        } else if (e.getDamager() instanceof Arrow arrow) {
            if (arrow.getShooter() instanceof Player player) {
                PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
                    if (playerStats.isBlazeActive()) {
                        LivingEntity entity = (LivingEntity) e.getEntity();
                        entity.setFireTicks(80);
                    }
                }
            }
        }
    }

    public static void init(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
        if (PlayerData.get(player.getUniqueId()).wantToSeeFire()) {
            player.setVisualFire(true);
        }
        ServerMessage.unicastTo(player, ChatColor.GREEN + "Has recibido tu habilidad");
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
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
