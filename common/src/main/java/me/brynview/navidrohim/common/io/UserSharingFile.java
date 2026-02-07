package me.brynview.navidrohim.common.io;

import me.brynview.navidrohim.common.enums.ObjectType;
import me.brynview.navidrohim.common.syncing.share.io.CommonShareIO;

import java.util.UUID;

public class UserSharingFile extends CommonShareIO {
    public UserSharingFile(UUID userUUID) {
        super(JMWSServerIO.PathUtils.getObjectFilename(userUUID, "SHARED", ObjectType.SHARED, false));
    }

    @Override
    public boolean addToShared(String sharedValue, ObjectType sharedObjectType)
    {
        boolean b = super.addToShared(sharedValue, sharedObjectType);
        writeSharedList();
        return b;
    }

    @Override
    public boolean removeFromShared(String sharedValue, ObjectType sharedObjectType)
    {
        boolean b = super.removeFromShared(sharedValue, sharedObjectType);
        writeSharedList();
        return b;
    }

    public static void removeObjectFromUser(UUID playerUUID, String objectIdentifier, ObjectType sharedObjectType)
    {
        try (UserSharingFile usf = new UserSharingFile(playerUUID))
        {
            usf.removeFromShared(objectIdentifier, sharedObjectType);
        }
    }
}
