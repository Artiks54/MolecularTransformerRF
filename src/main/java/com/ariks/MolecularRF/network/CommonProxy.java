package com.ariks.MolecularRF.network;

import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.MolecularRecipeDoubleInput;
import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.TileRfMolecularDoubleInput;
import com.ariks.MolecularRF.Block.RFMolecularOutput.MolecularRecipeOutput;
import com.ariks.MolecularRF.Block.RFMolecularOutput.TileRfMolecularOutput;
import com.ariks.MolecularRF.Block.RfMolecular.MolecularRecipe;
import com.ariks.MolecularRF.Block.RfMolecular.TileRfMolecular;
import com.ariks.MolecularRF.MolecularRF;
import com.ariks.MolecularRF.Register.*;
import com.ariks.MolecularRF.Register.RegistryBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	public void preInit() {
		RegistryBlock.preInit();
		GameRegistry.registerTileEntity(TileRfMolecular.class, new ResourceLocation(MolecularRF.MOD_ID, "tile_tf_molecular"));
		GameRegistry.registerTileEntity(TileRfMolecularDoubleInput.class, new ResourceLocation(MolecularRF.MOD_ID, "tile_rf_molecular_double"));
		GameRegistry.registerTileEntity(TileRfMolecularOutput.class, new ResourceLocation(MolecularRF.MOD_ID, "tile_rf_molecular_output"));
		MolecularRecipe.preInit();
		MolecularRecipeDoubleInput.preInit();
		MolecularRecipeOutput.preInit();
		NetworkRegistry.INSTANCE.registerGuiHandler(MolecularRF.instance, new RegistryGui());
	}
	public void init(){
	}
}