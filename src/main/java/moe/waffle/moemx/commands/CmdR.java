package moe.waffle.moemx.commands;

import moe.waffle.moemx.utils.MessagingHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdR implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        StringBuilder content = new StringBuilder();

        if (args.length == 0) {
            sender.sendMessage(ChatColor.DARK_RED + "[!] Please supply your message!");
            return true;
        }

        for (String arg : args) {
            content.append(arg).append(" ");
        }

        MessagingHelper.HandleReplying((Player) sender, content.toString());

        return true;
    }
}
