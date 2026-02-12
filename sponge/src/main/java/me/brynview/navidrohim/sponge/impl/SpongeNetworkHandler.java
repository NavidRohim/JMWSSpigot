package me.brynview.navidrohim.sponge.impl;

import me.brynview.navidrohim.common.api.WSNetworkHandler;
import me.brynview.navidrohim.common.api.WSPacket;
import me.brynview.navidrohim.common.api.WSPlayer;

public class SpongeNetworkHandler implements WSNetworkHandler {
    @Override
    public WSPlayer getNetworkOwner() {
        return null;
    }

    @Override
    public void sendHandshake() {

    }

    @Override
    public void sendPacket(String channel, byte[] packetData) {

    }

    @Override
    public void sendPacket(String channel, WSPacket packet) {

    }
}
