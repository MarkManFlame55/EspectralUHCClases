package net.mmf55dev.uhcclases.player;
import net.mmf55dev.uhcclases.classes.UhcClass;

import java.util.HashMap;
import java.util.UUID;

public class PlayerData {
    private static final HashMap<UUID, PlayerStats> playerData = new HashMap<>();

    public static PlayerStats get(UUID playerId) {
        return playerData.computeIfAbsent(playerId, k -> new PlayerStats());
    }
}

