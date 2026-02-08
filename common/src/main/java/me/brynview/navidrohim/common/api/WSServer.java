package me.brynview.navidrohim.common.api;

import me.brynview.navidrohim.common.network.packets.ActionPacket;

import java.util.UUID;

public interface WSServer {
    void sendActionCommandToClient(UUID uuid, ActionPacket packet);
    void sendActionCommandToClient(UUID uuid, String packetEncodable);
    WSPlayer getWSPlayer(UUID uuid);
    WSPlayer getWSPlayer(String name);
}
