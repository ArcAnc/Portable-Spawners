/**
 * @author ArcAnc
 * Created at: 13.08.2024
 * Copyright (c) 2024
 * <p>
 * This code is licensed under "Arc's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package com.arcanc.ps.mixin;

import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SpawnerBlock.class)
public class FixSpawnerBlockProps
{

    @ModifyArg(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/BaseEntityBlock;<init>(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V"),
            method = "<init>")
    private static BlockBehaviour.@NotNull Properties applyPickaxeRequirements(BlockBehaviour.@NotNull Properties value)
    {
        return value.requiresCorrectToolForDrops();
    }

}