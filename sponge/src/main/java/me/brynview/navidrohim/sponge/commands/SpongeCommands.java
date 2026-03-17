package me.brynview.navidrohim.sponge.commands;

import me.brynview.navidrohim.common.CommonClass;
import me.brynview.navidrohim.common.Constants;
import me.brynview.navidrohim.common.api.commands.Argument;
import me.brynview.navidrohim.common.api.commands.ArgumentTypes;
import me.brynview.navidrohim.common.api.commands.CommonCommand;
import me.brynview.navidrohim.common.api.commands.CommonCommandContext;
import me.brynview.navidrohim.common.api.game.WSPlayer;
import me.brynview.navidrohim.common.commands.SuggestionProvider;
import me.brynview.navidrohim.common.enums.ObjectType;
import me.brynview.navidrohim.sponge.JMWSSponge;
import me.brynview.navidrohim.sponge.impl.game.SpongePlayer;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.CommandCompletion;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.manager.CommandMapping;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.command.parameter.Parameter;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;

import java.util.*;
import java.util.function.Supplier;

public class SpongeCommands {

    private record ParameterAndKey<T>(Parameter parameter, Parameter.Key<T> key) {}

    private record SpongeArgumentType<T>(Class<T> type, Supplier<Parameter.Value.Builder<T>> builder) {

        public Parameter.Value.Builder<T> createBuilder() {
                return builder.get();
            }

            private static <T> ParameterAndKey<T> buildParameter(String name, SpongeArgumentType<T> type) {
                Parameter.Key<T> key = Parameter.key(name, type.type());
                return new ParameterAndKey<>(type.createBuilder().key(key).build(), key);
            }
        }

    // Don't ask me about the name.
    private static final Map<ArgumentTypes, SpongeArgumentType<?>> PRIMITIVE_TO_PRIVILEGED = Map.of(
            ArgumentTypes.STRING, new SpongeArgumentType<>(String.class, Parameter::string),
            ArgumentTypes.WAYPOINT, new SpongeArgumentType<>(String.class, SpongeCommands::waypoint),
            ArgumentTypes.GROUP, new SpongeArgumentType<>(String.class, SpongeCommands::group)
    );
    private static final Map<String, CommonCommand> COMMAND_NAME_TO_OBJ_MAP = new HashMap<>();
    private static final Map<String, Parameter.Key<?>> PARAM_KEYS = new HashMap<>();

    private static Parameter.Value.Builder<String> withCompletionListOf(ObjectType completionType) {
        return Parameter.string().completer((context, currentInput) -> {
            Optional<UUID> UUID = context.cause().audience().get(Identity.UUID);
            List<CommandCompletion> completions = new ArrayList<>();

            // UUID won't be present if the user is a console or anything without a UUID
            if (UUID.isPresent()) {
                List<String> waypoints = completionType == ObjectType.WAYPOINT ? SuggestionProvider.suggestWaypoints(UUID.get()) : SuggestionProvider.suggestGroups(UUID.get());

                waypoints.forEach(wpString -> {
                    if (wpString.contains(currentInput)) {
                        completions.add(CommandCompletion.of(wpString));
                    }
                });
            }
            return completions;
        });
    }

    private static Parameter.Value.Builder<String> waypoint() {
        return withCompletionListOf(ObjectType.WAYPOINT);
    }

    private static Parameter.Value.Builder<String> group() {
        return withCompletionListOf(ObjectType.GROUP);
    }

    public static void registerCommand(CommonCommand commonCommand, final RegisterCommandEvent<Command.Parameterized> event)
    {
        Constants.getLogger().info("Registering JMWS command %s".formatted(commonCommand.commandName()));
        final List<Parameter> params = new ArrayList<>();

        for (Argument arg : commonCommand.commandArguments()) {
            SpongeArgumentType<?> spongeType = PRIMITIVE_TO_PRIVILEGED.get(arg.type());
            ParameterAndKey<?> param = SpongeArgumentType.buildParameter(arg.name(), spongeType);

            PARAM_KEYS.put(arg.name(), param.key());
            params.add(param.parameter);
        }

        Command.Parameterized nativeCommand = Command.builder().addParameters(params).executor((executor) -> {
            return SpongeCommands.executeCommand(executor, commonCommand.commandName());
        }).build();
        event.register(JMWSSponge.getPlugin().container(), nativeCommand, commonCommand.commandName());
        COMMAND_NAME_TO_OBJ_MAP.put(commonCommand.commandName(), commonCommand);
    }

    private static CommandResult executeCommand(CommandContext commandContext, String commandName)
    {
        if (commandContext.cause().audience().get(Identity.UUID).isPresent())
        {
            CommonCommand commonCommand = COMMAND_NAME_TO_OBJ_MAP.get(commandName);
            HashMap<String, Object> commonArgs = new HashMap<>();
            UUID UUID = commandContext.cause().audience().get(Identity.UUID).get(); // Sorry but why the name audience? Made no sense.
            WSPlayer commonPlayer = CommonClass.server.getWSPlayer(UUID);

            Arrays.asList(commonCommand.commandArguments()).forEach(arg -> {
                Object value = commandContext.requireOne(PARAM_KEYS.get(arg.name()));
                commonArgs.put(arg.name(), value);
            });

            CommonCommandContext ctx = new CommonCommandContext(commonArgs, commonPlayer);
            commonCommand.executeWithContext(ctx);

            return CommandResult.success();
        } else {
            Constants.getLogger().info("Cannot execute command. Debug: %s".formatted(commandContext.cause().audience().get(Identity.UUID).isPresent()));
            return CommandResult.error(Component.text("Cannot execute command. Internal error."));
        }
    }
}
