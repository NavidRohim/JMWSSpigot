package me.brynview.navidrohim.common.network.packets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.brynview.navidrohim.common.Constants;
import me.brynview.navidrohim.common.api.WSPacket;
import me.brynview.navidrohim.common.config.ServerConfig;
import me.brynview.navidrohim.common.network.Utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public final class HandshakePacket implements WSPacket {
    public static byte[] generateHandshake()
    {
        JsonObject jsonObject = JsonParser.parseString(ServerConfig.rawServerConfigData).getAsJsonObject();
        jsonObject.addProperty("serverVersion", Constants.SERVER_VERSION);

        String serverConfigDataJson = jsonObject.toString();
        return Utils.getByteArrayOutputStreamWithEncodedString(serverConfigDataJson);
    }
}
