package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.items.WitchWandItem;
import org.bukkit.entity.Player;

public class WitchAbility {
    public static void init(Player player) {
        player.getInventory().addItem(WitchWandItem.giveItem());
    }
}
