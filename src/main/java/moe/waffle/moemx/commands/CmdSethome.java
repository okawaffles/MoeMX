package moe.waffle.moemx.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static moe.waffle.moemx.utils.PlayerInformation.setPlayerHome;

public class CmdSethome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Location l = ((Player) sender).getLocation();
        setPlayerHome((Player) sender, l);

        return true;
    }
}
