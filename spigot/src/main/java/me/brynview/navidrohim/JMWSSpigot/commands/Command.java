package me.brynview.navidrohim.JMWSSpigot.commands;

import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.executors.CommandExecutor;
import dev.jorel.commandapi.executors.PlayerCommandExecutor;


import java.util.List;

public record Command(String commandName, PlayerCommandExecutor executor, Argument<?>... arguments) {}
