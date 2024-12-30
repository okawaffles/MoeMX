package dev.lilycatgirl.moemx.utils;

import dev.lilycatgirl.moemx.qol.DetectAFK;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessagingHelper {
    private static HashMap<Player, Player> last_messaged = new HashMap<Player, Player>();
    private static HashMap<Player, Boolean> is_spying = new HashMap<Player, Boolean>();

    public static void MessagePlayer(Player from, Player to, String content) {
        DetectAFK.UpdateLastActivity(from);

        // record who last messaged who, for the /r command
        last_messaged.put(from, to);
        last_messaged.put(to, from);

        // custom formatting cuz minecraft is boring
        String message_to_receiver = ChatColorFormatter.FormatToChatColors(String.format("&8[&3%s &8to &3me&8] &7&o%s", from.getName(), content));
        String message_from_sender = ChatColorFormatter.FormatToChatColors(String.format("&8[&3me &8to &3%s&8] &7&o%s", to.getName(), content));

        to.sendMessage(message_to_receiver);
        from.sendMessage(message_from_sender);

        is_spying.keySet().forEach((player -> {
            if (is_spying.get(player))
                player.sendMessage(ChatColorFormatter.FormatToChatColors(
                        String.format("&8&o(spying) [%s to %s] %s", from.getName(), to.getName(), content)
                ));
        }));
    }

    public static void HandleReplying(Player from, String content) {
        DetectAFK.UpdateLastActivity(from);

        if (!last_messaged.containsKey(from)) {
            from.sendMessage("&8[!] You haven't sent or received a message recently!");
            return;
        }

        Player to = last_messaged.get(from);

        if (to == null) {
            from.sendMessage(ChatColor.DARK_RED + "[!] Can't find that player! Have they gone offline?");
            return;
        }

        MessagePlayer(from, to, content);
    }

    public static void ToggleSpying(Player plr) {
        DetectAFK.UpdateLastActivity(plr);

        if (!is_spying.containsKey(plr) || !is_spying.get(plr)) {
            is_spying.put(plr, true);
            plr.sendMessage(ChatColor.DARK_GRAY + "Toggled message spying " + ChatColor.DARK_AQUA + "ON");
        } else {
            is_spying.put(plr, false);
            plr.sendMessage(ChatColor.DARK_GRAY + "Toggled message spying " + ChatColor.DARK_AQUA + "OFF");
        }
    }
}
