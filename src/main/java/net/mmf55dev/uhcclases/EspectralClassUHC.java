package net.mmf55dev.uhcclases;

import net.mmf55dev.uhcclases.abilities.AssassinAbility;
import net.mmf55dev.uhcclases.commands.uhcclassCommand;
import net.mmf55dev.uhcclases.items.ArcherItem;
import net.mmf55dev.uhcclases.items.SelectorItem;
import net.mmf55dev.uhcclases.items.SleepyItem;
import net.mmf55dev.uhcclases.items.SonicBoomItem;
import net.mmf55dev.uhcclases.menu.ClassSelectorInventory;
import net.mmf55dev.uhcclases.utils.PlayerJumpEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class EspectralClassUHC extends JavaPlugin {

    @Override
    public void onEnable() {
        PlayerJumpEvent.register(this);
        getCommand("uhcclass").setExecutor(new uhcclassCommand());

        getServer().getPluginManager().registerEvents(new SelectorItem(), this);
        getServer().getPluginManager().registerEvents(new ClassSelectorInventory(), this);
        getServer().getPluginManager().registerEvents(new AssassinAbility(), this);
        getServer().getPluginManager().registerEvents(new SonicBoomItem(), this);
        getServer().getPluginManager().registerEvents(new SleepyItem(), this);
        getServer().getPluginManager().registerEvents(new ArcherItem(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
