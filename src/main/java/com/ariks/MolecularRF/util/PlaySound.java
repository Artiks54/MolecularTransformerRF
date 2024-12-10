package com.ariks.MolecularRF.util;

import com.ariks.MolecularRF.MolecularRF;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlaySound {

    @SideOnly(Side.CLIENT)
    private int tick = 0;
    public void play(World world, BlockPos pos, Boolean work) {
        tick++;
        if (tick >= 30 && work) {
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), MolecularRF.SOUND_MOLECULAR, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            tick = 0;
        }
    }
}