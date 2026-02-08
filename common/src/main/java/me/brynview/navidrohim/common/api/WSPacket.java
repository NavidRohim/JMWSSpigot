package me.brynview.navidrohim.common.api;

import me.brynview.navidrohim.common.network.Utils;

public interface WSPacket {
    static byte[] encode(String string)
    {
        return Utils.getByteArrayOutputStreamWithEncodedString(string);
    }
}
