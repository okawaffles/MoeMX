package dev.lilycatgirl.moemx.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WarpsHelper {
    private static final HashMap<String, Location> warps = new HashMap<String, Location>();
    private static final String WARP_NAME_PATTERN = "[^A-Za-z0-9_-]";
    private static JavaPlugin plg;

    public static String SanitizeWarpName(String name) {
        return name.replaceAll(WARP_NAME_PATTERN, "");
    }

    public static boolean IsValidWarpName(String name) {
        return !SanitizeWarpName(name).isEmpty();
    }

    public static boolean WarpExists(String name) {
        return warps.containsKey(name);
    }

    public static String FindWarpName(String name) {
        if (warps.containsKey(name)) return name;

        String sanitizedName = SanitizeWarpName(name);
        if (!sanitizedName.equals(name) && warps.containsKey(sanitizedName)) {
            return sanitizedName;
        }

        return null;
    }

    public static void CreateNewWarp(String name, Location loc) {
        warps.put(SanitizeWarpName(name), loc);
    }

    public static void DeleteWarp(String name) {
        try {
            String warpName = FindWarpName(name);
            if (warpName != null) warps.remove(warpName);
        } catch (Exception ex) {
            plg.getLogger().info("Didn't delete any warp since the name didn't exist in the HashMap.");
        }
    }

    public static Location GetWarp(String name) {
        try {
            String warpName = FindWarpName(name);
            if (warpName == null) return null;

            return warps.get(warpName);
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
        HashMap<String, Location> loadedWarps = new HashMap<String, Location>();

        try {
            config.load(warpf);
            Set<String> keys = config.getKeys(false);
            for (String key : keys) {
                Location loc = (Location) config.get(key);
                loadedWarps.put(key, loc);
            }

            warps.clear();
            warps.putAll(loadedWarps);
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
