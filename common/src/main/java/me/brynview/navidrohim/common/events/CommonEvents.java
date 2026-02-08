package me.brynview.navidrohim.common.events;

import me.brynview.navidrohim.common.api.WSPlayer;

public class CommonEvents {

    public static void handleJoin(WSPlayer serverPlayer)
    {
        // Send handshake to client. Can get rid of loads of client checks because plugin is server only.
        // Need to code packets for functionality
        serverPlayer.sendHandshake();
        //CommonClass.scheduler.schedule(() -> {PlayerHelper.sendUserAlert(Component.translatable("warning.jmws.world_is_local"), true, false, JMWSMessageType.NEUTRAL);}, 2, TimeUnit.SECONDS);
    }
}
