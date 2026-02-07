package me.brynview.navidrohim.JMWSSpigot;

import me.brynview.navidrohim.JMWSSpigot.events.JMWSEvents;
import me.brynview.navidrohim.JMWSSpigot.server.payloads.ActionPayload;
import me.brynview.navidrohim.JMWSSpigot.server.payloads.HandshakePayload;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class JMWSSpigot extends JavaPlugin {

    public static Server server;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new JMWSEvents(), this);
        JMWSSpigot.server = getServer();
        JMWSSpigot.server.getMessenger().registerIncomingPluginChannel(this, Constants.ACTION_COMMAND, new ActionPayload());
        JMWSSpigot.server.getMessenger().registerIncomingPluginChannel(this, Constants.HANDSHAKE, new HandshakePayload());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
