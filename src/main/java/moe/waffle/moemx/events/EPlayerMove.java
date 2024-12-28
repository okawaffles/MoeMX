package moe.waffle.moemx.events;

import moe.waffle.moemx.qol.DetectAFK;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

public class EPlayerMove implements Listener {
    public EPlayerMove(JavaPlugin plg) {
        Bukkit.getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent e) {
        DetectAFK.UpdateLastActivity(e.getPlayer());
    }
}
