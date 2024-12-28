package moe.waffle.moemx.events;

import moe.waffle.moemx.qol.DetectAFK;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

public class EPlayerInteract implements Listener {
    public EPlayerInteract(JavaPlugin plg) {
        Bukkit.getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent e) {
        DetectAFK.UpdateLastActivity(e.getPlayer());
    }
}
