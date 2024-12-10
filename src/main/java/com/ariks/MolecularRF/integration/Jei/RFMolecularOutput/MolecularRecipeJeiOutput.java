package com.ariks.MolecularRF.integration.Jei.RFMolecularOutput;

import com.ariks.MolecularRF.Block.RFMolecularOutput.MolecularRecipeOutput;
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

public class MolecularRecipeJeiOutput implements IRecipeWrapper {
    MolecularRecipeOutput recipe;
    public final LocalizedStringKey LS = new LocalizedStringKey();

    public MolecularRecipeJeiOutput(MolecularRecipeOutput recipes) {
        this.recipe = recipes;
    }
    @SuppressWarnings("deprecation")
    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, getInputs());
        ingredients.setOutput(ItemStack.class, getOutputs1());
        ingredients.setOutput(ItemStack.class, getOutputs2());
    }
    public List<ItemStack> getInputs() {
        return Collections.singletonList(recipe.getInput());
    }
    public List<ItemStack> getOutputs1() {
        return Collections.singletonList(recipe.getRecipeOutput1());
    }
    public List<ItemStack> getOutputs2() {
        return Collections.singletonList(recipe.getRecipeOutput2());
    }
    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        IRecipeWrapper.super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
        String energyText = EnergyFormat.formatNumber(recipe.getEnergy());
        minecraft.fontRenderer.drawString(LS.StrEnergyRecipe, 55, 5, Color.WHITE.getRGB());
        minecraft.fontRenderer.drawString(energyText, 55, 15, Color.WHITE.getRGB());
    }
}