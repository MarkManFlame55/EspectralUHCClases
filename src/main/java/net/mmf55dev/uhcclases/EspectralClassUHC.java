package net.mmf55dev.uhcclases;

import net.mmf55dev.uhcclases.abilities.*;
import net.mmf55dev.uhcclases.commands.uhcclassCommand;
import net.mmf55dev.uhcclases.items.*;
import net.mmf55dev.uhcclases.menu.ClassSelectorInventory;
import net.mmf55dev.uhcclases.utils.PlayerJumpEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class EspectralClassUHC extends JavaPlugin {

    @Override
    public void onEnable() {
        PlayerJumpEvent.register(this);
        getCommand("class").setExecutor(new uhcclassCommand());

        getServer().getPluginManager().registerEvents(new SelectorItem(), this);
        getServer().getPluginManager().registerEvents(new ClassSelectorInventory(), this);
        getServer().getPluginManager().registerEvents(new AssassinAbility(), this);
        getServer().getPluginManager().registerEvents(new SonicBoomItem(), this);
        getServer().getPluginManager().registerEvents(new SleepyItem(), this);
        getServer().getPluginManager().registerEvents(new ArcherItem(), this);
        getServer().getPluginManager().registerEvents(new WitchWandItem(), this);
        getServer().getPluginManager().registerEvents(new ArcherAbility(), this);
        getServer().getPluginManager().registerEvents(new BlazeAbility(), this);
        getServer().getPluginManager().registerEvents(new DolphinAbility(), this);
        getServer().getPluginManager().registerEvents(new IronGolemAbility(), this);
        getServer().getPluginManager().registerEvents(new WardenAbility(), this);
        getServer().getPluginManager().registerEvents(new HurtSounds(), this);
        getServer().getPluginManager().registerEvents(new GolemHammerItem(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
