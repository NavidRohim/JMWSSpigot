package me.brynview.navidrohim.common.events;

import me.brynview.navidrohim.common.api.JMWSPlayer;

public class CommonEvents {

    public static void handleJoin(JMWSPlayer serverPlayer)
    {
        // Send handshake to client. Can get rid of loads of client checks because plugin is server only.
        // Need to code packets for functionality
        System.out.println(serverPlayer.getName());

        //CommonClass.scheduler.schedule(() -> {PlayerHelper.sendUserAlert(Component.translatable("warning.jmws.world_is_local"), true, false, JMWSMessageType.NEUTRAL);}, 2, TimeUnit.SECONDS);
    }
}
