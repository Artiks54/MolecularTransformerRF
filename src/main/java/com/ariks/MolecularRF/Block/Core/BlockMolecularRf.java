package com.ariks.MolecularRF.Block.Core;

import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.TileRfMolecularDoubleInput;
import com.ariks.MolecularRF.Block.RFMolecularOutput.TileRfMolecularOutput;
import com.ariks.MolecularRF.Block.RfMolecular.TileRfMolecular;
import com.ariks.MolecularRF.MolecularRF;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockMolecularRf extends BlockCustomModelTile {
    private final int id;
    public BlockMolecularRf(String name, int id) {
        super(name);
        this.id = id;
        this.setCreativeTab(MolecularRF.molecularTransformerTab);
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        if(id == 1) {
            return new TileRfMolecular();
        }
        if(id == 2) {
            return new TileRfMolecularDoubleInput();
        }
        if(id == 3) {
            return new TileRfMolecularOutput();
        }
        return null;
    }
    @Override
    public void breakBlock(World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        assert tileEntity != null;
        InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
        super.breakBlock(worldIn, pos, state);
    }
}