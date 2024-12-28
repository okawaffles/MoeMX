package moe.waffle.moemx.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class WarpTabCompleter implements TabCompleter {

    public WarpTabCompleter() {
        // do nothing ig
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> warpNames = WarpsHelper.GetWarpList(); // update warps list if need be

        if (args.length == 1) {
            List<String> suggestions = new ArrayList<>();
            String currentInput = args[0].toLowerCase();

            for (String warp : warpNames) {
                if (warp.toLowerCase().startsWith(currentInput)) suggestions.add(warp);
            }

            return suggestions;
        }

        return null;
    }
}
