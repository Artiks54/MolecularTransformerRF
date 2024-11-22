package com.ariks.MolecularRF.network;

import com.ariks.MolecularRF.Block.RfMolecular.TileRfMolecular;
import com.ariks.MolecularRF.Block.RfMolecular.TileRfMolecularRenderer;
import com.ariks.MolecularRF.Register.RegistryBlock;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit() {
		super.preInit();
		ClientRegistry.bindTileEntitySpecialRenderer(TileRfMolecular.class, new TileRfMolecularRenderer());
	}
	@Override
	public void Init() {
		super.Init();
		RegistryBlock.registerRender();
	}
}