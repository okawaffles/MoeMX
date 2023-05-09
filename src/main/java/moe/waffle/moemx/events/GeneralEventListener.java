package moe.waffle.moemx.events;

import moe.waffle.moemx.MoeMX;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import static moe.waffle.moemx.utils.PlayerInformation.setPreviousLocation;

/**
 * This class helps with player info recording for commands like /back
 */
public class GeneralEventListener implements Listener {
    static MoeMX plg;
    public GeneralEventListener(MoeMX plugin) {
        plg = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public static void onPlayerTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        setPreviousLocation(p, p.getLocation());
    }
}
