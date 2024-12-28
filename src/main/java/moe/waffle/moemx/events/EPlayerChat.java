package moe.waffle.moemx.events;

import com.google.gson.JsonObject;
import moe.waffle.moemx.MoeMX;
import moe.waffle.moemx.utils.ChatColorFormatter;
import moe.waffle.moemx.utils.http.PostMessagesToURL;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.concurrent.CompletableFuture;

import static moe.waffle.moemx.utils.Configuration.cfg;

public class EPlayerChat implements Listener {
    static MoeMX plg;
    public EPlayerChat(MoeMX plugin) {
        plg = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public static void onPlayerChat(AsyncPlayerChatEvent e) {
        String format = cfg.getString("customize.chat.format").replaceAll("\\{player\\}", "%1\\$s").replaceAll("\\{message\\}", "%2\\$s");

        e.setFormat(ChatColorFormatter.FormatToChatColors(format));

        CompletableFuture.runAsync(() -> {
            // posting the message to a webserver
            JsonObject object = new JsonObject();
            object.addProperty("username", e.getPlayer().getName());
            object.addProperty("content", e.getMessage());
            object.addProperty("type", "chat");
            PostMessagesToURL.PostMessage(object.toString());
        });
    }
}
