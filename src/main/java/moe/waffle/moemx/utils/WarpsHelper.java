package moe.waffle.moemx.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class WarpsHelper {
    private static HashMap<String, Location> warps = new HashMap<String, Location>();
    private static JavaPlugin plg;

    public boolean LoadWarpsFromFile() {
        File warpf = new File(plg.getDataFolder(), "warps.yml");
        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(warpf);
            Set<String> keys = config.getKeys(false);
            for (String key : keys) {
                World world = Bukkit.getWorld(config.get(key + ".world").toString());
                Location loc = new Location(world,
                        Double.parseDouble(config.get(key + ".x").toString()),
                        Double.parseDouble(config.get(key + ".y").toString()),
                        Double.parseDouble(config.get(key + ".z").toString()));

                warps.put(key, loc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
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
    }
}
