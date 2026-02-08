package me.brynview.navidrohim.common.network.packets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.brynview.navidrohim.common.Constants;
import me.brynview.navidrohim.common.api.WSPacket;
import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.config.ServerConfig;
import me.brynview.navidrohim.common.network.Utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public final class HandshakePacket implements WSPacket {

    public static String serverConfigClient;
    private final byte[] data;
    private final WSPlayer player;

    public static void setServerConfigForClient()
    {
        JsonObject jsonObject = JsonParser.parseString(ServerConfig.rawServerConfigData).getAsJsonObject();
        jsonObject.addProperty("serverVersion", Constants.SERVER_VERSION);

        HandshakePacket.serverConfigClient = jsonObject.toString();
    }

    public HandshakePacket(WSPlayer player)
    {
        this.player = player;
        this.data = Utils.getByteArrayOutputStreamWithEncodedString(HandshakePacket.serverConfigClient);
    }

    @Override
    public byte[] encode() {
        return this.data;
    }

    @Override
    public HandshakePacket send() {
        this.player.getNetworkHandler().sendPacket(HandshakePacket.getChannel(),this);
        return this;
    }

    @Override
    public WSPlayer getRecipient() {
        return this.player;
    }

    public static String getChannel()
    {
        return "%s:jmws_handshake".formatted(Constants.MODID);
    }
}
