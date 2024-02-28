package net.mmf55dev.uhcclases.abilities;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.DelayedTask;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class IronGolemAbility implements Listener {

    @EventHandler
    public static void onPlayerConsume(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
        ItemStack itemStack = e.getItem();
        if (playerStats.getUhcClass() != null) {
            if (itemStack.getType().equals(Material.MILK_BUCKET) && playerStats.getUhcClass().equals(UhcClass.IRON_GOLEM)) {
                new DelayedTask(() -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, PotionEffect.INFINITE_DURATION, 1, false, false, false));
                }, 1);

            }
        }
    }
    public static void init(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, PotionEffect.INFINITE_DURATION, 1, false, false, false));
    }
}
