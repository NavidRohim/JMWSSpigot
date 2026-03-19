package me.brynview.navidrohim.sponge.commands.impl;

import me.brynview.navidrohim.common.enums.ObjectType;
import me.brynview.navidrohim.common.objects.ServerWaypoint;
import me.brynview.navidrohim.sponge.commands.api.ObjectShareType;
import me.brynview.navidrohim.sponge.commands.api.SpongeArgumentType;
import me.brynview.navidrohim.sponge.commands.api.SpongeCommandCommonEncoder;
import org.spongepowered.api.command.parameter.Parameter;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static me.brynview.navidrohim.sponge.commands.impl.ImplUtil.*;

public class WaypointCommandCommonEncoder implements SpongeCommandCommonEncoder {

    private final ObjectShareType objectShareType;

    public WaypointCommandCommonEncoder(ObjectShareType encoderType)
    {
        this.objectShareType = encoderType;
    }

    public WaypointCommandCommonEncoder()
    {
        this.objectShareType = ObjectShareType.DEFAULT;
    }

    private static Parameter.Value.Builder<String> waypoints() {
        return withCompletionListOfObjects(ObjectType.WAYPOINT);
    }

    private static Parameter.Value.Builder<String> globalWaypoints() {
        return withCompletionListOfGlobalObjects(ObjectType.WAYPOINT);
    }

    private static Parameter.Value.Builder<String> sharedWaypoints() {
        return withCompletionListOfSharedObjects(ObjectType.WAYPOINT);
    }

    @Override
    public SpongeArgumentType<?> buildParameterForNative() {
        Supplier<Parameter.Value.Builder<String>> supplier;

        if (this.objectShareType == ObjectShareType.DEFAULT) {
            supplier = WaypointCommandCommonEncoder::waypoints;
        } else if (this.objectShareType == ObjectShareType.SHARED) {
            supplier = WaypointCommandCommonEncoder::sharedWaypoints;
        } else {
            supplier = WaypointCommandCommonEncoder::globalWaypoints;
        }

        return new SpongeArgumentType<>(String.class, supplier);
    }

    @Override
    public Optional<ServerWaypoint> getCommonParameterValue(Object value, UUID commandSenderUUID) {
        return ObjectType.WAYPOINT.getServerObject(value.toString(), commandSenderUUID);
    }
}
