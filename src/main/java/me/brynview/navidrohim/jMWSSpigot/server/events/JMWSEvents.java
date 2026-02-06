package me.brynview.navidrohim.jMWSSpigot.server.events;

import me.brynview.navidrohim.jMWSSpigot.common.events.CommonEvents;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JMWSEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        CommonEvents.handleJoin(event.getPlayer());
    }
}
