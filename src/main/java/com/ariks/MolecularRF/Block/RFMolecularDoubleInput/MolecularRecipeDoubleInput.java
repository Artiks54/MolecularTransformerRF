package com.ariks.MolecularRF.Block.RFMolecularDoubleInput;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class MolecularRecipeDoubleInput {
    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack output;
    private final long energy;
    public static ArrayList<MolecularRecipeDoubleInput> recipes = new ArrayList<>();
    public MolecularRecipeDoubleInput(ItemStack input1,ItemStack input2, ItemStack output, long energy) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
        this.energy = energy;
    }
    public ItemStack getInput1() {
        return input1;
    }
    public ItemStack getInput2() {
        return input2;
    }
    public long getEnergy() {
        return energy;
    }
    public @NotNull ItemStack getRecipeOutput() {
        return output.copy();
    }
    public static void addRecipe(MolecularRecipeDoubleInput recipe) {
        recipes.add(recipe);
    }
    public static ArrayList<MolecularRecipeDoubleInput> getRecipes() {
        return recipes;
    }
    public boolean matches(ItemStack inputStack1, ItemStack inputStack2) {
        return (input1.isItemEqual(inputStack1) && ItemStack.areItemStackTagsEqual(input1, inputStack1) &&
                input2.isItemEqual(inputStack2) && ItemStack.areItemStackTagsEqual(input2, inputStack2)) ||
                (input1.isItemEqual(inputStack2) && ItemStack.areItemStackTagsEqual(input1, inputStack2) &&
                        input2.isItemEqual(inputStack1) && ItemStack.areItemStackTagsEqual(input2, inputStack1));
    }
    public static void preInit() {
    }
}