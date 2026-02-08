package me.brynview.navidrohim.JMWSSpigot.commands.completions;

import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.enums.ObjectType;
import me.brynview.navidrohim.common.io.JMWSServerIO;
import me.brynview.navidrohim.common.objects.ServerObject;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ObjectSuggestions {

    public static HashMap<String, ServerObject> getUserObjectsAsNameHashmap(UUID playerUUID, ObjectType objectType, boolean global, boolean onlyShared)
    {
        HashMap<String, ServerObject> stringServerObjectHashMap = new HashMap<>();
        for (ServerObject object : JMWSServerIO.getObjectsForUser(playerUUID, objectType, global))
        {
            String nonDupeIdentifier = object.getObjectNonDuplicateIdentifier();
            if ((!object.syncing.isGlobal() && !onlyShared) || global || (onlyShared && !object.syncing.sharedTo.isEmpty()))
            {
                stringServerObjectHashMap.put(nonDupeIdentifier, object);
            }
        }

        return stringServerObjectHashMap;
    }


    private static List<String> suggestObject(ObjectType objectType, UUID forPlayerUUID)
    {
        return getUserObjectsAsNameHashmap(forPlayerUUID, objectType, false, false).keySet().stream().toList();
    }

    private static List<String> suggestGlobalObject(ObjectType objectType, UUID forPlayerUUID)
    {
        return getUserObjectsAsNameHashmap(forPlayerUUID, objectType, true, false).keySet().stream().toList();

    }

    private static List<String> suggestSharedObject(ObjectType objectType, UUID forPlayerUUID)
    {
        return getUserObjectsAsNameHashmap(forPlayerUUID, objectType, false, true).keySet().stream().toList();
    }



    public static List<String> suggestWaypoints(UUID forPlayerUUID) {
        return suggestObject(ObjectType.WAYPOINT, forPlayerUUID);
    }

    public static List<String> suggestGroups(UUID forPlayerUUID) {
        return suggestObject(ObjectType.GROUP, forPlayerUUID);
    }

    public static List<String> suggestGlobalGroups(UUID forPlayerUUID) {
        return suggestGlobalObject(ObjectType.GROUP, forPlayerUUID);
    }

    public static List<String> suggestGlobalWaypoints(UUID forPlayerUUID) {
        return suggestGlobalObject(ObjectType.WAYPOINT, forPlayerUUID);
    }

    public static List<String> suggestSharedGroups(UUID forPlayerUUID) {
        return suggestSharedObject(ObjectType.GROUP, forPlayerUUID);
    }

    public static List<String> suggestSharedWaypoints(UUID forPlayerUUID) {
        return suggestSharedObject(ObjectType.WAYPOINT, forPlayerUUID);
    }

    public static List<String> suggestOnlineUsers()
    {
        //return sender.getServer().getOnlinePlayers().forEach(x -> completions.add(x.getName()));
        return List.of();
    }
}
