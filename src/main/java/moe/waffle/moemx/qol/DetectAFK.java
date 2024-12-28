package moe.waffle.moemx.qol;

import com.google.gson.JsonObject;
import moe.waffle.moemx.utils.http.PostMessagesToURL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * Scheduled task that should run every 30 seconds or so
 * to check if a player has been active. If they haven't
 * we will send a broadcast informing that they are now
 * AFK.
 */
public class DetectAFK {
    private static HashMap<Player, Boolean> isAFK = new HashMap<>();
    private static HashMap<Player, Long> last_activity = new HashMap<>();

    /**
     * Register the check
     * @param plg MoeMX plugin
     */
    public static void Schedule(JavaPlugin plg) {
        Bukkit.getScheduler().runTaskTimer(plg, () -> {
            // current time since epoch. doesn't really matter as long as it stays
            // consistent from where it started.
            final Long unix_timestamp = System.currentTimeMillis() / 1000;
            final Long SECONDS_FOR_AFK = 60L * 5L; // 5 minutes = AFK

            Bukkit.getOnlinePlayers().forEach((player -> {
                // don't need to check if the player is already marked AFK
                if (isAFK.containsKey(player) && isAFK.get(player)) return;

                if (!last_activity.containsKey(player)) {
                    last_activity.put(player, 0L);
                    return;
                }

                Long last_action = last_activity.get(player);

                if (unix_timestamp > last_action + SECONDS_FOR_AFK) {
                    isAFK.put(player, true);

                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + player.getName() + ChatColor.DARK_GRAY + " is now AFK.");

                    CompletableFuture.runAsync(() -> {
                        // posting the info to okabot
                        JsonObject object = new JsonObject();
                        object.addProperty("username", player.getName());
                        object.addProperty("type", "afk");
                        PostMessagesToURL.PostMessage(object.toString());
                    });
                }
            }));
        }, 0L, 600L);
    }

    /**
     * Update the last activity for a player so they aren't marked AFK.
     * @param plr the player to update
     */
    public static void UpdateLastActivity(Player plr) {
        final Long unix_timestamp = System.currentTimeMillis() / 1000;

        last_activity.put(plr, unix_timestamp);

        if (isAFK.containsKey(plr) && isAFK.get(plr)) {
            isAFK.put(plr, false);

            Bukkit.broadcastMessage(ChatColor.DARK_AQUA + plr.getName() + ChatColor.DARK_GRAY + " is no longer AFK.");

            CompletableFuture.runAsync(() -> {
                // posting the info to okabot
                JsonObject object = new JsonObject();
                object.addProperty("username", plr.getName());
                object.addProperty("type", "unafk");
                PostMessagesToURL.PostMessage(object.toString());
            });
        }
    }

    /**
     * Manually mark a player AFK/non-AFK
     * @param plr the player to toggle
     */
    public static void ManuallyToggleAFK(Player plr) {
        if (!isAFK.containsKey(plr)) {
            // we set this to true because we will toggle later.
            // this will default to false if the player doesn't
            // exist in the hashmap.
            isAFK.put(plr, true);
        }

        // toggle
        isAFK.put(plr, false);

        if (isAFK.get(plr)) {
            Bukkit.broadcastMessage(ChatColor.DARK_AQUA + plr.getName() + ChatColor.DARK_GRAY + " is now AFK.");

            CompletableFuture.runAsync(() -> {
                // posting the info to okabot
                JsonObject object = new JsonObject();
                object.addProperty("username", plr.getName());
                object.addProperty("type", "afk");
                PostMessagesToURL.PostMessage(object.toString());
            });
        } else {
            UpdateLastActivity(plr);

            Bukkit.broadcastMessage(ChatColor.DARK_AQUA + plr.getName() + ChatColor.DARK_GRAY + " is no longer AFK.");

            CompletableFuture.runAsync(() -> {
                // posting the info to okabot
                JsonObject object = new JsonObject();
                object.addProperty("username", plr.getName());
                object.addProperty("type", "unafk");
                PostMessagesToURL.PostMessage(object.toString());
            });
        }
    }
}
