package net.mmf55dev.uhcclases.player;

import net.mmf55dev.uhcclases.classes.UhcClass;
import org.bukkit.entity.Player;

public class PlayerStats {
    private UhcClass uhcClass;
    private boolean classActive;
    private boolean archerActive;

    private Player player;

    public UhcClass getUhcClass() {
        return uhcClass;
    }

    public void setUhcClass(UhcClass uhcClass) {
        this.uhcClass = uhcClass;
    }

    public void setActive(boolean classActive) {
        this.classActive = classActive;
    }
    public boolean isActive() {
        return this.classActive;
    }

    public void setArcherActive(boolean archerActive) {
        this.archerActive = archerActive;
    }
    public boolean isArcherActive() {
        return this.archerActive;
    }



}
