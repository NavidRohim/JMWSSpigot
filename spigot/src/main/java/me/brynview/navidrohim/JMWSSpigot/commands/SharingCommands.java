package me.brynview.navidrohim.JMWSSpigot.commands;

import me.brynview.navidrohim.common.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

public class SharingCommands implements CommandExecutor {
    // Docs say to make a new class for each command, and to that, I say fuck no.

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        Constants.getLogger().info(command.getName());
        return true;
    }
}
