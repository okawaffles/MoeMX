package dev.lilycatgirl.moemx.events;

import com.google.gson.JsonObject;
import dev.lilycatgirl.moemx.qol.DetectAFK;
import dev.lilycatgirl.moemx.utils.http.PostMessagesToURL;
import org.bukkit.Bukkit;
import org.bukkit.advancement.AdvancementDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

public class EGetAchievement implements Listener {
    public EGetAchievement(JavaPlugin plg) {
        Bukkit.getPluginManager().registerEvents(this, plg);
    }

    @EventHandler
    public static void onAchievementGet(PlayerAdvancementDoneEvent e) {
        Player plr = e.getPlayer();
        AdvancementDisplay adv = e.getAdvancement().getDisplay();
        assert adv != null;


        CompletableFuture.runAsync(() -> {
            DetectAFK.UpdateLastActivity(e.getPlayer());

            // posting the message to a webserver
            JsonObject object = new JsonObject();
            object.addProperty("username", plr.getName());
            object.addProperty("name", adv.getTitle());
            object.addProperty("description", adv.getDescription());
            object.addProperty("type", "achievement");
            PostMessagesToURL.PostMessage(object.toString());
        });
    }
}
