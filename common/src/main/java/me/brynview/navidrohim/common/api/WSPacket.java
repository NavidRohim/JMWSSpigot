package me.brynview.navidrohim.common.api;

import me.brynview.navidrohim.common.network.Utils;

public interface WSPacket {
    byte[] encode();
    WSPacket send();
    WSPlayer getRecipient();

    static String getChannel() {return null;}

}
