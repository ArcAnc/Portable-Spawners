package com.arcanc.ps.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class ChangeSpawnerBlock
{

    @Inject(at = @At("HEAD"), method = "playerWillDestroy")
    public void playerWillDestroy(Level $$0, BlockPos $$1, BlockState $$2, Player $$3, CallbackInfo ci)
    {
        Block block = (Block) ((Object)this);
        if (block instanceof SpawnerBlock spawnerBlock)
        {
            BlockEntity $$4 = $$0.getBlockEntity($$1);
            if ($$4 instanceof SpawnerBlockEntity)
            {
                if (!$$0.isClientSide() && !$$3.isCreative())
                {
                    ItemStack $$6 = block.asItem().getDefaultInstance();
                    $$4.saveToItem($$6);

                    ItemEntity $$7 = new ItemEntity($$0, (double)$$1.getX() + 0.5, (double)$$1.getY() + 0.5, (double)$$1.getZ() + 0.5, $$6);
                    $$7.setDefaultPickUpDelay();
                    $$0.addFreshEntity($$7);
                }
            }
        }
    }
}