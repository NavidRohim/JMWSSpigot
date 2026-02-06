package me.brynview.navidrohim.jMWSSpigot.server.objects;

import me.brynview.navidrohim.jMWSSpigot.common.enums.ObjectType;

public interface PossessesIdentifier {

    public String getName();
    public String getCustomData();
    public String getGroupIdentifier();
    public ObjectType getObjectType();
}
