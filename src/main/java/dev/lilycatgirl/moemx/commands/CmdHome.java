package dev.lilycatgirl.moemx.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static dev.lilycatgirl.moemx.utils.PlayerInformation.getPlayerHome;

public class CmdHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Location home = getPlayerHome((Player) sender);
        ((Player) sender).teleport(home);

        return true;
    }
}
