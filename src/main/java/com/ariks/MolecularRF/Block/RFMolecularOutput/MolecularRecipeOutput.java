package com.ariks.MolecularRF.Block.RFMolecularOutput;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class MolecularRecipeOutput {
    private final ItemStack input;
    private final ItemStack output1;
    private final ItemStack output2;
    private final long energy;
    public static ArrayList<MolecularRecipeOutput> recipes = new ArrayList<>();
    public MolecularRecipeOutput(ItemStack input, ItemStack output1, ItemStack output2, long energy) {
        this.input = input;
        this.output1 = output1;
        this.output2 = output2;
        this.energy = energy;
    }
    public ItemStack getInput() {
        return input;
    }
    public long getEnergy() {
        return energy;
    }
    public @NotNull ItemStack getRecipeOutput1() {
        return output1.copy();
    }
    public @NotNull ItemStack getRecipeOutput2() {
        return output2.copy();
    }
    public static void addRecipe(MolecularRecipeOutput recipe) {
        recipes.add(recipe);
    }
    public static ArrayList<MolecularRecipeOutput> getRecipes() {
        return recipes;
    }
    public static void preInit() {
        MolecularRecipeOutput.addRecipe(new MolecularRecipeOutput(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.CARROT),new ItemStack(Items.CHICKEN), 50_000_000));
    }
}