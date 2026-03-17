package me.brynview.navidrohim.common.api.commands;

import me.brynview.navidrohim.common.api.game.WSPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public record CommonCommandContext(HashMap<String, ?> arguments, WSPlayer commandSender)
{
    @Nullable
    public <T> T getArgumentAsType(String key, Class<T> clazz) {
        Object value = arguments.get(key);

        if (clazz.isInstance(value)) {
            return clazz.cast(value);
        }

        return null;
    }
}
