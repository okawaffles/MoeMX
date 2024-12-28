package moe.waffle.moemx.qol;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class CanSleep {
    private static boolean messageTriggered = false;

    public static void SchedleSleepCheckTask(JavaPlugin plg) {
        // check if the player can sleep every second
        Bukkit.getScheduler().runTaskTimer(plg, () -> {
            World world = Bukkit.getWorld("world"); // assumes your world is named "world" lol
            if (world == null) return;

            long time = world.getTime();
            if (time > 12542 && !messageTriggered) {
                Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "psst...you can now sleep!");
                messageTriggered = true;
            }

            if (time < 12000 && messageTriggered) {
                messageTriggered = false;
            }
        }, 0L, 20L);
    }
}
