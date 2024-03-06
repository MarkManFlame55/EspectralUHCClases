package net.mmf55dev.uhcclases.commands;

import net.mmf55dev.uhcclases.abilities.*;
import net.mmf55dev.uhcclases.classes.ClassManager;
import net.mmf55dev.uhcclases.classes.UhcClass;
import net.mmf55dev.uhcclases.items.SelectorItem;
import net.mmf55dev.uhcclases.items.WitchWandItem;
import net.mmf55dev.uhcclases.player.PlayerData;
import net.mmf55dev.uhcclases.player.PlayerStats;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class uhcclassCommand implements CommandExecutor, TabCompleter {

    List<String> deleteItems = new ArrayList<>();

    private final List<String> options = new ArrayList<>();
    public uhcclassCommand() {
        options.add("selector");
        options.add("start");
        options.add("reload");
        options.add("give");
        options.add("remove");
        options.add("help");
        options.add("get");
        options.add("list");


        deleteItems.add("class_item");
        deleteItems.add("archer_weapon");
        deleteItems.add("golem_weapon");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            if (strings.length == 0) {
                ServerMessage.unicastTo(player, ChatColor.RED + "Comando Incompleto");
            } else {
                Server server = Bukkit.getServer();
                switch (strings[0]) {
                    case "selector":
                        for (Player serverPlayers : server.getOnlinePlayers()) {
                            if (!serverPlayers.getGameMode().equals(GameMode.SPECTATOR)) {
                                SelectorItem.giveTo(serverPlayers);
                                ServerMessage.unicastTo(serverPlayers, "¡Has recibido el " + ChatColor.GOLD + ChatColor.BOLD + "Selector De Clases" + ChatColor.RESET + "! Haz click derecho en el para abrir el menu de selección.");
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1f, 1.5f);
                            }
                        }
                        break;
                    case "start":
                        ClassManager.initClasses();
                        break;
                    case "reload":
                        ClassManager.restartClasses();
                        break;
                    case "remove":
                        if (strings.length >= 2 && server.getPlayer(strings[1]) != null) {
                            Player target = server.getPlayer(strings[1]);
                            PlayerStats playerStats = PlayerData.get(target.getUniqueId());
                            removeEffectsTo(target);
                            playerStats.setUhcClass(null);
                            for (ItemStack itemStack : target.getInventory().getContents()) {
                                if (itemStack != null) {
                                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLocalizedName()) {
                                        if (deleteItems.contains(itemStack.getItemMeta().getLocalizedName())) {
                                            itemStack.setAmount(0);
                                        }
                                    }
                                }
                            }
                            if (target.isVisualFire()) {
                                target.setVisualFire(false);
                            }
                            ServerMessage.unicastTo(player, ChatColor.GREEN + "Se ha eliminado la clase de " + target.getDisplayName());
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1f, 1.5f);
                            ServerMessage.unicastTo(target, ChatColor.GREEN + "Tu clase a sido borrada por el host");
                            player.playSound(target, Sound.BLOCK_NOTE_BLOCK_BIT, 1f, 1.5f);
                            break;
                        }
                    case "give":
                        if (server.getPlayer(strings[1]) != null) {
                            Player target = server.getPlayer(strings[1]);
                            PlayerStats playerStats = PlayerData.get(target.getUniqueId());
                            if (strings.length == 3) {
                                switch (strings[2]) {
                                    case "ASSASSIN":
                                        removeEffectsTo(target);
                                        playerStats.setUhcClass(UhcClass.ASSASSIN);
                                        AssassinAbility.init(target);
                                        break;
                                    case "ARCHER":
                                        removeEffectsTo(target);
                                        playerStats.setUhcClass(UhcClass.ARCHER);
                                        ArcherAbility.init(target);
                                        break;
                                    case "WARDEN":
                                        playerStats.setUhcClass(UhcClass.WARDEN);
                                        removeEffectsTo(target);
                                        WardenAbility.init(target);
                                        break;
                                    case "BLAZE":
                                        removeEffectsTo(target);
                                        playerStats.setUhcClass(UhcClass.BLAZE);
                                        BlazeAbility.init(target);
                                        break;
                                    case "DOLPHIN":
                                        removeEffectsTo(target);
                                        playerStats.setUhcClass(UhcClass.DOLPHIN);
                                        DolphinAbility.init(target);
                                        break;
                                    case "IRONGOLEM":
                                        removeEffectsTo(target);
                                        playerStats.setUhcClass(UhcClass.IRON_GOLEM);
                                        IronGolemAbility.init(target);
                                        break;
                                    case "SLEEPY":
                                        removeEffectsTo(target);
                                        playerStats.setUhcClass(UhcClass.SLEEPY);
                                        SleepyAbiility.init(target);
                                        break;
                                    case "WITCH":
                                        removeEffectsTo(target);
                                        playerStats.setUhcClass(UhcClass.WITCH);
                                        WitchAbility.init(target);
                                        break;
                                    default:
                                        ServerMessage.unicastTo(player, ChatColor.RED + "No se encuentra esa clase, puede que la hayas escrito mal :P (ASSASSIN/ARCHER/WARDEN/IRONGOLEM/BLAZE/DOLPHIN/WITCH/SLEEPY)");
                                }
                            } else {
                                ServerMessage.unicastTo(player, ChatColor.RED + "Especifique la clase que vas a dar: (ASSASSIN/ARCHER/WARDEN/IRONGOLEM/BLAZE/DOLPHIN/WITCH/SLEEPY)");
                            }
                        } else {
                            ServerMessage.unicastTo(player, ChatColor.RED + "Ese jugador no existe o no esta conectado");
                        }
                        break;
                    case "help":
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1f, 1.5f);
                        for (String i : getStrings()) {
                            player.sendMessage(i);
                        }
                        break;
                    case "get":
                        if (strings.length == 2 && server.getPlayer(strings[1]) != null) {
                            Player target = server.getPlayer(strings[1]);
                            PlayerStats playerStats = PlayerData.get(target.getUniqueId());
                            if (playerStats.getUhcClass() != null) {
                                ServerMessage.unicastTo(player, "Clase de: " + target.getDisplayName() + ": " + ChatColor.GREEN + playerStats.getUhcClass());
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1f, 1.5f);
                            } else {
                                ServerMessage.unicastTo(player, ChatColor.RED + target.getDisplayName() + " no tiene ninguna clase");
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1f, 1.5f);
                            }
                        }
                        break;
                    case "list":
                        if (strings.length == 2) {
                            int count = 0;
                            boolean failed = false;
                            switch (strings[1]) {
                                case "ASSASSIN":
                                    for (Player serverPlayers : server.getOnlinePlayers()) {
                                        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                                        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.ASSASSIN)) {
                                            if (serverPlayers.getGameMode().equals(GameMode.SURVIVAL) || serverPlayers.getGameMode().equals(GameMode.ADVENTURE)) {
                                                count++;
                                            }
                                        }
                                    }
                                    break;
                                case "WARDEN":
                                    for (Player serverPlayers : server.getOnlinePlayers()) {
                                        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                                        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.WARDEN)) {
                                            if (serverPlayers.getGameMode().equals(GameMode.SURVIVAL) || serverPlayers.getGameMode().equals(GameMode.ADVENTURE)) {
                                                count++;
                                            }
                                        }
                                    }
                                    break;
                                case "ARCHER":
                                    for (Player serverPlayers : server.getOnlinePlayers()) {
                                        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                                        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.ARCHER)) {
                                            if (serverPlayers.getGameMode().equals(GameMode.SURVIVAL) || serverPlayers.getGameMode().equals(GameMode.ADVENTURE)) {
                                                count++;
                                            }
                                        }
                                    }
                                    break;
                                case "IRONGOLEM":
                                    for (Player serverPlayers : server.getOnlinePlayers()) {
                                        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                                        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.IRON_GOLEM)) {
                                            if (serverPlayers.getGameMode().equals(GameMode.SURVIVAL) || serverPlayers.getGameMode().equals(GameMode.ADVENTURE)) {
                                                count++;
                                            }
                                        }
                                    }
                                    break;
                                case "DOLPHIN":
                                    for (Player serverPlayers : server.getOnlinePlayers()) {
                                        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                                        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.DOLPHIN)) {
                                            if (serverPlayers.getGameMode().equals(GameMode.SURVIVAL) || serverPlayers.getGameMode().equals(GameMode.ADVENTURE)) {
                                                count++;
                                            }
                                        }
                                    }
                                    break;
                                case "SLEEPY":
                                    for (Player serverPlayers : server.getOnlinePlayers()) {
                                        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                                        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.SLEEPY)) {
                                            if (serverPlayers.getGameMode().equals(GameMode.SURVIVAL) || serverPlayers.getGameMode().equals(GameMode.ADVENTURE)) {
                                                count++;
                                            }
                                        }
                                    }
                                    break;
                                case "WITCH":
                                    for (Player serverPlayers : server.getOnlinePlayers()) {
                                        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                                        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.WITCH)) {
                                            if (serverPlayers.getGameMode().equals(GameMode.SURVIVAL) || serverPlayers.getGameMode().equals(GameMode.ADVENTURE)) {
                                                count++;
                                            }
                                        }
                                    }
                                    break;
                                case "BLAZE":
                                    for (Player serverPlayers : server.getOnlinePlayers()) {
                                        PlayerStats playerStats = PlayerData.get(player.getUniqueId());
                                        if (playerStats.getUhcClass() != null && playerStats.getUhcClass().equals(UhcClass.BLAZE)) {
                                            if (serverPlayers.getGameMode().equals(GameMode.SURVIVAL) || serverPlayers.getGameMode().equals(GameMode.ADVENTURE)) {
                                                count++;
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    ServerMessage.unicastTo(player, ChatColor.RED + "Esa clase no existe, puede que la hayas escrito mal :P (ASSASSIN/ARCHER/WARDEN/IRONGOLEM/BLAZE/DOLPHIN/WITCH/SLEEPY)");
                                    failed = true;
                            }
                            if (!failed && count == 0) {
                                ServerMessage.unicastTo(player, ChatColor.RED + "Parece que nadie ha elegido la clase " + strings[1]);
                            } else if (!failed && count > 0) {
                                ServerMessage.unicastTo(player, "Hay un total de " + ChatColor.GREEN + count + " " + strings[1]);
                            }
                        }
                        break;
                }
            }
        } else {
            commandSender.sendMessage("No puedes ejecutar este comando!");
        }
        return false;
    }

    private static List<String> getStrings() {
        List<String> msg = new ArrayList<>();
        msg.add("");
        msg.add(ChatColor.AQUA + "" + ChatColor.BOLD + "COMANDOS UHC CLASES");
        msg.add("");
        msg.add(ChatColor.GOLD + "/class selector" + ChatColor.RESET + ": Da el el item para seleccionar clase a todos los jugadores");
        msg.add(ChatColor.GOLD + "/class start" + ChatColor.RESET + ": Habilita las habilidades y da los items a los jugadores segun su clase");
        msg.add(ChatColor.GOLD + "/class reload" + ChatColor.RESET + ": Borra la clase de todos los jugadores");
        msg.add(ChatColor.GOLD + "/class give <jugador> <UhcClass>" + ChatColor.RESET + ": Da los objetos y habilidades de una clase a un jugador en especifico. Hacerlo antes de /class start hara que no funcione la habilidad.");
        msg.add(ChatColor.GOLD + "/class remove <jugador>" + ChatColor.RESET + ": Elimina la habilidad e items de clase de un jugador");
        msg.add(ChatColor.GOLD + "/class help" + ChatColor.RESET + ": Mostrar esta lista");
        return msg;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1 && commandSender instanceof Player) {
            List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(strings[0], options, completions);
            Collections.sort(completions);
            return completions;
        }
        return null;
    }

    private void removeEffectsTo(Player player) {
        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
    }
}