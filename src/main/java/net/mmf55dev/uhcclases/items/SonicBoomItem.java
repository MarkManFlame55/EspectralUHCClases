package net.mmf55dev.uhcclases.items;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.AbilityUtils;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import net.mmf55dev.uhcclases.utils.Time;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SonicBoomItem implements Listener {

    private final HashMap<UUID, Long> cooldown;
    public SonicBoomItem() {this.cooldown = new HashMap<>();}
    //.
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        PlayerStats playerData = PlayerData.get(player.getUniqueId());
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (item != null) {
                if (item.equals(SonicBoomItem.giveItem())) {
                    if (playerData.isActive()) {
                        if (playerData.getUhcClass().equals(UhcClass.WARDEN)) {
                            if (!this.cooldown.containsKey(player.getUniqueId())) {
                                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                                particleBeam(player);
                                player.playSound(player, Sound.ENTITY_WARDEN_SONIC_BOOM, 1.0f, 0.9f);
                                //AbilityUtils.notifyWhenFinish(player, cooldown, Time.seconds(30), item);
                            } else {
                                long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                                if (timeElapsed >= Time.seconds(30)) {
                                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                                    particleBeam(player);
                                    player.playSound(player, Sound.ENTITY_WARDEN_SONIC_BOOM, 1.0f, 0.9f);
                                    //AbilityUtils.notifyWhenFinish(player, cooldown, Time.seconds(30), item);
                                } else {
                                    if (playerData.wantToSeeCooldown()) {
                                        ServerMessage.unicastTo(player, item.getItemMeta().getDisplayName() + ChatColor.RED + " sigue en cooldown!" + ChatColor.GRAY + " (" + Time.getRemainTime(timeElapsed, Time.seconds(30)) + "s)");
                                    }
                                }
                            }
                        } else {
                            ServerMessage.unicastTo(player,  ChatColor.RED + "No puedes usar este item, no eres un Warden");
                        }
                    } else {
                        ServerMessage.unicastTo(player, ChatColor.RED + "Aun no puedes usar este item");
                    }
                }
            }
        }
    }

    public static void particleBeam(Player p) {
        Location startLoc = p.getEyeLocation();
        Location particleLoc = startLoc.clone();
        World world = startLoc.getWorld();
        Vector dir = startLoc.getDirection();

        Vector vecOffset = dir.clone().multiply(1);

        new BukkitRunnable() {
            int maxBeamLenght = 60;
            int beamLenght = 0;

            @Override
            public void run() {

                for (Entity entity : world.getNearbyEntities(particleLoc, 5, 5, 5)) {
                    if (entity instanceof LivingEntity) {
                        if (entity == p) {
                            continue;
                        }
                        Vector particleMinVector = new Vector(
                                particleLoc.getX() - 0.75,
                                particleLoc.getY() - 0.75,
                                particleLoc.getZ() - 0.75);
                        Vector particleMaxVector = new Vector(
                                particleLoc.getX() + 0.75,
                                particleLoc.getY() + 0.75,
                                particleLoc.getZ() + 0.75);

                        if(entity.getBoundingBox().overlaps(particleMinVector,particleMaxVector)){
                            if (entity instanceof Player player) {
                                if (player.isBlocking()) {
                                    player.setCooldown(Material.SHIELD, 5*20);
                                }
                            }
                            ((Damageable) entity).damage(30,p);
                            p.playSound(p, Sound.ENTITY_WARDEN_ATTACK_IMPACT, 1.0f, 1.0f);
                            this.cancel();
                            return;
                        }
                    }
                }
                beamLenght++;
                if (beamLenght >= maxBeamLenght) {
                    world.spawnParticle(Particle.SONIC_BOOM, particleLoc, 0);
                    this.cancel();
                    return;
                }
                particleLoc.add(vecOffset);
                world.spawnParticle(Particle.SONIC_BOOM, particleLoc, 0);
            }
        }.runTaskTimer(EspectralClassUHC.getPlugin(EspectralClassUHC.class), 0, 1);

    }


    public static ItemStack giveItem() {
        ItemStack itemStack = new ItemStack(Material.ECHO_SHARD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Sonic Crystal");
        itemMeta.setLocalizedName("class_item");
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        List<String> itemLore = new ArrayList<>();
        itemLore.add(" ");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para lanza un SONIC BOOM");
        itemLore.add(ChatColor.DARK_GRAY + "30s Cooldown");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void give(Player player) {
        player.getInventory().addItem(giveItem());
    }
}
