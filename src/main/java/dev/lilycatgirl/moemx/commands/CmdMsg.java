package dev.lilycatgirl.moemx.commands;

import dev.lilycatgirl.moemx.utils.MessagingHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdMsg implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.DARK_RED + "[!] Please supply who you want to message!");
            return true;
        } else if (args.length < 2) {
            sender.sendMessage(ChatColor.DARK_RED + "[!] Please supply your message!");
            return true;
        }

        Player to = Bukkit.getPlayer(args[0]);

        if (to == null) {
            sender.sendMessage(ChatColor.DARK_RED + "[!] Can't find that player, are they online?");
            return true;
        }

        StringBuilder content = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            content.append(args[i]).append(" ");
        }

        MessagingHelper.MessagePlayer((Player) sender, to, content.toString());

        return true;
    }
}
