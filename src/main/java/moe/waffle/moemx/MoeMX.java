package moe.waffle.moemx;

import moe.waffle.moemx.commands.CmdFly;
import moe.waffle.moemx.commands.CmdMoeMX;
import moe.waffle.moemx.events.EPlayerChat;
import moe.waffle.moemx.events.EPlayerQuit;
import moe.waffle.moemx.utils.CommandHelper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import static moe.waffle.moemx.utils.Configuration.*;
import moe.waffle.moemx.events.EPlayerJoin;

public final class MoeMX extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        LoadConfig(this);

        // move to event helper java class eventually...
        new EPlayerJoin(this);
        new EPlayerQuit(this);
        new EPlayerChat(this);

        // register commands in CommandHelper.java
        CommandHelper.RegisterCommands(this);

        Bukkit.getLogger().info("MoeMX Enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("MoeMX Disabled. Thanks for using MoeMX!");
    }
}
