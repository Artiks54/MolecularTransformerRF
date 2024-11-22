package com.ariks.MolecularRF.network;

import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.MolecularRecipeDoubleInput;
import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.TileRfMolecularDoubleInput;
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
		RegistryReciep.preInit();
		MolecularRecipe.preInit();
		MolecularRecipeDoubleInput.preInit();
		GameRegistry.registerTileEntity(TileRfMolecular.class,new ResourceLocation(MolecularRF.MOD_ID,"Tile_Rf_Molecular"));
		GameRegistry.registerTileEntity(TileRfMolecularDoubleInput.class,new ResourceLocation(MolecularRF.MOD_ID,"Tile_Rf_Molecular_Double"));
		NetworkRegistry.INSTANCE.registerGuiHandler(MolecularRF.instance, new RegistryGui());
	}
	public void Init(){}
}