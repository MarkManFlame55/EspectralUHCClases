package net.mmf55dev.uhcclases.items;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.Time;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ArcherItem implements Listener {

    private final HashMap<UUID, Long> cooldown;
    public ArcherItem() {this.cooldown = new HashMap<>();}

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        ItemStack itemStack = e.getItem();
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (itemStack != null && itemStack.getItemMeta().getLocalizedName().equalsIgnoreCase("archer_weapon")) {
                if (playerStats.isActive()) {
                    if (!this.cooldown.containsKey(player.getUniqueId())) {
                        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        performAbiility(player, playerStats);
                    } else {
                        long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                        if (timeElapsed >= Time.minutes(1)) {
                            this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                            performAbiility(player, playerStats);
                        } else {
                            player.sendMessage(itemStack.getItemMeta().getDisplayName() + ChatColor.RED + " sigue en cooldown");
                        }
                    }

                }
            }
        }
    }
    @EventHandler
    public static void onArrowDamage(EntityDamageByEntityEvent e) {
         if (e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
             if (e.getDamager() instanceof Arrow arrow) {
                 if (arrow.getShooter() instanceof Player player) {
                     PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                     if (playerStats.getUhcClass().equals(UhcClass.ARCHER) && playerStats.isArcherActive()) {
                         e.setDamage(e.getDamage()*2);
                     }
                 }
             }
         }
    }
    public static ItemStack giveItem() {
        ItemStack itemStack = new ItemStack(Material.BOW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Arco Refinado");
        itemMeta.setLocalizedName("archer_weapon");
        itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.GRAY + "Click Izquierdo para activar habilidad");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private void performAbiility(Player player, PlayerStats playerStats) {
        new BukkitRunnable() {
            int seconds = 0;
            @Override
            public void run() {
                if (seconds <= 5) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 21, 255, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 21, 127, false, false, false));
                    playerStats.setArcherActive(true);
                    seconds++;
                } else {
                    playerStats.setArcherActive(false);
                    cancel();
                }
            }
        }.runTaskTimer(EspectralClassUHC.getPlugin(EspectralClassUHC.class), 0, 20);
    }

}
