package me.brynview.navidrohim.common.api;

import me.brynview.navidrohim.common.network.packets.ActionPacket;

import java.util.Optional;
import java.util.UUID;

public interface WSServer {
    void sendActionCommandToClient(UUID uuid, ActionPacket packet);
    void sendActionCommandToClient(UUID uuid, String packetEncodable);
    void registerPacket(PacketFlow direction, String channel);

    Optional<WSPlayer> getWSPlayer(UUID uuid);
    Optional<WSPlayer> getWSPlayer(String name);

    WSPlayer getWSPlayerAssured(UUID uuid);
    WSPlayer getWSPlayerAssured(String name);
}
