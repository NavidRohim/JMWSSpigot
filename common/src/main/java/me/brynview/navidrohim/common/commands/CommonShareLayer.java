package me.brynview.navidrohim.common.commands;

import me.brynview.navidrohim.common.CommonClass;
import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.enums.ObjectType;

import java.util.Arrays;
import java.util.List;

public class CommonShareLayer {
    public static void doShareWaypoint(WSPlayer sender, List<String> args) {
        WSPlayer player = CommonClass.server.getWSPlayer(args.getFirst());
        String waypointName = args.getLast();
        ServerCommands.share(sender, player, waypointName, ObjectType.WAYPOINT);
    }
}
