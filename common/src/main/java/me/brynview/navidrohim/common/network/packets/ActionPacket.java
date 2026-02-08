package me.brynview.navidrohim.common.network.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.brynview.navidrohim.common.api.WSPacket;
import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.helper.CommandFactory;
import me.brynview.navidrohim.common.network.ServerPacketHandler;
import me.brynview.navidrohim.common.network.Utils;

import java.util.List;

public final class ActionPacket implements WSPacket {

    public byte[] data;
    public String rawString;
    public CommandFactory.Commands command = null;
    public List<JsonElement> argumentList = null;
    public WSPlayer player;

    public ActionPacket(byte[] jsonData, WSPlayer player)
    {
        ByteArrayDataInput in = ByteStreams.newDataInput(jsonData);
        this.rawString = Utils.readUtf(in);
        this.data = jsonData;
        this.player = player;

        _setCommandAndArguments();
    }

    private void _setCommandAndArguments()
    {
        JsonObject jsonifyied = CommandFactory.getJsonObjectFromJsonString(rawString);

        command = CommandFactory.Commands.valueOf(jsonifyied.asMap().get("command").getAsString());
        argumentList = jsonifyied.asMap().get("arguments").getAsJsonArray().asList();
    }

    public CommandFactory.Commands command() {
        return command;
    }

    public List<JsonElement> arguments() {
        return argumentList;
    }

    public void process()
    {
        ServerPacketHandler.handleIncomingActionCommand(this, this.player);
    }
}
