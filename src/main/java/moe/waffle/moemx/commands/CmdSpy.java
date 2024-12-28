package moe.waffle.moemx.commands;

import moe.waffle.moemx.utils.MessagingHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSpy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessagingHelper.ToggleSpying((Player) sender);
        return true;
    }
}
