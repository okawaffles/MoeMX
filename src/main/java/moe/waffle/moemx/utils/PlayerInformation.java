package moe.waffle.moemx.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

// this class stores hashmapped data on player information, such as death locations, etc...
public class PlayerInformation {
    private static HashMap<Player, Location> deathLocations = new HashMap<Player, Location>();
    public static void setPlayerLastDeath(Player p, Location l) {
        deathLocations.put(p, l);
    }
    public static boolean checkPlayerDeath(Player p) {
        return deathLocations.containsKey(p);
    }
    public static Location getPlayerLastDeath(Player p) {
        return deathLocations.get(p);
    }
}
