package com.ariks.MolecularRF.integration.Jei;

import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.MolecularRecipeDoubleInput;
import com.ariks.MolecularRF.Block.RfMolecular.MolecularRecipe;
import com.ariks.MolecularRF.MolecularRF;
import com.ariks.MolecularRF.Register.RegistryBlock;
import com.ariks.MolecularRF.integration.Jei.RfMolecular.MolecularRfRecipeCategory;
import com.ariks.MolecularRF.integration.Jei.RfMolecular.MolecularRfWrapper;
import com.ariks.MolecularRF.integration.Jei.RfMolecularDoubleInput.MolecularRfRecipeCategoryDoubleInput;
import com.ariks.MolecularRF.integration.Jei.RfMolecularDoubleInput.MolecularRfWrapperDoubleInput;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class AddonJei implements IModPlugin {
    public void register(IModRegistry registry) {
        String idMolecular = MolecularRF.MOD_ID + "_molecular";
        String idMolecular_Double_input = MolecularRF.MOD_ID + "_molecular_double_input";

        registry.handleRecipes(MolecularRecipe.class, new MolecularRfWrapper(), idMolecular);
        registry.addRecipes(MolecularRecipe.getRecipes(), idMolecular);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.RF_Molecular), idMolecular);

        registry.handleRecipes(MolecularRecipeDoubleInput.class, new MolecularRfWrapperDoubleInput(), idMolecular_Double_input);
        registry.addRecipes(MolecularRecipeDoubleInput.getRecipes(), idMolecular_Double_input);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.RF_Molecular_Double), idMolecular_Double_input);
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new MolecularRfRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new MolecularRfRecipeCategoryDoubleInput(registry.getJeiHelpers().getGuiHelper()));
    }
}