package me.brynview.navidrohim.sponge.impl;

import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.api.WSServer;
import me.brynview.navidrohim.common.network.packets.ActionPacket;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;

import java.util.Optional;
import java.util.UUID;

public class SpongeServer implements WSServer {

    public static Server nativeServer;

    public SpongeServer(Server server)
    {
        if (SpongeServer.nativeServer == null)
        {
            SpongeServer.nativeServer = server;
        } else {
            throw new RuntimeException("Server object already initialized. Cannot have more than one.");
        }

    }

    @Override
    public void sendActionCommandToClient(UUID uuid, ActionPacket packet)
    {

    }

    @Override
    public void sendActionCommandToClient(UUID uuid, String packetEncodable)
    {

    }

    @Override
    public WSPlayer getWSPlayer(UUID uuid) {
        Optional<ServerPlayer> serverPlayer = SpongeServer.nativeServer.player(uuid);
        return new SpongePlayer(serverPlayer.get());
    }

    @Override
    public WSPlayer getWSPlayer(String name) {
        Optional<ServerPlayer> serverPlayer = SpongeServer.nativeServer.player(name);
        return new SpongePlayer(serverPlayer.get());
    }
}
