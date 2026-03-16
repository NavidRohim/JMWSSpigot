package me.brynview.navidrohim.common.api.commands;

import java.util.List;

public record Command(String commandName, int permissionLevel, Argument... commandArguments)
{

}
