/**
 * @author ArcAnc
 * Created at: 13.08.2024
 * Copyright (c) 2024
 * <p>
 * This code is licensed under "Arc's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package com.arcanc.ps.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class ChangeSpawnerBlock
{
    @Inject(at = @At("HEAD"), method = "playerWillDestroy")
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer, CallbackInfoReturnable<BlockState> cir)
    {
        Block block = (Block) ((Object)this);
        if (block instanceof SpawnerBlock spawnerBlock)
        {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SpawnerBlockEntity)
            {
                ItemStack pTool = pPlayer.getMainHandItem();
                Holder<Enchantment> enchantmentHolder = pLevel.holderLookup(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH);
                if (!pLevel.isClientSide() && !pPlayer.isCreative()
                        && pTool.isEnchanted()
                        && pTool.getEnchantments().getLevel(enchantmentHolder) > 0
                        && pTool.isCorrectToolForDrops(pState))
                {
                    ItemStack itemStack = block.asItem().getDefaultInstance();
                    blockEntity.saveToItem(itemStack, pLevel.registryAccess());

                    ItemEntity itemEntity = new ItemEntity(pLevel, (double)pPos.getX() + 0.5, (double)pPos.getY() + 0.5, (double)pPos.getZ() + 0.5, itemStack);
                    itemEntity.setDefaultPickUpDelay();
                    pLevel.addFreshEntity(itemEntity);
                }
            }
        }
    }
}
