package dev.lilycatgirl.moemx.commands;

import dev.lilycatgirl.moemx.utils.WarpsHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdDeleteWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        if (args.length == 0) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[!] Please supply a warp name!");
            return true;
        }

        WarpsHelper.DeleteWarp(args[0]);
        sender.sendMessage(ChatColor.DARK_GRAY + "Deleted warp " + ChatColor.DARK_AQUA + args[0] + ChatColor.DARK_GRAY + "!");

        return true;
    }
}
