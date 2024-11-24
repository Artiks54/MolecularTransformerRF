package com.ariks.MolecularRF.integration.CraftTweaker;

import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.MolecularRecipeDoubleInput;
import com.ariks.MolecularRF.Block.RFMolecularOutput.MolecularRecipeOutput;
import com.ariks.MolecularRF.Block.RfMolecular.MolecularRecipe;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.mrf.molecular")

public class molecular {
    @ZenMethod
    public static void addRecipeMolecular(IItemStack input, IItemStack output, long energy) {
        ItemStack inputStack = CraftTweakerMC.getItemStack(input);
        inputStack.setItemDamage(input.getMetadata());
        ItemStack outputStack = CraftTweakerMC.getItemStack(output);
        outputStack.setItemDamage(output.getMetadata());
        MolecularRecipe.addRecipe(new MolecularRecipe(inputStack, outputStack, energy));
    }
    @ZenMethod
    public static void addRecipeMolecularInput(IItemStack input1, IItemStack input2, IItemStack output, long energy) {
        ItemStack inputStack1 = CraftTweakerMC.getItemStack(input1);
        ItemStack inputStack2 = CraftTweakerMC.getItemStack(input2);
        inputStack1.setItemDamage(input1.getMetadata());
        inputStack2.setItemDamage(input2.getMetadata());
        ItemStack outputStack = CraftTweakerMC.getItemStack(output);
        outputStack.setItemDamage(output.getMetadata());
        MolecularRecipeDoubleInput.addRecipe(new MolecularRecipeDoubleInput(inputStack1,inputStack2, outputStack, energy));
    }
    @ZenMethod
    public static void addRecipeMolecularOutput(IItemStack input, IItemStack output1, IItemStack output2, long energy) {
        ItemStack inputStack = CraftTweakerMC.getItemStack(input);
        inputStack.setItemDamage(input.getMetadata());
        ItemStack outputStack1 = CraftTweakerMC.getItemStack(output1);
        ItemStack outputStack2 = CraftTweakerMC.getItemStack(output2);
        outputStack1.setItemDamage(output1.getMetadata());
        outputStack2.setItemDamage(output2.getMetadata());
        MolecularRecipeOutput.addRecipe(new MolecularRecipeOutput(inputStack, outputStack1,outputStack2, energy));
    }
}