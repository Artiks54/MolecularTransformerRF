package com.ariks.MolecularRF.Block.RfMolecular;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class MolecularRecipe {
    private final ItemStack input;
    private final ItemStack output;
    private final long energy;
    public static ArrayList<MolecularRecipe> recipes = new ArrayList<>();
    public MolecularRecipe(ItemStack input, ItemStack output, long energy) {
        this.input = input;
        this.output = output;
        this.energy = energy;
    }
    public ItemStack getInput() {
        return input;
    }
    public long getEnergy() {
        return energy;
    }
    public @NotNull ItemStack getRecipeOutput() {
        return output.copy();
    }
    public static void addRecipe(MolecularRecipe recipe) {
        recipes.add(recipe);
    }
    public static ArrayList<MolecularRecipe> getRecipes() {
        return recipes;
    }
    public boolean matches(ItemStack inputStack, ItemStack outputStack) {
        return input.isItemEqual(inputStack) &&
                ItemStack.areItemStackTagsEqual(input, inputStack) && inputStack.getCount() >= input.getCount() && (outputStack.isEmpty() || (outputStack.isItemEqual(output) && ItemStack.areItemStackTagsEqual(outputStack, output)));
    }
        public static void preInit() {
    }
}