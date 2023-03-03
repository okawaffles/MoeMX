package moe.waffle.moemx.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import moe.waffle.moemx.utils.WarpsHelper;
import org.bukkit.entity.Player;

public class CmdWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[!] Please supply a warp name!");
            return true;
        }

        Location warp = WarpsHelper.GetWarp(args[0]);
        if (warp == null) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[!] That warp doesn't exist!");
            return true;
        }

        Player self = Bukkit.getPlayer(sender.getName());
        self.teleport(warp);
        self.sendMessage(ChatColor.DARK_GRAY + "Teleporting you to " + ChatColor.DARK_AQUA + args[0] + ChatColor.DARK_GRAY + "...");

        return true;
    }
}
