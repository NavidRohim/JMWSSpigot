package me.brynview.navidrohim.JMWSSpigot.events;

import me.brynview.navidrohim.common.events.CommonEvents;
import me.brynview.navidrohim.JMWSSpigot.impl.SpigotPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JMWSEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        CommonEvents.handleJoin(new SpigotPlayer(event.getPlayer()));
    }
}
