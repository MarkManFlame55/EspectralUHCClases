package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.items.SleepyItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SleepyAbiility implements Listener {
    public static void init(Player player) {
        player.getInventory().addItem(SleepyItem.giveItem());
    }
}
