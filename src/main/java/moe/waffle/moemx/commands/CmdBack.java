package moe.waffle.moemx.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static moe.waffle.moemx.utils.PlayerInformation.getPreviousLocation;

public class CmdBack implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        // Get previous location of player
        Player p = (Player) sender;
        Location l = getPreviousLocation(p);
        // Teleport 'em.
        p.teleport(l);
        return true;
    }
}
