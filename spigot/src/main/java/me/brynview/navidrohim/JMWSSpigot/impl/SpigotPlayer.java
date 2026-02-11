package me.brynview.navidrohim.JMWSSpigot.impl;

import me.brynview.navidrohim.JMWSSpigot.JMWSSpigot;
import me.brynview.navidrohim.common.CommonClass;
import me.brynview.navidrohim.common.Constants;
import me.brynview.navidrohim.common.api.WSPacket;
import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.api.WSNetworkHandler;
import me.brynview.navidrohim.common.api.WSServer;
import me.brynview.navidrohim.common.network.packets.ActionPacket;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpigotPlayer implements WSPlayer
{
    private final Player nativePayerObj;
    private final SpigotServer server;

    private final SpigotNetworkHandler spigotNetworkHandler;

    public SpigotPlayer(Player nativePlayerObj)
    {
        this.server = JMWSSpigot.server;
        this.nativePayerObj = nativePlayerObj;
        this.spigotNetworkHandler = new SpigotNetworkHandler(this, nativePlayerObj);
    }

    @Override
    public boolean equals(Object o)
    {
        return (this.getClass().equals(o.getClass()) && this.getUUID().equals(((SpigotPlayer) o).getUUID()));
    }

    @Override
    public WSNetworkHandler getNetworkHandler() {
        return this.spigotNetworkHandler;
    }

    @Override
    public WSServer getServer() {
        return this.server;
    }

    @Override
    public String getName() {
        return this.nativePayerObj.getDisplayName();
    }

    @Override
    public UUID getUUID() {
        return nativePayerObj.getUniqueId();
    }

    @Override
    public void sendHandshake()
    {
        Bukkit.getScheduler().runTaskLater(JMWSSpigot.getPluginInstance(), this.getNetworkHandler()::sendHandshake, 20);
    }

    @Override
    public void sendActionCommand(ActionPacket command) {
        this.getNetworkHandler().sendPacket(ActionPacket.getChannel(), command.encode());
    }
}
