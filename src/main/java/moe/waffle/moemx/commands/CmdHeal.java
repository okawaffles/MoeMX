package moe.waffle.moemx.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHeal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player executor = (Player) sender;

        if (args.length != 0) {
            try {
                Player toGet = Bukkit.getPlayer(args[0]);
                toGet.setHealth(executor.getHealthScale());
                toGet.setFoodLevel(20);
                executor.sendMessage(ChatColor.DARK_GRAY + "Healed " + ChatColor.DARK_AQUA + toGet.getName());
            } catch (NullPointerException err) {
                executor.sendMessage(ChatColor.DARK_GRAY+""+ChatColor.ITALIC + "[!] That player is not online!");
                return true;
            }
        } else {
            executor.setHealth(executor.getHealthScale());
            executor.setFoodLevel(20);
            executor.sendMessage(ChatColor.DARK_GRAY + "Healed you.");
        }

        return true;
    }
}
