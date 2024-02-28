package net.mmf55dev.uhcclases.items;

import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
                world.playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 3.0f, 0.8f);
                world.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THUNDER, 3.0f, 1.0f);
                world.createExplosion(player.getLocation(), 12.0f, false, true, player);
            }
        }
    }

    public static ItemStack giveItem() {
        ItemStack itemStack = new ItemStack(Material.TNT_MINECART);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_RED + "EXPLOTAR");
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
