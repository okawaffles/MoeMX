package moe.waffle.moemx.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TpRequestHelper {
    // HashMap<toPlayer, fromPlayer>
    static private HashMap<Player, Player> requests = new HashMap<Player, Player>();

    public static void CreateRequest(Player to, Player from) {
        requests.put(to, from);
    }

    public static boolean checkRequest(Player to, Player from) {
        // does the To player have any incoming requests?
        if (!requests.containsKey(to)) return false;

        // if so, does the To player have any requests from that same person?
        return requests.get(to) == from;
    }

    public static Player getRequestFor(Player to) {
        return requests.get(to);
    }
    public static void deleteRequest(Player to, Player from, boolean accepted) {
        if (requests.remove(to, from) && accepted) {
            // get player location
            Location plrLoc = to.getLocation();
            from.teleport(plrLoc);
        }
    }
}
