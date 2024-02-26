package net.mmf55dev.uhcclases.utils;

import net.mmf55dev.uhcclases.EspectralClassUHC;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class DelayedTask implements Listener {

    private static Plugin plugin = EspectralClassUHC.getPlugin(EspectralClassUHC.class);
    private int id = -1;

    public DelayedTask(Plugin instance) {
        plugin = instance;
    }
    public DelayedTask(Runnable runnable) {
        this(runnable, 0);
    }
    public DelayedTask(Runnable runnable, long delay) {
        if (plugin.isEnabled()) {
            id = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, runnable, delay);
        } else {
            runnable.run();
        }
    }
}
