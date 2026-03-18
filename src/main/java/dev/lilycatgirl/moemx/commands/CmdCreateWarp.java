package dev.lilycatgirl.moemx.commands;

import dev.lilycatgirl.moemx.utils.WarpsHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdCreateWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        if (args.length == 0) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[!] Please supply a warp name!");
            return true;
        }

        String sanitizedWarpName = WarpsHelper.SanitizeWarpName(args[0]);
        if (!WarpsHelper.IsValidWarpName(args[0])) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[!] Warp names must include at least one letter, number, underscore, or dash!");
            return true;
        }

        if (WarpsHelper.WarpExists(sanitizedWarpName)) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[!] A warp named " + ChatColor.DARK_AQUA + sanitizedWarpName + ChatColor.DARK_GRAY + " already exists!");
            return true;
        }

        Player self = Bukkit.getPlayer(sender.getName());
        Location warp = self.getLocation();
        WarpsHelper.CreateNewWarp(args[0], warp);
        if (!sanitizedWarpName.equals(args[0])) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[!] Removed unsupported characters. Using warp name " + ChatColor.DARK_AQUA + sanitizedWarpName + ChatColor.DARK_GRAY + ".");
        }
        sender.sendMessage(ChatColor.DARK_GRAY + "Created new warp " + ChatColor.DARK_AQUA + sanitizedWarpName + ChatColor.DARK_GRAY + "!");

        return true;
    }
}
