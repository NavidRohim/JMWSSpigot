package me.brynview.navidrohim.common.helper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.brynview.navidrohim.common.CommonClass;
import me.brynview.navidrohim.common.enums.JMWSMessageType;
import me.brynview.navidrohim.common.enums.ObjectType;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommandFactory {
    public static class PacketCommand {
        Commands command;
        Object[] arguments;

        public PacketCommand(Commands command, Object... arguments) {
            this.command = command;
            this.arguments = arguments;
        }
    }

    public static String makeBaseJsonRequest(Commands command, Object... arguments) {
       return CommonClass.gson.toJson(new PacketCommand(command, arguments));
    }

    public static String makeDeleteRequestJson(String waypointIdentifier, boolean silent, boolean all) {
        return CommandFactory.makeBaseJsonRequest(Commands.COMMON_DELETE_WAYPOINT, waypointIdentifier, silent, all);
    }

    public static String makeDeleteGroupRequestJson(String groupUniversalIdentifier, String groupGUID, boolean silent, boolean removeAllWaypointsInGroup, boolean removeGroupItself, boolean isGlobal, boolean deleteAllGroups) {
        return CommandFactory.makeBaseJsonRequest(Commands.COMMON_DELETE_GROUP,
                groupUniversalIdentifier,
                groupGUID,
                silent,
                removeAllWaypointsInGroup,
                removeGroupItself,
                isGlobal,
                deleteAllGroups);
    }

    public static String makeWaypointSyncRequestJson(boolean sendAlert, boolean isForDeathSync) {
        return CommandFactory.makeBaseJsonRequest(Commands.SYNC, Map.of(), Map.of(), sendAlert, isForDeathSync);
    }

    public static String makeSyncRequestResponseJson(HashMap<String, String> jsonArray, HashMap<String, String> jsonGroupArray, boolean sendAlert, boolean isDeathSync) {
        return CommandFactory.makeBaseJsonRequest(Commands.SYNC, jsonArray, jsonGroupArray, sendAlert, isDeathSync);
    }

    public static String makeClientAlertRequestJson(String message, boolean overlay, JMWSMessageType messageType) {
        return CommandFactory.makeBaseJsonRequest(Commands.CLIENT_ALERT, message, overlay, messageType);
    }

    public static String makeTransitionObjectRequest(String objectIdentifier, String filename, ObjectType transitionType)
    {
        return CommandFactory.makeBaseJsonRequest(Commands.TRANSITION, objectIdentifier, filename, transitionType);
    }

    public static JsonObject getJsonObjectFromJsonString(String jsonString) {
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }

    /**
     * Enums for different packet commands
     */

    public enum Commands {

        // Waypoint handling
        SERVER_CREATE,
        COMMON_DELETE_WAYPOINT,

        // Group handling
        SERVER_CREATE_GROUP,
        COMMON_DELETE_GROUP,

        // Utility
        SYNC,
        REQUEST_CLIENT_SYNC,
        CLIENT_ALERT,
        COMMON_DISPLAY_INTERVAL,
        COMMON_DISPLAY_NEXT_UPDATE,

        // Object sharing
        OBJECT_SHARE, // Share waypoint / group
        AFFIRM_SHARE, // Confirm user wants shared object
        REJECT_SHARE, // User doesnt want shared object.

        // Object sharing errors
        USER_ALREADY_PROCESSING_SHARE, // User is already processing another share request

        UPDATE,
        TRANSITION
    }
}
