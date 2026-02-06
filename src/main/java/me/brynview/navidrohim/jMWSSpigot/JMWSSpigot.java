package me.brynview.navidrohim.jMWSSpigot;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import me.brynview.navidrohim.jMWSSpigot.common.CommonClass;
import me.brynview.navidrohim.jMWSSpigot.server.events.JMWSEvents;
import me.brynview.navidrohim.jMWSSpigot.server.payloads.ActionPayload;
import me.brynview.navidrohim.jMWSSpigot.server.payloads.HandshakePayload;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class JMWSSpigot extends JavaPlugin {

    public static Server server;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new JMWSEvents(), this);
        CommonClass.minecraftServerInstance = getServer();
        CommonClass.networkHandler = ProtocolLibrary.getProtocolManager();

        CommonClass.minecraftServerInstance.getMessenger().registerIncomingPluginChannel(this, Constants.ACTION_COMMAND, new ActionPayload());
        CommonClass.minecraftServerInstance.getMessenger().registerIncomingPluginChannel(this, Constants.HANDSHAKE, new HandshakePayload());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
