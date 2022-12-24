package moe.waffle.moemx.utils;

import moe.waffle.moemx.MoeMX;
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

        // defaults ; i dont think we need this?
        /*cfg.addDefault("customize.joinLeaveMessages.enabled", true);
        cfg.addDefault("customize.joinLeaveMessages.firstJoinMessage", "&8Welcome to the server, &3%s&8!");
        cfg.addDefault("customize.joinLeaveMessages.joinMessage", "&8Welcome back, &3%s&8!");
        cfg.addDefault("customize.joinLeaveMessages.leaveMessage", "&8See you later, &3%s&8!");*/

        // save default cfg
        plugin.saveDefaultConfig();

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
