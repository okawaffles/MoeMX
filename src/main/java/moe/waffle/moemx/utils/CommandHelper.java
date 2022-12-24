package moe.waffle.moemx.utils;

import moe.waffle.moemx.MoeMX;
import moe.waffle.moemx.commands.*;

public class CommandHelper {
    public static void RegisterCommands(MoeMX plugin) {
        // there's probably an easier way to do this...
        plugin.getCommand("fly").setExecutor(new CmdFly());
        plugin.getCommand("moemx").setExecutor(new CmdMoeMX());
        plugin.getCommand("gms").setExecutor(new CmdGms());
        plugin.getCommand("gmc").setExecutor(new CmdGmc());
        plugin.getCommand("gma").setExecutor(new CmdGma());
        plugin.getCommand("gmsp").setExecutor(new CmdGmsp());
    }
}
