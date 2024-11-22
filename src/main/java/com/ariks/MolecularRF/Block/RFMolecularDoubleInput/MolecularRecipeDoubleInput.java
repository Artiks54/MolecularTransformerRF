package com.ariks.MolecularRF.Block.RFMolecularDoubleInput;

import net.minecraft.init.Items;
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
    public static void preInit() {
        MolecularRecipeDoubleInput.addRecipe(new MolecularRecipeDoubleInput(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.DIAMOND),new ItemStack(Items.NETHER_STAR), 10_000_000));
    }
}