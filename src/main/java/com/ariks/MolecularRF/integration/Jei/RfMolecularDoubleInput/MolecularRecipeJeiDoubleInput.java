package com.ariks.MolecularRF.integration.Jei.RfMolecularDoubleInput;

import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.MolecularRecipeDoubleInput;
import com.ariks.MolecularRF.util.EnergyFormat;
import com.ariks.MolecularRF.util.LocalizedStringKey;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class MolecularRecipeJeiDoubleInput implements IRecipeWrapper {
    MolecularRecipeDoubleInput recipe;
    public final LocalizedStringKey LS = new LocalizedStringKey();

    public MolecularRecipeJeiDoubleInput(MolecularRecipeDoubleInput recipes) {
        this.recipe = recipes;
    }
    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, getInputs1());
        ingredients.setInputs(ItemStack.class, getInputs2());
        ingredients.setOutput(ItemStack.class, getOutputs());
    }
    public List<ItemStack> getInputs1() {
        return Collections.singletonList(recipe.getInput1());
    }
    public List<ItemStack> getInputs2() {
        return Collections.singletonList(recipe.getInput2());
    }
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(recipe.getRecipeOutput());
    }
    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        IRecipeWrapper.super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
        String energyText =  LS.StrEnergyRecipe +" "+ EnergyFormat.formatNumber(recipe.getEnergy());
        minecraft.fontRenderer.drawString(energyText, 55, 5, Color.WHITE.getRGB());
    }
}