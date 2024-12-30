package dev.lilycatgirl.moemx.events;

import dev.lilycatgirl.moemx.qol.DetectAFK;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EPlayerMove implements Listener {
    public EPlayerMove(JavaPlugin plg) {
        Bukkit.getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent e) {
        DetectAFK.UpdateLastActivity(e.getPlayer());
    }
}
