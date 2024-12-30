package dev.lilycatgirl.moemx;

import dev.lilycatgirl.moemx.events.*;
import dev.lilycatgirl.moemx.qol.CanSleep;
import dev.lilycatgirl.moemx.qol.DetectAFK;
import dev.lilycatgirl.moemx.utils.WarpTabCompleter;
import dev.lilycatgirl.moemx.utils.WarpsHelper;
import dev.lilycatgirl.moemx.utils.CommandHelper;
import dev.lilycatgirl.moemx.utils.http.MinimalHttpServer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import static dev.lilycatgirl.moemx.utils.Configuration.*;

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
        new EPlayerMove(this);
        new EPlayerInteract(this);
        new EGetAchievement(this);
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

        // register the sleep and afk checkers
        CanSleep.SchedleSleepCheckTask(this);
        DetectAFK.Schedule(this);

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
