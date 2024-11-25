package com.ariks.MolecularRF.util;

import com.ariks.MolecularRF.Register.RegistryBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Tab extends CreativeTabs {
    public Tab(String string) {
        super(string);
    }

    @Override
    public @NotNull ItemStack getTabIconItem() {
        return new ItemStack(RegistryBlock.RF_Molecular);
    }
}