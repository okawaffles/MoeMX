package dev.lilycatgirl.moemx.commands;

import dev.lilycatgirl.moemx.utils.WarpsHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Set;

public class CmdWarplist implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Set<String> warps = WarpsHelper.ListWarps();
        StringBuilder r = new StringBuilder("" + ChatColor.DARK_AQUA);

        for (String warp : warps) {
            r.append(warp);
            r.append(", ");
        }

        sender.sendMessage(ChatColor.DARK_GRAY + "List of available warps:");
        sender.sendMessage(r.toString());

        return true;
    }
}
