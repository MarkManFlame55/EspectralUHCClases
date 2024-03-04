package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class AssassinAbility implements Listener {

    private final HashMap<UUID, Long> cooldown;
    public AssassinAbility() {this.cooldown = new HashMap<>();}

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e) {
        Player player = e.getPlayer();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.getUhcClass() != null) {
            if (playerStats.isActive() && playerStats.getUhcClass().equals(UhcClass.ASSASSIN)) {
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

    public static void init(Player player) {
        ServerMessage.unicastTo(player, ChatColor.GREEN + "Has recibido tu habilidad");
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1f, 1.5f);
    }
    private void performAbility(Player player) {
        World world = player.getWorld();
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Time.secondsToTicks(30), 0, false, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Time.secondsToTicks(30), 1, false, false, false));
        world.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 2.0f, 0.8f);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0, false, false, false));
        world.spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 5);
        new DelayedTask(() -> {
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Time.secondsToTicks(10), 0, false, false, false));
            world.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2.5f, 1.2f);
            world.spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 5);
        }, 30 * 20);
    }
}
