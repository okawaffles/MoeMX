package dev.lilycatgirl.moemx.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.lang.Math.round;
import static dev.lilycatgirl.moemx.utils.PlayerInformation.checkPlayerDeath;
import static dev.lilycatgirl.moemx.utils.PlayerInformation.getPlayerLastDeath;

public class CmdLastDeath implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // check if the player has a deathlocation value:
        Player p = (Player) sender;
        if (!checkPlayerDeath(p)) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[!] You haven't died recently!");
            return true;
        } else {
            Location l = getPlayerLastDeath(p);
            sender.sendMessage(ChatColor.DARK_GRAY + "Your last death point was at X: " + ChatColor.DARK_AQUA + round(l.getX()) + ChatColor.DARK_GRAY + " Y: " + ChatColor.DARK_AQUA + round(l.getY()) + ChatColor.DARK_GRAY + " Z: "+ ChatColor.DARK_AQUA + round(l.getZ()));
        }
        return true;
    }
}
