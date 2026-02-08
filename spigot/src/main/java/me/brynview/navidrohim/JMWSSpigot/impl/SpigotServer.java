package me.brynview.navidrohim.JMWSSpigot.impl;

import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.api.WSServer;
import me.brynview.navidrohim.common.network.packets.ActionPacket;
import org.bukkit.Server;
import org.bukkit.entity.Player;

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
        SpigotPlayer player = this.getWSPlayer(uuid);
        player.sendActionCommand(packet);

    }

    @Override
    public void sendActionCommandToClient(UUID uuid, String packetEncodable)
    {
        SpigotPlayer player = this.getWSPlayer(uuid);
        new ActionPacket(packetEncodable, player).send();
    }

    @Override
    public SpigotPlayer getWSPlayer(UUID uuid)
    {
        return new SpigotPlayer(this.getNativeServer().getPlayer(uuid));
    }

    public Server getNativeServer()
    {
        return server;
    }
}
