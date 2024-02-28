package net.mmf55dev.uhcclases.items;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.AbilityUtils;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import net.mmf55dev.uhcclases.utils.Time;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class WitchWandItem implements Listener {


    private final HashMap<UUID, Long> cooldown;
    public WitchWandItem() {this.cooldown = new HashMap<>();}
    private static boolean isHealing = false;


    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            ItemStack itemStack = e.getItem();
            if (itemStack != null && itemStack.equals(WitchWandItem.giveItem())) {
                PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                if (playerStats.getUhcClass().equals(UhcClass.WITCH)) {
                    if (!this.cooldown.containsKey(player.getUniqueId())) {
                        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        performAbility(player);
                        //AbilityUtils.notifyWhenFinish(player, cooldown, Time.minutes(1), itemStack);
                    } else {
                        long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                        if (timeElapsed >= Time.minutes(1)) {
                            this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                            performAbility(player);
                            //AbilityUtils.notifyWhenFinish(player, cooldown, Time.minutes(1), itemStack);
                        } else {
                            ServerMessage.unicastTo(player, itemStack.getItemMeta().getDisplayName() + ChatColor.RED + " sigue en cooldown!" + ChatColor.GRAY + " (" + Time.getRemainTime(timeElapsed, Time.minutes(1)) + "s)");
                        }
                    }
                } else {
                    ServerMessage.unicastTo(player, ChatColor.RED + "No puedes usar esta habilidad.");
                }
            }
        }


    }



    @EventHandler
    public static void onLeftClick(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            ItemStack itemStack = e.getItem();
            if (itemStack != null && itemStack.equals(giveItem())) {
                isHealing = !isHealing;
                Player player = e.getPlayer();
                if (isHealing) {
                    player.sendTitle("", ChatColor.RED + "❤", 1, 10, 1);
                    player.playSound(player, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 0.8f, 1.3f);
                } else {
                    player.sendTitle("", ChatColor.DARK_RED + "☠", 1, 10 ,1);
                    player.playSound(player, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 0.8f, 1.3f);
                }
            }
        }
    }

    private void performAbility(Player player) {
        World world = player.getWorld();
        if (isHealing) {
            for (Entity entity : player.getNearbyEntities(2, 3, 2)) {
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, false, false));
                }
            }
            world.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1.0f, 1.0f);
            world.spawnParticle(Particle.HEART, player.getLocation(), 5, 1, 1, 1);
        } else {
            for (Entity entity : player.getNearbyEntities(2, 3, 2)) {
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0, false, false, false));
                }
            }
            world.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0f, 1.0f);
            world.spawnParticle(Particle.VILLAGER_ANGRY, player.getLocation(), 10, 1, 1, 1);
        }
    }



    public static ItemStack giveItem() {
        ItemStack itemStack = new ItemStack(Material.STICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Varita de Bruja");
        itemMeta.setLocalizedName("witch_wand");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
