package moe.waffle.moemx.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

// this class stores hashmapped data on player information, such as death locations, etc...
public class PlayerInformation {
    // information for death locations
    private static HashMap<Player, Location> deathLocations = new HashMap<Player, Location>();
    // information for /back, etc
    public static HashMap<Player, Location> previousLocations = new HashMap<Player, Location>();
    public static void setPlayerLastDeath(Player p, Location l) {
        deathLocations.put(p, l);
        previousLocations.put(p, l);
    }
    public static boolean checkPlayerDeath(Player p) {
        return deathLocations.containsKey(p);
    }
    public static Location getPlayerLastDeath(Player p) {
        return deathLocations.get(p);
    }



    public static void setPreviousLocation(Player p, Location l) {
        previousLocations.put(p, l);
    }
    public static Location getPreviousLocation(Player p) {
        Location bed = p.getBedSpawnLocation();
        return previousLocations.getOrDefault(p, (bed != null) ? bed : p.getLocation() );
    }
}