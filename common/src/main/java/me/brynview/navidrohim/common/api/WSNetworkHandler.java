package me.brynview.navidrohim.common.api;

import me.brynview.navidrohim.common.network.packets.ActionPacket;

public interface WSNetworkHandler {
    WSPlayer getNetworkOwner();
    void sendHandshake();
    void sendPacket(String channel, byte[] packetData);
    void sendPacket(String channel, WSPacket packet);

}
