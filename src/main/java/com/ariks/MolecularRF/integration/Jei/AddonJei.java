package com.ariks.MolecularRF.integration.Jei;

import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.GuiRfMolecularDouble;
import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.MolecularRecipeDoubleInput;
import com.ariks.MolecularRF.Block.RFMolecularOutput.GuiRfMolecularOutput;
import com.ariks.MolecularRF.Block.RFMolecularOutput.MolecularRecipeOutput;
import com.ariks.MolecularRF.Block.RfMolecular.GuiRfMolecular;
import com.ariks.MolecularRF.Block.RfMolecular.MolecularRecipe;
import com.ariks.MolecularRF.MolecularRF;
import com.ariks.MolecularRF.Register.RegistryBlock;
import com.ariks.MolecularRF.integration.Jei.RFMolecularOutput.MolecularRecipeJeiOutput;
import com.ariks.MolecularRF.integration.Jei.RFMolecularOutput.MolecularRfRecipeCategoryOutput;import com.ariks.MolecularRF.integration.Jei.RfMolecular.MolecularRecipeJei;
import com.ariks.MolecularRF.integration.Jei.RfMolecular.MolecularRfRecipeCategory;
import com.ariks.MolecularRF.integration.Jei.RfMolecularDoubleInput.MolecularRecipeJeiDoubleInput;
import com.ariks.MolecularRF.integration.Jei.RfMolecularDoubleInput.MolecularRfRecipeCategoryDoubleInput;
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
        String idMolecular_Output = MolecularRF.MOD_ID + "_molecular_output";

        registry.handleRecipes(MolecularRecipe.class, MolecularRecipeJei::new, idMolecular);
        registry.addRecipes(MolecularRecipe.getRecipes(), idMolecular);
        registry.addRecipeClickArea(GuiRfMolecular.class, 7, 24, 14, 29, idMolecular);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.RF_Molecular), idMolecular);

        registry.handleRecipes(MolecularRecipeDoubleInput.class, MolecularRecipeJeiDoubleInput::new, idMolecular_Double_input);
        registry.addRecipes(MolecularRecipeDoubleInput.getRecipes(), idMolecular_Double_input);
        registry.addRecipeClickArea(GuiRfMolecularDouble.class, 17, 24, 14, 29, idMolecular_Double_input);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.RF_Molecular_Double), idMolecular_Double_input);

        registry.handleRecipes(MolecularRecipeOutput.class, MolecularRecipeJeiOutput::new, idMolecular_Output);
        registry.addRecipes(MolecularRecipeOutput.getRecipes(), idMolecular_Output);
        registry.addRecipeClickArea(GuiRfMolecularOutput.class, 17, 24, 14, 29, idMolecular_Output);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.RF_Molecular_Output), idMolecular_Output);
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new MolecularRfRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new MolecularRfRecipeCategoryDoubleInput(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new MolecularRfRecipeCategoryOutput(registry.getJeiHelpers().getGuiHelper()));
    }
}