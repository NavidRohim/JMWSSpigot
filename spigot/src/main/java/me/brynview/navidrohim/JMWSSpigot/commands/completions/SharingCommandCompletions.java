package me.brynview.navidrohim.JMWSSpigot.commands.completions;

import me.brynview.navidrohim.common.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SharingCommandCompletions implements TabExecutor {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (sender instanceof Player)
        {
            if (command.getName().equals("share_waypoint"))
            {
                if (args.length == 1)
                {
                    sender.getServer().getOnlinePlayers().forEach(x -> completions.add(x.getName()));
                    return completions;
                } else if (args.length == 2)
                {
                    return ObjectSuggestions.suggestWaypoints(((Player) sender).getUniqueId());
                }
            }
            return completions;
        }
        return List.of();
    }
}
