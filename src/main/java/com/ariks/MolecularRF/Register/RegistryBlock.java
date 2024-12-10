package com.ariks.MolecularRF.Register;

import com.ariks.MolecularRF.Block.Core.BlockCustomModelTile;
import com.ariks.MolecularRF.Block.Core.BlockMolecularRf;
import com.ariks.MolecularRF.MolecularRF;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Objects;

public class RegistryBlock {
    public static final String modId = MolecularRF.MOD_ID + "_";
    public static Block RF_Molecular = new BlockMolecularRf(modId +"molecular_rf",1);
    public static Block RF_Molecular_Double = new BlockMolecularRf(modId +"molecular_rf_double_input",2);
    public static Block RF_Molecular_Output = new BlockMolecularRf(modId +"molecular_rf_output",3);
    public static Block Molecular_Core = new BlockCustomModelTile(modId + "molecular_core");
    public static void preInit() {
        setRegister(RF_Molecular);
        setRegister(RF_Molecular_Double);
        setRegister(RF_Molecular_Output);
        setRegister(Molecular_Core);
    }
    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        setRender(RF_Molecular);
        setRender(RF_Molecular_Double);
        setRender(RF_Molecular_Output);
        setRender(Molecular_Core);
    }
    private static void setRegister(Block block) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName())));
    }
    @SideOnly(Side.CLIENT)
    private static void setRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory"));
    }
}