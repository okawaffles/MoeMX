package moe.waffle.moemx.commands;

import moe.waffle.moemx.utils.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdMoeMX implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equals("reload")) {
            if (sender instanceof Player) {
                Player executor = (Player) sender;
                executor.sendMessage(ChatColor.DARK_AQUA+""+ChatColor.ITALIC + "Reloading MoeMX...");
            }
            Configuration.reloadConfig();
            return true;
        }

        if (sender instanceof Player) {
            Player executor = (Player) sender;
            executor.sendMessage(ChatColor.DARK_AQUA + "MoeMX v0.7.0-beta");
            executor.sendMessage(ChatColor.DARK_AQUA + "Run \"/moemx reload\" to reload the configuration.");
        }

        return true;
    }
}
