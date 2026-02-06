package me.brynview.navidrohim.jMWSSpigot.common;

import com.comphenix.protocol.ProtocolManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Server;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class CommonClass {

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.

    public static Server minecraftServerInstance;

    public static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    public static ProtocolManager networkHandler;

    public static final Gson gson = new Gson();
    public static final Gson gsonExcludeNoExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static Server getMinecraftServerInstance()
    {
        return minecraftServerInstance;
    }

    public static void _createServerResources() {
        new File("./jmws").mkdir();
        new File("./jmws/groups").mkdir();
        new File("./jmws/users").mkdir();
    }

    /* TODO: PACKET
    public static void init() {

        Network.registerPacket(JMWSActionPayload.type(), JMWSActionPayload.class, JMWSActionPayload.STREAM_CODEC, CommonClass::_determinePacketAction);
        Network.registerPacket(JMWSHandshakePayload.type(), JMWSHandshakePayload.class, JMWSHandshakePayload.STREAM_CODEC, CommonClass::_determineHandshakePacketAction);

        if (Services.PLATFORM.side().equals("CLIENT") && Services.PLATFORM.getPlatformName().equals("Fabric"))
        {
            CommonClass.setupMinecraftClientInstance();
        }

        Constants.getLogger().info("Creating server resources..");
        ServerConfig.ensureExistence();
        _createServerResources();

        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.
    }*/
}
