package moe.waffle.moemx.events;

import moe.waffle.moemx.MoeMX;
import moe.waffle.moemx.utils.ChatColorFormatter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

import static moe.waffle.moemx.utils.Configuration.cfg;

public class EPlayerQuit implements Listener {
    public EPlayerQuit(MoeMX plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnPlayerQuit(PlayerQuitEvent e) {
        if (!cfg.getBoolean("customize.joinLeaveMessages.enabled")) return;

        String playerName = e.getPlayer().getName();
        String colorFormatted = ChatColorFormatter.FormatToChatColors(Objects.requireNonNull(cfg.getString("customize.joinLeaveMessages.leaveMessage")));

        e.setQuitMessage(String.format(colorFormatted, playerName));
    }
}
