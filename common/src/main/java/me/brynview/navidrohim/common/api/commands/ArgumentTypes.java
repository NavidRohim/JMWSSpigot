package me.brynview.navidrohim.common.api.commands;

import me.brynview.navidrohim.common.objects.ServerGroup;
import me.brynview.navidrohim.common.objects.ServerObject;
import me.brynview.navidrohim.common.objects.ServerWaypoint;

public enum ArgumentTypes {
    STRING(String.class),
    WAYPOINT(ServerWaypoint.class),
    GROUP(ServerGroup.class),;

    private final Class<?> clazz;

    ArgumentTypes(Class<?> associatedClass)
    {
        this.clazz = associatedClass;
    }

    public Class<?> getAssociatedClass() {
        return clazz;
    }
}
