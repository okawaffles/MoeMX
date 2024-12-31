package dev.lilycatgirl.moemx.commands;

import dev.lilycatgirl.moemx.MoeMX;
import dev.lilycatgirl.moemx.utils.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

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
            executor.sendMessage(ChatColor.DARK_AQUA + "MoeMX Coconut v" + MoeMX.getProvidingPlugin(MoeMX.class).getDescription().getVersion());
            executor.sendMessage(ChatColor.DARK_AQUA + "Run \"/moemx reload\" to reload the configuration.");
        }

        return true;
    }
}
