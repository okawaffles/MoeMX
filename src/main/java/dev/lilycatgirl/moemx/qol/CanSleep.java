package dev.lilycatgirl.moemx.qol;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

public class CanSleep {
    private static boolean messageTriggered = false;

    public static void ScheduleSleepCheckTask(JavaPlugin plg) {
        // check if the player can sleep every second
        Bukkit.getScheduler().runTaskTimer(plg, () -> {
            World world = Bukkit.getWorld("world"); // assumes your world is named "world" lol
            if (world == null) return;

            long time = world.getTime();
            if (time > 12542 && !messageTriggered) {
                DoBossbarNotification(plg);
                messageTriggered = true;
            }

            if (time < 12000 && messageTriggered) {
                messageTriggered = false;
            }
        }, 0L, 20L);
    }

    private static void DoBossbarNotification(JavaPlugin plg) {
        BossBar bar = Bukkit.createBossBar(ChatColor.BLUE + "" + ChatColor.ITALIC + "you can now sleep!", BarColor.BLUE, BarStyle.SOLID);

        for (Player plr : Bukkit.getOnlinePlayers()) bar.addPlayer(plr);
        bar.setProgress(1.0);
        bar.setVisible(true);

        for (int i = 0; i <= 10; i++) {
            final int f = i;

            Bukkit.getScheduler().runTaskLater(plg, () -> {
                bar.setProgress(1.0 - (f*0.1));

                if (f == 10) {
                    bar.setVisible(false);
                    bar.removeAll();
                }
            }, 20L + (i*20L));
        }
    }
}
