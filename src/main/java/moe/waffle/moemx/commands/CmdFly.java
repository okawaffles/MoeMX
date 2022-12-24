package moe.waffle.moemx.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdFly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player executor = (Player) sender;

        if (executor.getGameMode().equals(GameMode.CREATIVE) || executor.getGameMode().equals(GameMode.SPECTATOR)) {
            executor.sendMessage(ChatColor.DARK_GRAY +""+ ChatColor.ITALIC + "[!] This command has no effect in the gamemode you are in.");
            return true;
        }

        if (executor.getAllowFlight()) {
            executor.sendMessage(ChatColor.DARK_GRAY + "Set fly mode to " + ChatColor.DARK_AQUA + "DISABLED");
            executor.setFlying(false);
            executor.setAllowFlight(!executor.getAllowFlight());
        } else {
            executor.sendMessage(ChatColor.DARK_GRAY + "Set fly mode to " + ChatColor.DARK_AQUA + "ENABLED");
            executor.setAllowFlight(!executor.getAllowFlight());
            executor.setFlying(true);
        }

        return true;
    }
}
