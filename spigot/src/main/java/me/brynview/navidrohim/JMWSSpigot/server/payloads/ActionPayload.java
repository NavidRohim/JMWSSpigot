package me.brynview.navidrohim.JMWSSpigot.server.payloads;


import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.brynview.navidrohim.JMWSSpigot.impl.SpigotPlayer;
import me.brynview.navidrohim.common.Constants;
import me.brynview.navidrohim.common.helper.CommandFactory;
import me.brynview.navidrohim.common.network.Utils;
import me.brynview.navidrohim.common.network.packets.ActionPacket;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.List;

public class ActionPayload implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(@NonNull String s, @NonNull Player player, byte @NonNull [] bytes) {
        ActionPacket packet = new ActionPacket(bytes, new SpigotPlayer(player));
        packet.process();
    }
}
