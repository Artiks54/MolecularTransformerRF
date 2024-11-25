package com.ariks.MolecularRF;

import com.ariks.MolecularRF.network.CommonProxy;
import com.ariks.MolecularRF.util.Tab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MolecularRF.MOD_ID, name = MolecularRF.MOD_NAME, useMetadata = true, acceptedMinecraftVersions = "[1.12]", version = MolecularRF.VERSION)
public class MolecularRF {
	public static Logger logger;
	public static final String MOD_ID = "mrf", MOD_NAME = "MolecularTransformerRF", VERSION = "1.0";
	@Mod.Instance(MolecularRF.MOD_ID)
	public static MolecularRF instance;
	@SidedProxy(clientSide = "com.ariks.MolecularRF.network.ClientProxy", serverSide = "com.ariks.MolecularRF.network.CommonProxy")
	public static CommonProxy proxy;
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit();
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
	public static final SoundEvent SOUND_MOLECULAR = new SoundEvent(new ResourceLocation(MOD_ID, "molecular")).setRegistryName("molecular");
	public static CreativeTabs molecularTransformerTab = new Tab("mrf");
}