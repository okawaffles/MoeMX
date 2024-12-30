package dev.lilycatgirl.moemx.utils;

import dev.lilycatgirl.moemx.MoeMX;
import dev.lilycatgirl.moemx.qol.NoNetheriteTemplate;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
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

        // register the nnt recipes after we load config
        NoNetheriteTemplate.registerRecipes(plugin);

        // save motd (thanks chatgpt for helping me with this)
        InputStream is = MoeMX.class.getResourceAsStream("/motd.txt");
        File file = new File(plugin.getDataFolder(), "motd.txt");
        if (!file.exists())
            plugin.saveResource("motd.txt", false);

    }

    public static FileConfiguration GetConfig() {
        return cfg;
    }

    public static void reloadConfig() {
        cfg = plg.getConfig();
        WarpsHelper.LoadWarpsFromFile();
    }
}
