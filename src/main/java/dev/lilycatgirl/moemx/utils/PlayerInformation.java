package dev.lilycatgirl.moemx.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;

// this provides utility for player information, such as death locations, etc...
public class PlayerInformation {
    // information for death locations
    private static HashMap<Player, Location> deathLocations = new HashMap<>();
    // information for /back, etc
    public static HashMap<Player, Location> previousLocations = new HashMap<>();

    // player homes
    private static HashMap<Player, Location> homes = new HashMap<>();
    private static JavaPlugin plg; // required for writing data to disk


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
    public static Location getPreviousLocation(@NotNull Player p) {
        Location bed = p.getBedSpawnLocation();
        return previousLocations.getOrDefault(p, (bed != null) ? bed : p.getLocation() );
    }


    public static void setPlayerHome(Player p, Location l) {
        homes.put(p, l);
    }
    public static @NotNull Location getPlayerHome(Player p) {
        Location home = homes.get(p);
        Location bed = p.getBedSpawnLocation();
        Location spawnPoint = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
        // tries: home -> bed -> spawn
        return (home != null)? home : (bed != null)? bed : spawnPoint;
    }
    private void loadPlayerHomes() {
        File warpf = new File(plg.getDataFolder(), "homes.yml");
        YamlConfiguration config = new YamlConfiguration();

        try {
            // do this later because i cba
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void RunPluginLoadTasks() {

    }
    public static void RunPluginUnloadTasks() {

    }
}