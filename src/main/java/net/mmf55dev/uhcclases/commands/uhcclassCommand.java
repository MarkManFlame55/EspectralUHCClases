package net.mmf55dev.uhcclases.commands;

import net.mmf55dev.uhcclases.classes.ClassManager;
import net.mmf55dev.uhcclases.items.SelectorItem;
import net.mmf55dev.uhcclases.utils.ServerMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class uhcclassCommand implements CommandExecutor, TabCompleter {

    private final List<String> options = new ArrayList<>();
    public uhcclassCommand() {
        options.add("selector");
        options.add("start");
        options.add("reload");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player && player.isOp()) {
            if (strings.length == 0) {
                ServerMessage.unicastTo(player, ChatColor.RED + "Comando Incompleto");
            } else {
                Server server = Bukkit.getServer();
                switch (strings[0]) {
                    case "selector":
                        for (Player serverPlayers : server.getOnlinePlayers()) {
                            if (!serverPlayers.getGameMode().equals(GameMode.SPECTATOR)) {
                                SelectorItem.giveTo(serverPlayers);
                            }
                        }
                        break;
                    case "start":
                        ClassManager.initClasses();
                        break;
                    case "reload":
                        ClassManager.restartClasses();
                        break;
                }
            }
        }
        return false;
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
}
