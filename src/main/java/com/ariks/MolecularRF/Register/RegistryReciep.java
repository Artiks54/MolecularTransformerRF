package com.ariks.MolecularRF.Register;

import com.ariks.MolecularRF.MolecularRF;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import static com.ariks.MolecularRF.Register.RegistryBlock.*;

public class RegistryReciep {
    public static void preInit() {
        //RF_Molecular
        GameRegistry.addShapedRecipe(
                new ResourceLocation(MolecularRF.MOD_ID + ":" + "Molecular_1"), null,
                new ItemStack(RF_Molecular),
                "BSB",
                "NPN",
                "PCP",
                'B', new ItemStack(Items.NETHER_STAR),
                'S', new ItemStack(Items.NETHER_STAR),
                'C', new ItemStack(Items.NETHER_STAR),
                'P', new ItemStack(Items.NETHER_STAR),
                'N', new ItemStack(Items.NETHER_STAR));
    }
}