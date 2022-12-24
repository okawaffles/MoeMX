package moe.waffle.moemx.events;

import moe.waffle.moemx.MoeMX;
import moe.waffle.moemx.utils.ChatColorFormatter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static moe.waffle.moemx.utils.Configuration.cfg;

public class EPlayerChat implements Listener {
    static MoeMX plg;
    public EPlayerChat(MoeMX plugin) {
        plg = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public static void onPlayerChat(AsyncPlayerChatEvent e) {
        if (!cfg.getBoolean("customize.chat.enabled")) return;

        String player = e.getPlayer().getName();
        String message = e.getMessage();
        String format = cfg.getString("customize.chat.format").replaceAll("\\{player\\}", "%1\\$s").replaceAll("\\{message\\}", "%2\\$s");

        e.setFormat(ChatColorFormatter.FormatToChatColors(format));
    }
}
