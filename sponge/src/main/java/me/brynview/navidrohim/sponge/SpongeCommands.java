package me.brynview.navidrohim.sponge;

import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.parameter.Parameter;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.plugin.PluginContainer;

import java.util.List;

public class SpongeCommands {

    private record CommandNameWrapper(Command.Parameterized command, String name) {}

    public static void register(final RegisterCommandEvent<Command.Parameterized> event, PluginContainer container) {
        // TODO Add autocompletion results to object parameters

        final Parameter.Value<String> waypointParameter = Parameter.string().key("waypoint").build();
        final Parameter.Value<String> groupParameter = Parameter.string().key("group").build();

        final Parameter.Value<String> sharedWaypointParameter = Parameter.string().key("shared_waypoint").build();
        final Parameter.Value<String> sharedGroupParameter = Parameter.string().key("shared_group").build();

        final Parameter.Value<String> globalWaypointParameter = Parameter.string().key("global_waypoint").build();
        final Parameter.Value<String> globalGroupParameter = Parameter.string().key("global_group").build();

        final Parameter.Value<String> playerParam = Parameter.string().key("player").build();

        List<CommandNameWrapper> commands = List.of(
                new CommandNameWrapper(
                        Command.builder()
                                .addParameter(playerParam)
                                .addParameter(waypointParameter)
                                .build(),
                        "share_waypoint"
                ),
                new CommandNameWrapper(
                        Command.builder()
                                .addParameter(playerParam)
                                .addParameter(groupParameter)
                                .build(),
                        "share_group"
                ),
                new CommandNameWrapper(
                        Command.builder()
                                .addParameter(sharedWaypointParameter)
                                .build(),
                        "stop_sharing_waypoint"
                ),
                new CommandNameWrapper(
                        Command.builder()
                                .addParameter(sharedGroupParameter)
                                .build(),
                        "stop_sharing_group"
                )
        );

        List<CommandNameWrapper> adminCommands = List.of(
        );

        for (CommandNameWrapper command : commands) {
            event.register(container, command.command(), command.name());
        }
        for (CommandNameWrapper command : adminCommands) {
            event.register(container, command.command(), command.name());
        }
    }
}
