package me.brynview.navidrohim.spigot.impl;

import me.brynview.navidrohim.spigot.JMWSSpigot;
import me.brynview.navidrohim.common.api.PacketFlow;
import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.api.WSServer;
import me.brynview.navidrohim.common.network.packets.ActionPacket;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.Messenger;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class SpigotServer implements WSServer {
    public static Server server = null;

    public SpigotServer(Server server)
    {
        if (SpigotServer.server == null)
        {
            SpigotServer.server = server;
        } else {
            throw new RuntimeException("Cannot initialise more than one instance of SpigotServer.");
        }
    }


    @Override
    public void sendActionCommandToClient(UUID uuid, ActionPacket packet)
    {
        Optional<WSPlayer> player = this.getWSPlayer(uuid);
        player.ifPresent(wsPlayer -> wsPlayer.sendActionCommand(packet));

    }

    @Override
    public void sendActionCommandToClient(UUID uuid, String packetEncodable)
    {
        Optional<WSPlayer> player = this.getWSPlayer(uuid);
        player.ifPresent(wsPlayer -> new ActionPacket(packetEncodable, wsPlayer).send());
    }

    @Override
    public void registerPacket(PacketFlow direction, String channel)
    {
        JMWSSpigot plugin = JMWSSpigot.getPluginInstance();
        Messenger messenger = JMWSSpigot.server.getNativeServer().getMessenger();

        if (direction == PacketFlow.OUTGOING)
        {
            messenger.registerOutgoingPluginChannel(plugin, channel);
        } else if  (direction == PacketFlow.INCOMING)
        {
            messenger.registerIncomingPluginChannel(plugin, channel, plugin);
        }
    }

    @Override
    public Optional<WSPlayer> getWSPlayer(UUID uuid)
    {
        @Nullable Player player = this.getNativeServer().getPlayer(uuid);
        if (player != null) {
            return Optional.of(new SpigotPlayer(player));
        }
        return Optional.empty();
    }

    @Override
    public Optional<WSPlayer> getWSPlayer(String name) {
        @Nullable Player player = this.getNativeServer().getPlayerExact(name);
        if (player != null) {
            return Optional.of(new SpigotPlayer(player));
        }
        return Optional.empty();
    }

    @Override
    public WSPlayer getWSPlayerAssured(UUID uuid) {
        return null;
    }

    @Override
    public WSPlayer getWSPlayerAssured(String name) {
        return null;
    }

    public Server getNativeServer()
    {
        return server;
    }
}
