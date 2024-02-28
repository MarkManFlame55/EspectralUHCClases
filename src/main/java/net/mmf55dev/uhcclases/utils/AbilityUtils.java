package net.mmf55dev.uhcclases.utils;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class AbilityUtils {
    /*
    public static void notifyWhenFinish(Player player, HashMap<UUID, Long> cooldown, long finishTime, ItemStack itemStack) {
        new BukkitRunnable() {
            @Override
            public void run() {
                long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                if (timeElapsed >= finishTime) {
                    ServerMessage.unicastTo(player, ChatColor.GREEN + "Ya puedes usar tu " + itemStack.getItemMeta().getDisplayName());
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    cancel();
                }
            }
        }.runTaskTimer(EspectralClassUHC.getPlugin(EspectralClassUHC.class), 0, 1);
    }
    public static void notifyWhenFinish(Player player, HashMap<UUID, Long> cooldown, long finishTime) {
        new BukkitRunnable() {
            @Override
            public void run() {
                long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                if (timeElapsed >= finishTime) {
                    ServerMessage.unicastTo(player, ChatColor.GREEN + "Ya puedes usar tu habilidad!" );
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    cancel();
                }
            }
        }.runTaskTimer(EspectralClassUHC.getPlugin(EspectralClassUHC.class), 0, 1);
    }
     */
}