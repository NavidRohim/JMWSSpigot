package me.brynview.navidrohim.common.objects;

import me.brynview.navidrohim.common.enums.ObjectType;

public interface PossessesIdentifier {

    public String getName();
    public String getCustomData();
    public String getGroupIdentifier();
    public ObjectType getObjectType();
}
