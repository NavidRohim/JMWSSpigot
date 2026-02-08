package me.brynview.navidrohim.JMWSSpigot.commands;

import me.brynview.navidrohim.JMWSSpigot.impl.SpigotPlayer;
import me.brynview.navidrohim.common.api.WSPlayer;
import me.brynview.navidrohim.common.commands.CommonShareLayer;
import me.brynview.navidrohim.common.enums.ObjectType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;

public class SharingCommands implements CommandExecutor {
    // Docs say to make a new class for each command, and to that, I say fuck no.

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        if (sender instanceof Player)
        {
            SpigotPlayer spigotPlayer = new SpigotPlayer((Player) sender);
            switch (command.getName()) {
                case "share_waypoint" ->
                {
                    CommonShareLayer.doShareWaypoint(spigotPlayer, Arrays.asList(args));
                }
            }
            return true;
        }
        return false;
    }
}
