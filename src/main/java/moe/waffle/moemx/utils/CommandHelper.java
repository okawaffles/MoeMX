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
        plugin.getCommand("heal").setExecutor(new CmdHeal());
        plugin.getCommand("spawn").setExecutor(new CmdSpawn());
        plugin.getCommand("tpreq").setExecutor(new CmdTpReq());
        plugin.getCommand("tpa").setExecutor(new CmdTpa());
        plugin.getCommand("tpd").setExecutor(new CmdTpd());
        plugin.getCommand("warp").setExecutor(new CmdWarp());
        plugin.getCommand("createwarp").setExecutor(new CmdCreateWarp());
        plugin.getCommand("deletewarp").setExecutor(new CmdDeleteWarp());
        plugin.getCommand("warplist").setExecutor(new CmdWarplist());
        plugin.getCommand("lastdeath").setExecutor(new CmdLastDeath());
    }
}
