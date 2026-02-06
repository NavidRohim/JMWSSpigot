package me.brynview.navidrohim.jMWSSpigot.common.events;

import org.bukkit.entity.Player;

public class CommonEvents {

    public static void handleJoin(Player serverPlayer)
    {
        // Send handshake to client. Can get rid of loads of client checks because plugin is server only.
        // Need to code packets for functionality
        System.out.println(serverPlayer.getDisplayName());

        //CommonClass.scheduler.schedule(() -> {PlayerHelper.sendUserAlert(Component.translatable("warning.jmws.world_is_local"), true, false, JMWSMessageType.NEUTRAL);}, 2, TimeUnit.SECONDS);
    }
}
