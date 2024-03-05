package net.mmf55dev.uhcclases.player;

import net.mmf55dev.uhcclases.classes.UhcClass;
import org.bukkit.entity.Player;

public class PlayerStats {
    private UhcClass uhcClass;
    private boolean classActive;
    private boolean archerActive;
    private boolean canSeeFire = true;
    private boolean canSeeCooldown = true;
    private boolean blazeActive;

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
    public boolean wantToSeeFire() {
        return this.canSeeFire;
    }
    public void setToSeeFire(boolean wantToSeeFire) {
        this.canSeeFire = wantToSeeFire;
    }
    public void setArcherActive(boolean archerActive) {
        this.archerActive = archerActive;
    }
    public boolean isArcherActive() {
        return this.archerActive;
    }
    public void setSeeCooldown(boolean canSeeCooldown) {
        this.canSeeCooldown = canSeeCooldown;
    }
    public boolean wantToSeeCooldown() {
        return this.canSeeCooldown;
    }
    public void setBlazeActive(boolean blazeActive) {
        this.blazeActive = blazeActive;
    }
    public boolean isBlazeActive() {
        return this.blazeActive;
    }


}
