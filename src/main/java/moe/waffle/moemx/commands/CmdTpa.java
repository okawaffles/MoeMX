package moe.waffle.moemx.commands;

import moe.waffle.moemx.utils.TpRequestHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTpa implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player from = TpRequestHelper.getRequestFor(Bukkit.getPlayer(sender.getName()));

        if (from == null) {
            sender.sendMessage(ChatColor.DARK_GRAY + "[!] You have no incoming teleport requests!");
            return false;
        }

        TpRequestHelper.deleteRequest(Bukkit.getPlayer(sender.getName()), from, true);

        return true;
    }
}
