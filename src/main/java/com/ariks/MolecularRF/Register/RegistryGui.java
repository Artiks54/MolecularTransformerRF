package com.ariks.MolecularRF.Register;

import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.ContainerRfMolecularDouble;
import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.GuiRfMolecularDouble;
import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.TileRfMolecularDoubleInput;
import com.ariks.MolecularRF.Block.RFMolecularOutput.ContainerRfMolecularOutput;
import com.ariks.MolecularRF.Block.RFMolecularOutput.GuiRfMolecularOutput;
import com.ariks.MolecularRF.Block.RFMolecularOutput.TileRfMolecularOutput;
import com.ariks.MolecularRF.Block.RfMolecular.ContainerRfMolecular;
import com.ariks.MolecularRF.Block.RfMolecular.GuiRfMolecular;
import com.ariks.MolecularRF.Block.RfMolecular.TileRfMolecular;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RegistryGui implements IGuiHandler {
    public static final int GUI_RF_MOLECULAR = 0;
    public static final int GUI_RF_MOLECULAR_DOUBLE_INPUT = 1;
    public static final int GUI_RF_MOLECULAR_OUTPUT = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_RF_MOLECULAR) {
            return new ContainerRfMolecular(player.inventory, (TileRfMolecular) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_RF_MOLECULAR_DOUBLE_INPUT) {
            return new ContainerRfMolecularDouble(player.inventory, (TileRfMolecularDoubleInput) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_RF_MOLECULAR_OUTPUT) {
            return new ContainerRfMolecularOutput(player.inventory, (TileRfMolecularOutput) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        return null;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_RF_MOLECULAR) {
            return new GuiRfMolecular(player.inventory, (TileRfMolecular) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_RF_MOLECULAR_DOUBLE_INPUT) {
            return new GuiRfMolecularDouble(player.inventory, (TileRfMolecularDoubleInput) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_RF_MOLECULAR_OUTPUT) {
            return new GuiRfMolecularOutput(player.inventory, (TileRfMolecularOutput) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        return null;
    }
}