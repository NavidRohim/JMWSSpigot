package me.brynview.navidrohim.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.brynview.navidrohim.common.config.ServerConfig;

import java.io.File;
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class CommonClass {

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.

    public static final Gson gson = new Gson();
    public static final Gson gsonExcludeNoExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static void _createServerResources() {
        new File("./jmws").mkdir();
        new File("./jmws/groups").mkdir();
        new File("./jmws/users").mkdir();
    }


    public static void init() {
        Constants.getLogger().info("Creating server resources..");
        ServerConfig.ensureExistence();
        _createServerResources();
        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.
    }
}
