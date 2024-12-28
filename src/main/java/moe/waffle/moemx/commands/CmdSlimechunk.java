package moe.waffle.moemx.commands;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class CmdSlimechunk implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Chunk chunk = ((Player) sender).getLocation().getChunk();
        Long seed = ((Player) sender).getWorld().getSeed();

        Random rnd = new Random(
                seed +
                        (int) (chunk.getX() * chunk.getX() * 0x4c1906) +
                        (int) (chunk.getX() * 0x5ac0db) +
                        (int) (chunk.getZ() * chunk.getZ()) * 0x4307a7L +
                        (int) (chunk.getZ() * 0x5f24f) ^ 0x3ad8025fL
        );

        if (rnd.nextInt(10) == 0) {
            sender.sendMessage(ChatColor.DARK_GRAY + "Yay! Chunk " + ChatColor.DARK_AQUA + "[" + chunk.getX() + ", " + chunk.getZ() + "] " + ChatColor.DARK_GRAY + "is a slime chunk!");
        } else {
            sender.sendMessage(ChatColor.DARK_GRAY + "Aww! Chunk " + ChatColor.DARK_AQUA + "[" + chunk.getX() + ", " + chunk.getZ() + "] " + ChatColor.DARK_GRAY + "is not slime chunk.");
        }

        return true;
    }
}
