package me.brynview.navidrohim.JMWSSpigot;

import me.brynview.navidrohim.JMWSSpigot.events.JMWSEvents;
import me.brynview.navidrohim.JMWSSpigot.impl.SpigotServer;
import me.brynview.navidrohim.JMWSSpigot.server.payloads.ActionPayload;
import me.brynview.navidrohim.JMWSSpigot.server.payloads.HandshakePayload;
import me.brynview.navidrohim.common.CommonClass;
import me.brynview.navidrohim.common.Constants;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class JMWSSpigot extends JavaPlugin {

    public static SpigotServer server;
    public static JMWSSpigot plugin;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new JMWSEvents(), this);
        JMWSSpigot.server = new SpigotServer(getServer());
        JMWSSpigot.plugin = this;

        CommonClass.init();
        this.registerNetworkChannels();
    }

    private void registerNetworkChannels()
    {
        server.getNativeServer().getMessenger().registerOutgoingPluginChannel(this, Constants.HANDSHAKE);
        server.getNativeServer().getMessenger().registerOutgoingPluginChannel(this, Constants.ACTION_COMMAND);
        server.getNativeServer().getMessenger().registerIncomingPluginChannel(this, Constants.ACTION_COMMAND, new ActionPayload());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JMWSSpigot getPluginInstance() {
        return JMWSSpigot.plugin;
    }
}
