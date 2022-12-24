package moe.waffle.moemx.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdGms implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player executor = (Player) sender;
        executor.setGameMode(GameMode.SURVIVAL);
        executor.sendMessage(ChatColor.DARK_GRAY + "Set your gamemode to " + ChatColor.DARK_AQUA + "SURVIVAL");

        return true;
    }
}
