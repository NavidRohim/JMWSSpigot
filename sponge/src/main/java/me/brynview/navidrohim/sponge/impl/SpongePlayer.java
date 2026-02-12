package me.brynview.navidrohim.sponge.impl;

import me.brynview.navidrohim.common.api.WSNetworkHandler;
import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.api.WSServer;
import me.brynview.navidrohim.common.network.packets.ActionPacket;
import me.brynview.navidrohim.sponge.JMWSSponge;
import org.spongepowered.api.Server;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;

import java.util.UUID;

public class SpongePlayer implements WSPlayer {

    public final Player nativePlayer;
    public final Server server;
    public final SpongeNetworkHandler networkHandler;

    public SpongePlayer(ServerPlayer nativePlayer) {
        this.nativePlayer = nativePlayer;
        this.server = JMWSSponge.getServer();
        this.networkHandler = new SpongeNetworkHandler();
    }

    @Override
    public String getName() {
        return this.nativePlayer.name();
    }

    @Override
    public UUID getUUID() {
        return this.nativePlayer.uniqueId();
    }

    @Override
    public void sendHandshake() {

    }

    @Override
    public void sendActionCommand(ActionPacket command) {

    }

    @Override
    public WSNetworkHandler getNetworkHandler() {
        return this.networkHandler;
    }

    @Override
    public WSServer getServer() {
        return null;
    }
}
