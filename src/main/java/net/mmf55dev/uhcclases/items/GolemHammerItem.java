package net.mmf55dev.uhcclases.items;

import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.DelayedTask;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import net.mmf55dev.uhcclases.utils.Time;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import javax.print.DocFlavor;
import javax.swing.*;
import java.util.*;

public class GolemHammerItem implements Listener {

    private final HashMap<UUID, Long> cooldown;


    public GolemHammerItem() {
        this.cooldown = new HashMap<>();
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack itemStack = e.getItem();
            Player player = e.getPlayer();
            PlayerStats playerStats = PlayerData.get(player.getUniqueId());
            Location pos = e.getClickedBlock().getLocation();
            if (itemStack != null && itemStack.hasItemMeta()) {
                if (itemStack.getItemMeta().hasLocalizedName() && itemStack.getItemMeta().getLocalizedName().equalsIgnoreCase("golem_weapon")) {
                    if (playerStats.getUhcClass() != null && playerStats.isActive()) {
                        if (playerStats.getUhcClass().equals(UhcClass.IRON_GOLEM)) {
                            if (!this.cooldown.containsKey(player.getUniqueId())) {
                                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                                performAbility(player, pos);
                            } else {
                                long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                                if (timeElapsed >= Time.minutes(2)) {
                                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                                    performAbility(player, pos);
                                } else {
                                    if (playerStats.wantToSeeCooldown()) {
                                        ServerMessage.unicastTo(player, itemStack.getItemMeta().getDisplayName() + ChatColor.RED + " sigue en cooldown!" + ChatColor.GRAY + " (" + Time.getRemainTime(timeElapsed, Time.minutes(2)) + "s)");
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }
    @EventHandler
    public static void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory() instanceof AnvilInventory || e.getInventory() instanceof GrindstoneInventory) {
            ItemStack itemStack = e.getCurrentItem();
            Player player = (Player) e.getWhoClicked();
            if (itemStack != null && itemStack.hasItemMeta()) {
                if (itemStack.getItemMeta().hasLocalizedName() && itemStack.getItemMeta().getLocalizedName().equalsIgnoreCase("golem_weapon")) {
                    e.setCancelled(true);
                    ServerMessage.unicastTo(player, ChatColor.RED + "No puedes meter este item aqui");
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                }
            }
        }
    }
    @EventHandler
    public static void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getItemMeta() != null) {
            if (itemStack.getItemMeta().getLocalizedName().equalsIgnoreCase("golem_weapon")) {
                if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    private void performAbility(Player player, Location pos) {
        World world = player.getWorld();
        world.spawnParticle(Particle.SWEEP_ATTACK, pos.clone().add(0,1,0), 20, 3, 0, 3);
        for (Entity entity : world.getNearbyEntities(pos, 3, 2, 3)) {
            if (entity instanceof LivingEntity livingEntity && entity != player) {
                Location entityPos = livingEntity.getLocation();
                Vector launchDirection = entityPos.toVector().add(entityPos.toVector().multiply(-1));
                launchDirection.setY(1.5);
                livingEntity.damage(10, player);
                new DelayedTask(() -> {
                    livingEntity.setVelocity(launchDirection);
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0, false, false, false));
                },1);
            }
        }
        world.playSound(pos, Sound.ENTITY_IRON_GOLEM_ATTACK, 20.0f, 0.8f);
        new DelayedTask(() -> {
            world.playSound(pos, Sound.BLOCK_ANVIL_LAND, 0.5f, 1.0f);
        }, 20);
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Time.secondsToTicks(20), 2, false, false, false));
    }

    public static ItemStack item() {
        ItemStack itemStack = new ItemStack(Material.IRON_AXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Martillo de Golem");
        itemMeta.setLocalizedName("golem_weapon");
        itemMeta.setUnbreakable(true);
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        AttributeModifier nodamage = new AttributeModifier(UUID.randomUUID(), "generic.luck", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        itemMeta.addAttributeModifier(Attribute.GENERIC_LUCK, nodamage);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para usar la habilidad");
        itemLore.add("");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
