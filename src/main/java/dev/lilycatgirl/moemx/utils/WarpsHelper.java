package dev.lilycatgirl.moemx.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WarpsHelper {
    private static HashMap<String, Location> warps = new HashMap<String, Location>();
    private static JavaPlugin plg;

    public static void CreateNewWarp(String name, Location loc) {
        warps.put(name, loc);
    }

    public static void DeleteWarp(String name) {
        try {
            warps.remove(name);
        } catch (Exception ex) {
            plg.getLogger().info("Didn't delete any warp since the name didn't exist in the HashMap.");
        }
    }

    public static Location GetWarp(String name) {
        try {
            return warps.get(name);
        } catch (Exception ex) {
            return null;
        }
    }

    public static List<String> GetWarpList() {
        return new ArrayList<>(warps.keySet());
    }

    public static void LoadWarpsFromFile() {
        File warpf = new File(plg.getDataFolder(), "warps.yml");
        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(warpf);
            Set<String> keys = config.getKeys(false);
            for (String key : keys) {
                Location loc = (Location) config.get(key);

                warps.put(key, loc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Set<String> ListWarps() {
        return warps.keySet();
    }

    public static void SaveWarpsToFile() {
        File warpf = new File(plg.getDataFolder(), "warps.yml");
        YamlConfiguration config = new YamlConfiguration();

        try {
            for (String key : warps.keySet()) {
                config.set(key, warps.get(key));
            }

            config.save(warpf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void RunFirstTimeSetup(JavaPlugin plugin) {
        plg = plugin;
        File warpf = new File(plg.getDataFolder(), "warps.yml");
        YamlConfiguration config = new YamlConfiguration();

        try {
            if (!warpf.exists()) {
                warpf.createNewFile();

                Location spawnLocation = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();

                config.set("spawn", spawnLocation);
                config.save(warpf);
            }
        } catch (IOException err) {
            plugin.getLogger().severe("Couldn't create warps.yml file! The warp command will not work!");
            err.printStackTrace();
        }

        LoadWarpsFromFile();
    }
}
