package dev.lilycatgirl.moemx.commands;

import dev.lilycatgirl.moemx.utils.TpRequestHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTpReq implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        // check if they didn't provide a player
        if (args.length == 0) {
            sender.sendMessage(ChatColor.DARK_GRAY +""+ ChatColor.ITALIC + "[!] Please provide a player!");
            return false;
        }

        // get the player they provided
        Player toSend = Bukkit.getPlayer(args[0]);
        if (toSend == null) {
            sender.sendMessage(ChatColor.DARK_GRAY +""+ ChatColor.ITALIC + "[!] That player isn't online!");
            return false;
        }

        // check if the player has already sent a request
        Player self = Bukkit.getPlayer(sender.getName());
        if (TpRequestHelper.checkRequest(toSend, self)) {
            sender.sendMessage(ChatColor.DARK_GRAY +""+ ChatColor.ITALIC + "[!] You already sent that player a request!");
            return false;
        }

        // if all passes, send a request
        TpRequestHelper.CreateRequest(toSend, self);

        self.sendMessage(ChatColor.DARK_GRAY + "Sent a teleport request to " + ChatColor.DARK_AQUA + toSend.getName() + ChatColor.DARK_GRAY + ".");

        toSend.sendMessage(ChatColor.DARK_AQUA + sender.getName() + ChatColor.DARK_GRAY + " would like to teleport to you.");
        toSend.sendMessage(ChatColor.DARK_GRAY + "Type '" + ChatColor.DARK_AQUA + "/tpa" + ChatColor.DARK_GRAY + "' to accept, or '" + ChatColor.DARK_AQUA + "/tpd" + ChatColor.DARK_GRAY + "' to deny.");

        return true;
    }
}
