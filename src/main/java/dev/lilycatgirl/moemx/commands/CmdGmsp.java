package dev.lilycatgirl.moemx.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdGmsp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player executor = (Player) sender;
        executor.setGameMode(GameMode.SPECTATOR);
        executor.sendMessage(ChatColor.DARK_GRAY + "Set your gamemode to " + ChatColor.DARK_AQUA + "SPECTATOR");

        return true;
    }
}
