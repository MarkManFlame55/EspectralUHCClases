package net.mmf55dev.uhcclases.items;

import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.DelayedTask;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import net.mmf55dev.uhcclases.utils.Time;
import org.bukkit.*;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class SleepyItem implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        if (playerStats.isActive()) {
            ItemStack itemStack = e.getItem();
            World world = player.getWorld();
            if (itemStack != null && itemStack.equals(giveItem())) {
                itemStack.setAmount(0);
                world.playSound(player.getLocation(), Sound.ENTITY_TNT_PRIMED, 4.0f, 0.1f);
                world.spawnParticle(Particle.SMOKE_LARGE, player.getLocation(), 100,1,1,1);
                player.setFireTicks(Time.secondsToTicks(2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Time.secondsToTicks(2), 0));
                player.sendTitle(ChatColor.RED + "o7","VAS A EXPLOTAR!", 0, Time.secondsToTicks(3), 0);
                new DelayedTask(() -> {
                    world.playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 3.0f, 0.8f);
                    world.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THUNDER, 3.0f, 1.0f);
                    world.createExplosion(player.getLocation(), 12.0f, true, true, player);
                    if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                        player.setHealth(0);
                    }
                }, Time.secondsToTicks(2));
            }
        }
    }

    public static ItemStack giveItem() {
        ItemStack itemStack = new ItemStack(Material.TNT_MINECART);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_RED + "EXPLOTAR");
        itemMeta.setLocalizedName("class_item");
        itemMeta.setLocalizedName("explosion");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.RED + "WARNING");
        itemLore.add(ChatColor.WHITE + "Usar la habilidad de matara a ti tambien y jugadores cercanos");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
