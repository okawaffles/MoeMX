package dev.lilycatgirl.moemx.utils;

import dev.lilycatgirl.moemx.MoeMX;
import dev.lilycatgirl.moemx.commands.*;

public class CommandHelper {
    public static void RegisterCommands(MoeMX plugin) {
        // there's probably an easier way to do this...
        plugin.getCommand("moemx").setExecutor(new CmdMoeMX());
        // gamemodes
        plugin.getCommand("gms").setExecutor(new CmdGms());
        plugin.getCommand("gmc").setExecutor(new CmdGmc());
        plugin.getCommand("gma").setExecutor(new CmdGma());
        plugin.getCommand("gmsp").setExecutor(new CmdGmsp());
        // operator commands
        plugin.getCommand("fly").setExecutor(new CmdFly());
        plugin.getCommand("heal").setExecutor(new CmdHeal());
        // tp requests
        plugin.getCommand("tpreq").setExecutor(new CmdTpReq());
        plugin.getCommand("tpa").setExecutor(new CmdTpa());
        plugin.getCommand("tpd").setExecutor(new CmdTpd());
        // warps
        plugin.getCommand("spawn").setExecutor(new CmdSpawn());
        plugin.getCommand("warp").setExecutor(new CmdWarp());
        plugin.getCommand("createwarp").setExecutor(new CmdCreateWarp());
        plugin.getCommand("deletewarp").setExecutor(new CmdDeleteWarp());
        plugin.getCommand("warps").setExecutor(new CmdWarplist());
        // util
        plugin.getCommand("lastdeath").setExecutor(new CmdLastDeath());
        plugin.getCommand("back").setExecutor(new CmdBack()); // this command seems to be pretty weirdly broken
        plugin.getCommand("slimechunk").setExecutor(new CmdSlimechunk());
        // messaging commands
        plugin.getCommand("msg").setExecutor(new CmdMsg());
        plugin.getCommand("w").setExecutor(new CmdMsg());
        plugin.getCommand("whisper").setExecutor(new CmdMsg());
        plugin.getCommand("tell").setExecutor(new CmdMsg());
        plugin.getCommand("r").setExecutor(new CmdR());
        plugin.getCommand("spy").setExecutor(new CmdSpy());
        // afk
        plugin.getCommand("afk").setExecutor(new CmdAFK());
        // inventories
        plugin.getCommand("ec").setExecutor(new CmdEc());
        plugin.getCommand("invsee").setExecutor(new CmdInvsee());
    }
}
