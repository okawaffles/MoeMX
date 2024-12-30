package dev.lilycatgirl.moemx.commands;

import dev.lilycatgirl.moemx.utils.TpRequestHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTpd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player from = TpRequestHelper.getRequestFor(Bukkit.getPlayer(sender.getName()));

        if (from == null) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[!] You have no incoming teleport requests!");
            return false;
        }

        sender.sendMessage(ChatColor.DARK_GRAY + "Rejected " + ChatColor.DARK_AQUA + from.getName() + ChatColor.DARK_GRAY + "'s teleport request.");

        TpRequestHelper.deleteRequest(Bukkit.getPlayer(sender.getName()), from, false);

        from.sendMessage(ChatColor.DARK_GRAY + "Your teleport request to " + ChatColor.DARK_AQUA + sender.getName() + ChatColor.DARK_GRAY + " was " + ChatColor.DARK_RED + "rejected" + ChatColor.DARK_GRAY + ".");

        return true;
    }
}
