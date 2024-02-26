package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.items.ArcherItem;
import org.bukkit.entity.Player;

public class ArcherAbility {
    public static void init(Player player) {
        player.getInventory().addItem(ArcherItem.giveItem());
    }

}
