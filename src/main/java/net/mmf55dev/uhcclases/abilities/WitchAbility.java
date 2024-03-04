package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.items.WitchWandItem;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class WitchAbility {
    public static void init(Player player) {
        player.getInventory().addItem(WitchWandItem.giveItem());
        ServerMessage.unicastTo(player, ChatColor.GREEN + "Has recibido tu habilidad");
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1f, 1.5f);
    }
}
