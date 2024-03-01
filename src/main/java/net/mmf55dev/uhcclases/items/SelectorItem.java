package net.mmf55dev.uhcclases.items;

import net.mmf55dev.uhcclases.classes.ClassManager;
import net.mmf55dev.uhcclases.menu.ClassSelectorInventory;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SelectorItem implements Listener {


    @EventHandler
    public static void onRightClick(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            ItemStack itemStack = e.getItem();
            Player player = e.getPlayer();
            PlayerStats playerStats = PlayerData.get(player.getUniqueId());
            if (itemStack != null && itemStack.isSimilar(give())) {
                if (!playerStats.isActive()) {
                    player.openInventory(ClassSelectorInventory.open(player));
                    player.playSound(player, Sound.BLOCK_TRIAL_SPAWNER_OPEN_SHUTTER, 1.0f, 0.5f);
                }
            }
        }
    }
    @EventHandler
    public static void onBlockPlace(BlockPlaceEvent e) {
        ItemStack itemStack = e.getItemInHand();
        if (itemStack.isSimilar(give())) {
            e.setCancelled(true);
        }
    }

    public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.AMETHYST_CLUSTER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.GOLD + "Seleccionar Clase");
        itemMeta.setLocalizedName("selector");
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para elegir");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void giveTo(Player player) {
        player.getInventory().addItem(give());
    }


}
