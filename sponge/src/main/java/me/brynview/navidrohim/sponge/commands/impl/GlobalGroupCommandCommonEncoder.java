package me.brynview.navidrohim.sponge.commands.impl;

import me.brynview.navidrohim.common.enums.ObjectType;
import me.brynview.navidrohim.sponge.commands.api.SpongeArgumentType;
import org.spongepowered.api.command.parameter.Parameter;

import static me.brynview.navidrohim.sponge.commands.impl.ImplUtil.withCompletionListOfObjects;

public class GlobalGroupCommandCommonEncoder extends GroupCommandCommonEncoder {
    private static Parameter.Value.Builder<String> group() {
        return withCompletionListOfObjects(ObjectType.GROUP);
    }

    @Override
    public SpongeArgumentType<?> buildParameterForNative() {
        return new SpongeArgumentType<>(String.class, GlobalGroupCommandCommonEncoder::group);
    }
}
