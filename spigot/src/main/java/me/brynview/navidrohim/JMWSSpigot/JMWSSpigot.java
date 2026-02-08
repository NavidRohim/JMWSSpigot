package me.brynview.navidrohim.JMWSSpigot;

import me.brynview.navidrohim.JMWSSpigot.commands.AdminCommands;
import me.brynview.navidrohim.JMWSSpigot.commands.SharingCommands;
import me.brynview.navidrohim.JMWSSpigot.commands.completions.SharingCommandCompletions;
import me.brynview.navidrohim.JMWSSpigot.events.JMWSEvents;
import me.brynview.navidrohim.JMWSSpigot.impl.SpigotPlayer;
import me.brynview.navidrohim.JMWSSpigot.impl.SpigotServer;
import me.brynview.navidrohim.common.CommonClass;
import me.brynview.navidrohim.common.network.packets.ActionPacket;
import me.brynview.navidrohim.common.network.packets.HandshakePacket;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jspecify.annotations.NonNull;

public final class JMWSSpigot extends JavaPlugin implements PluginMessageListener {

    public static SpigotServer server;
    public static JMWSSpigot plugin;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new JMWSEvents(), this);
        JMWSSpigot.server = new SpigotServer(getServer());
        JMWSSpigot.plugin = this;

        CommonClass.init(JMWSSpigot.server);
        this.registerNetworkChannels();
        this.registerAdminCommands();

    }

    private void registerAdminCommands() {
        SharingCommands sharingCommands = new SharingCommands();
        AdminCommands adminCommands = new AdminCommands();

        SharingCommandCompletions sharingCommandCompletions = new SharingCommandCompletions();

        PluginCommand shareWaypoint = getCommand("share_waypoint");
        PluginCommand shareGroup = getCommand("share_group");
        PluginCommand stopSharingWaypoint = getCommand("stop_sharing_waypoint");
        PluginCommand stopSharingGroup = getCommand("stop_sharing_group");

        shareWaypoint.setTabCompleter(sharingCommandCompletions);
        shareWaypoint.setExecutor(sharingCommands);

        shareGroup.setTabCompleter(sharingCommandCompletions);
        shareGroup.setExecutor(sharingCommands);

        stopSharingWaypoint.setTabCompleter(sharingCommandCompletions);
        stopSharingWaypoint.setExecutor(sharingCommands);

        stopSharingGroup.setTabCompleter(sharingCommandCompletions);
        stopSharingGroup.setExecutor(sharingCommands);
    }
    private void registerNetworkChannels()
    {
        server.getNativeServer().getMessenger().registerOutgoingPluginChannel(this, HandshakePacket.getChannel());
        server.getNativeServer().getMessenger().registerOutgoingPluginChannel(this, ActionPacket.getChannel());
        server.getNativeServer().getMessenger().registerIncomingPluginChannel(this, ActionPacket.getChannel(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JMWSSpigot getPluginInstance() {
        return JMWSSpigot.plugin;
    }

    @Override
    public void onPluginMessageReceived(@NonNull String channel, @NonNull Player player, byte @NonNull [] message) {
        ActionPacket packet = new ActionPacket(message, new SpigotPlayer(player));
        packet.process();
    }
}
