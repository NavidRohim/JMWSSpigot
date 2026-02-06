package me.brynview.navidrohim.jMWSSpigot.server.payloads;

import me.brynview.navidrohim.jMWSSpigot.Constants;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jspecify.annotations.NonNull;

public class ActionPayload implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(@NonNull String s, @NonNull Player player, @NonNull byte[] bytes) {
        Constants.getLogger().info("Got action %s".formatted(s));
    }
}
