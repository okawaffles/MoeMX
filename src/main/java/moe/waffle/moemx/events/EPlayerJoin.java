package moe.waffle.moemx.events;

import static moe.waffle.moemx.utils.Configuration.cfg;

import com.google.gson.JsonObject;
import moe.waffle.moemx.MoeMX;
import moe.waffle.moemx.utils.ChatColorFormatter;
import moe.waffle.moemx.utils.http.PostMessagesToURL;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class EPlayerJoin implements Listener {
    static MoeMX plg;
    public EPlayerJoin(MoeMX plugin) {
        plg = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public static void OnPlayerJoin(PlayerJoinEvent e) {
        if (cfg.getBoolean("customize.joinLeaveMessages.enabled")) {
            String playerName = e.getPlayer().getName();
            String colorFormatted;

            // switch message if first play
            if (e.getPlayer().hasPlayedBefore())
                colorFormatted = ChatColorFormatter.FormatToChatColors(Objects.requireNonNull(cfg.getString("customize.joinLeaveMessages.joinMessage")));
            else
                colorFormatted = ChatColorFormatter.FormatToChatColors(Objects.requireNonNull(cfg.getString("customize.joinLeaveMessages.firstJoinMessage")));

            e.setJoinMessage(String.format(colorFormatted, playerName));
        }

        CompletableFuture.runAsync(() -> {
            // posting the message to a webserver
            JsonObject object = new JsonObject();
            object.addProperty("username", e.getPlayer().getName());
            object.addProperty("type", "join");
            PostMessagesToURL.PostMessage(object.toString());
        });

        // send MOTD to player if enabled in config.
        if (!cfg.getBoolean("customize.motd.enabled")) return;

        try {
            String playerName = e.getPlayer().getName();
            File motdFile = new File(plg.getDataFolder(), "motd.txt");
            String motd = Files.readString(Paths.get(motdFile.getAbsolutePath()));
            String fullString = ChatColorFormatter.FormatToChatColors(motd.replaceAll("\\{player\\}", playerName));

            String[] splitMsg = fullString.split(System.lineSeparator());

            for (String s : splitMsg) {
                e.getPlayer().sendMessage(s);
            }
        } catch (IOException err) {
            Bukkit.getLogger().severe(err.toString());
        }
    }
}
