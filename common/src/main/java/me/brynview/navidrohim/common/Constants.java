package me.brynview.navidrohim.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    private static class LoggerHolder {
        private static final Logger INSTANCE = LoggerFactory.getLogger(MODID);
    }

    public static Logger getLogger() {
        return LoggerHolder.INSTANCE;
    }

    public static final String MODID = "jmws";
    public static final String VERSION = "1.2.1-1.21.1"; // This is purely for display and is not needed
    public static final double SERVER_VERSION = 1.101;
    public static final boolean DEBUG = VERSION.contains("-beta.");


    // For anyone forking, this MUST be updated if there is a change that is in a future version of JM that you use.
    // beta 52 fixed the waypoint-drag-drop event, so this is the only version compatible (and any newer)
    public static final String ACTION_COMMAND = "%s:action_command".formatted(MODID);
    public static final String HANDSHAKE = "%s:jmws_handshake".formatted(MODID);
}
