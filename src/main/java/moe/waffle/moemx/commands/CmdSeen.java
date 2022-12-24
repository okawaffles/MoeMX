package moe.waffle.moemx.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSeen implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player executor = (Player) sender;
        OfflinePlayer toGet;

        try {
            toGet = Bukkit.getPlayer(args[0]);
        } catch (NullPointerException err) {
            toGet = Bukkit.getPlayer(args[0]);
            long lastSeen = toGet.getLastPlayed();
            long daysAgo = lastSeen / 86400000;
            executor.sendMessage(String.format(ChatColor.DARK_GRAY + "Player " + ChatColor.DARK_AQUA + "%s" + ChatColor.DARK_GRAY + " was last seen " + ChatColor.DARK_RED + "%d" + ChatColor.DARK_GRAY + "days ago.", toGet.getName(), daysAgo));
        }

        if (toGet.isOnline()) {
            executor.sendMessage(String.format(ChatColor.DARK_GRAY + "Player" + ChatColor.DARK_AQUA + "%s" + ChatColor.DARK_GRAY + " is currently " + ChatColor.GREEN + "ONLINE", toGet));
        } else {
            // finish later
        }

        return true;
    }
}
