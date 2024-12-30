package dev.lilycatgirl.moemx.events;

import com.google.gson.JsonObject;
import dev.lilycatgirl.moemx.MoeMX;
import dev.lilycatgirl.moemx.utils.http.PostMessagesToURL;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.concurrent.CompletableFuture;

import static dev.lilycatgirl.moemx.utils.PlayerInformation.setPlayerLastDeath;

public class EPlayerDeath implements Listener {
    static MoeMX plg;
    public EPlayerDeath(MoeMX plugin) {
        plg = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent e) {
        // get the location of the death
        Location l = e.getEntity().getLastDeathLocation();
        // store it to the playerinformation
        setPlayerLastDeath(e.getEntity(), l);

        CompletableFuture.runAsync(() -> {
            // posting the message to a webserver
            JsonObject object = new JsonObject();
            object.addProperty("message", e.getDeathMessage());
            object.addProperty("type", "death");
            PostMessagesToURL.PostMessage(object.toString());
        });
    }
}
