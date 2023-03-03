package moe.waffle.moemx.utils;

import moe.waffle.moemx.MoeMX;
import moe.waffle.moemx.utils.WarpsHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Configuration {
    public static FileConfiguration cfg;
    private static JavaPlugin plg;

    public static void LoadConfig(JavaPlugin plugin) {
        cfg = plugin.getConfig();
        plg = plugin;

        // save default cfg
        plugin.saveDefaultConfig();
        WarpsHelper.RunFirstTimeSetup(plugin);

        // save motd (thanks chatgpt for helping me with this)
        InputStream is = MoeMX.class.getResourceAsStream("/motd.txt");
        File file = new File(plugin.getDataFolder(), "motd.txt");
        plugin.saveResource("motd.txt", false);

    }

    public static FileConfiguration GetConfig() {
        return cfg;
    }

    public static void reloadConfig() {
        cfg = plg.getConfig();
    }
}
