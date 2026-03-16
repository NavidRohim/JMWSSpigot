package me.brynview.navidrohim.common.api.networking;

import me.brynview.navidrohim.common.api.game.WSPlayer;

public interface WSPacket {
    byte[] encode();
    WSPacket send();
    WSPlayer getRecipient();

    String getChannel();

}
