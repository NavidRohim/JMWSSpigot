package me.brynview.navidrohim.common.api;

import me.brynview.navidrohim.common.network.packets.ActionPacket;

import java.util.UUID;

public interface WSPlayer {

    String getName();
    UUID getUUID();

    void sendHandshake();
    void sendActionCommand(String command);
    void sendActionCommand(ActionPacket command);

    WSNetworkHandler getNetworkHandler();
    WSServer getServer();
}
