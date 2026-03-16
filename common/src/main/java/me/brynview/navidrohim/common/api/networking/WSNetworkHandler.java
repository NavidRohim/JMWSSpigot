package me.brynview.navidrohim.common.api.networking;

import me.brynview.navidrohim.common.api.game.WSPlayer;

public interface WSNetworkHandler {
    WSPlayer getNetworkOwner();
    void sendHandshake();
    void sendPacket(String channel, byte[] packetData);
    void sendPacket(String channel, WSPacket packet);

}
