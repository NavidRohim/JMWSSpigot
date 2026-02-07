package me.brynview.navidrohim.JMWSSpigot.impl;

import me.brynview.navidrohim.common.api.JMWSPlayer;
import org.bukkit.entity.Player;

public class SpigotJMWSPlayer implements JMWSPlayer
{
    private final Player nativePayerObj;

    public SpigotJMWSPlayer(Player nativePlayerObj)
    {
        this.nativePayerObj = nativePlayerObj;
    }

    @Override
    public String getName() {
        return this.nativePayerObj.getDisplayName();
    }
}
