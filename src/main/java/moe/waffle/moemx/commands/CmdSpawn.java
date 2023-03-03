package moe.waffle.moemx.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // get the main world's spawnpoint. this wont be a permanent solution though.
        Location spawnPoint = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
        sender.sendMessage(ChatColor.DARK_GRAY + "The spawnpoint of this world is " + ChatColor.DARK_AQUA + "X:" + Math.round(spawnPoint.getX()) + " Y:" + Math.round(spawnPoint.getX()) + ChatColor.DARK_GRAY + ".");

        // wish we could just do sender.teleport();
        Player executor = Bukkit.getPlayer(sender.getName());
        executor.teleport(spawnPoint);

        return true;
    }
}
