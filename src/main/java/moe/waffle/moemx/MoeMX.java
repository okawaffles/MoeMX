package moe.waffle.moemx;

import moe.waffle.moemx.events.EPlayerDeath;
import moe.waffle.moemx.qol.CanSleep;
import moe.waffle.moemx.qol.NoNetheriteTemplate;
import moe.waffle.moemx.utils.WarpTabCompleter;
import moe.waffle.moemx.utils.WarpsHelper;
import moe.waffle.moemx.events.EPlayerChat;
import moe.waffle.moemx.events.EPlayerQuit;
import moe.waffle.moemx.utils.CommandHelper;
import moe.waffle.moemx.utils.http.MinimalHttpServer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import static moe.waffle.moemx.utils.Configuration.*;
import moe.waffle.moemx.events.EPlayerJoin;

public final class MoeMX extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic (also registers netherite recipe)
        LoadConfig(this);

        // move to event helper java class eventually...
        new EPlayerJoin(this);
        new EPlayerQuit(this);
        new EPlayerDeath(this);
        new EPlayerChat(this);
        // also start the HTTP server for relaying discord messages
        try {
            MinimalHttpServer.CreateServer();
            Bukkit.getLogger().info("HTTP Server listening on :9257");
        } catch (Exception e) {
            Bukkit.getLogger().severe("Failed to start HTTP server");
            e.printStackTrace();
        }

        // register commands in CommandHelper.java
        CommandHelper.RegisterCommands(this);

        // register the sleep checker
        CanSleep.SchedleSleepCheckTask(this);

        // tab completers
        getCommand("warp").setTabCompleter(new WarpTabCompleter());
        getCommand("deletewarp").setTabCompleter(new WarpTabCompleter());

        Bukkit.getLogger().info("MoeMX Enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Saving and shutting down MoeMX...");
        WarpsHelper.SaveWarpsToFile();
        MinimalHttpServer.ShutdownServer();
        Bukkit.getLogger().info("MoeMX disabled. Thanks for using MoeMX!");
    }
}
