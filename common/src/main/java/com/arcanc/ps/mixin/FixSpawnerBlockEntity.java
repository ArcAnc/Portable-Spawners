/**
 * @author ArcAnc
 * Created at: 13.08.2024
 * Copyright (c) 2024
 * <p>
 * This code is licensed under "Arc's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package com.arcanc.ps.mixin;

import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnerBlockEntity.class)
public class FixSpawnerBlockEntity
{
    @Inject(at = @At("HEAD"), method = "onlyOpCanSetNbt", cancellable = true)
    private void changeNbtData(@NotNull CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue(false);
    }
}
