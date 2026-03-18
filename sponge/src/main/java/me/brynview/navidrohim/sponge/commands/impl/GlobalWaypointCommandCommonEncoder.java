package me.brynview.navidrohim.sponge.commands.impl;

import me.brynview.navidrohim.common.enums.ObjectType;
import me.brynview.navidrohim.sponge.commands.api.SpongeArgumentType;
import org.spongepowered.api.command.parameter.Parameter;

import static me.brynview.navidrohim.sponge.commands.impl.ImplUtil.withCompletionListOfGlobalObjects;
import static me.brynview.navidrohim.sponge.commands.impl.ImplUtil.withCompletionListOfObjects;

public class GlobalWaypointCommandCommonEncoder extends WaypointCommandCommonEncoder {
    private static Parameter.Value.Builder<String> waypoint() {
        return withCompletionListOfGlobalObjects(ObjectType.WAYPOINT);
    }

    @Override
    public SpongeArgumentType<?> buildParameterForNative() {
        return new SpongeArgumentType<>(String.class, GlobalWaypointCommandCommonEncoder::waypoint);
    }
}
