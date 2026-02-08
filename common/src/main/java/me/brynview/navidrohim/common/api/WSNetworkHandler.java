package me.brynview.navidrohim.common.api;

import me.brynview.navidrohim.common.network.packets.ActionPacket;

public interface WSNetworkHandler {
    WSPlayer getNetworkOwner();
    ActionPacket getActionPacket();
    void sendHandshake();
    void sendPacket(String channel, byte[] packetData);

}
