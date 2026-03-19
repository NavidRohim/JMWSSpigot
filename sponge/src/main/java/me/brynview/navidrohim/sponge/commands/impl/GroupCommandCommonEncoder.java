package me.brynview.navidrohim.sponge.commands.impl;

import me.brynview.navidrohim.common.enums.ObjectType;
import me.brynview.navidrohim.common.objects.ServerGroup;
import me.brynview.navidrohim.sponge.commands.api.ObjectShareType;
import me.brynview.navidrohim.sponge.commands.api.SpongeArgumentType;
import me.brynview.navidrohim.sponge.commands.api.SpongeCommandCommonEncoder;
import org.spongepowered.api.command.parameter.Parameter;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static me.brynview.navidrohim.sponge.commands.impl.ImplUtil.*;

public class GroupCommandCommonEncoder implements SpongeCommandCommonEncoder {

    private final ObjectShareType objectShareType;

    public GroupCommandCommonEncoder(ObjectShareType encoderType)
    {
        this.objectShareType = encoderType;
    }

    public GroupCommandCommonEncoder()
    {
        this.objectShareType = ObjectShareType.DEFAULT;
    }

    private static Parameter.Value.Builder<String> group() {
        return withCompletionListOfObjects(ObjectType.GROUP);
    }

    private static Parameter.Value.Builder<String> globalGroups() {
        return withCompletionListOfGlobalObjects(ObjectType.GROUP);
    }

    private static Parameter.Value.Builder<String> sharedGroups() {
        return withCompletionListOfSharedObjects(ObjectType.GROUP);
    }

    @Override
    public SpongeArgumentType<?> buildParameterForNative() {
        Supplier<Parameter.Value.Builder<String>> supplier;

        if (this.objectShareType == ObjectShareType.DEFAULT) {
            supplier = GroupCommandCommonEncoder::group;
        } else if (this.objectShareType == ObjectShareType.SHARED) {
            supplier = GroupCommandCommonEncoder::sharedGroups;
        } else {
            supplier = GroupCommandCommonEncoder::globalGroups;
        }

        return new SpongeArgumentType<>(String.class, supplier);
    }

    @Override
    public Optional<ServerGroup> getCommonParameterValue(Object value, UUID commandSenderUUID) {
        return ObjectType.GROUP.getServerObject(value.toString(), commandSenderUUID);
    }
}
