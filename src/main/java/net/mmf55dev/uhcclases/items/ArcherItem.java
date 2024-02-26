package net.mmf55dev.uhcclases.items;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
import java.util.List;

public class ArcherItem implements Listener {

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (playerStats.isActive()) {

                new BukkitRunnable() {
                    int seconds = 0;
                    @Override
                    public void run() {
                        if (seconds < 5) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 21, 255, false, false, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 21, 255, false, false, false));
                            playerStats.setArcherActive(true);
                            seconds++;
                        } else {
                            playerStats.setArcherActive(false);
                            cancel();
                        }
                    }
                }.runTaskTimer(EspectralClassUHC.getPlugin(EspectralClassUHC.class), 0, 1);
            }
        }
    }
    @EventHandler
    public static void onArrowDamage(EntityDamageByEntityEvent e) {
         if (e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
             if (e.getDamager() instanceof Player player) {
                 PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                 if (playerStats.getUhcClass().equals(UhcClass.ARCHER) && playerStats.isArcherActive()) {
                     e.setDamage(e.getDamage()*3);
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

}
