package me.brynview.navidrohim.JMWSSpigot.impl;

import me.brynview.navidrohim.JMWSSpigot.JMWSSpigot;
import me.brynview.navidrohim.common.Constants;
import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.api.WSNetworkHandler;
import me.brynview.navidrohim.common.network.packets.ActionPacket;
import me.brynview.navidrohim.common.network.packets.HandshakePacket;
import org.bukkit.entity.Player;

public class SpigotNetworkHandler implements WSNetworkHandler {

    private final WSPlayer networkOwner;
    private final Player player;

    public SpigotNetworkHandler(WSPlayer networkOwner, Player owner)
    {
        this.networkOwner = networkOwner;
        this.player = owner;

    }

    @Override
    public WSPlayer getNetworkOwner() {
        return this.networkOwner;
    }

    @Override
    public ActionPacket getActionPacket() {
        return null;
    }

    @Override
    public void sendHandshake()
    {
        this.sendPacket(Constants.HANDSHAKE, HandshakePacket.generateHandshake());
    }

    public void sendPacket(String channel, byte[] packetData)
    {
        this.player.sendPluginMessage(JMWSSpigot.getPluginInstance(), channel, packetData);
    }
}
