package com.ariks.MolecularRF.integration.Jei.RfMolecularDoubleInput;

import com.ariks.MolecularRF.MolecularRF;
import com.ariks.MolecularRF.Register.RegistryBlock;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MolecularRfRecipeCategoryDoubleInput implements IRecipeCategory<MolecularRecipeJeiDoubleInput> {
    private final IDrawable background;
    private final String localizedName;
    private final IDrawableAnimated progressBar;

    public MolecularRfRecipeCategoryDoubleInput(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(MolecularRF.MOD_ID, "textures/gui/gui_molecular_double_jei.png");
        background = guiHelper.createDrawable(location, 0, 0, 176, 77);
        localizedName = RegistryBlock.RF_Molecular_Double.getLocalizedName();

        ResourceLocation progressLocation = new ResourceLocation(MolecularRF.MOD_ID, "textures/gui/gui_component.png");
        IDrawableStatic progressDrawable = guiHelper.createDrawable(progressLocation, 15, 0, 14, 29);
        progressBar = guiHelper.createAnimatedDrawable(progressDrawable, 100, IDrawableAnimated.StartDirection.TOP, false);
    }
    @Override
    public void drawExtras(@NotNull Minecraft minecraft) {
        progressBar.draw(minecraft, 17, 24);
    }
    @Override
    public @NotNull String getUid() {
        return MolecularRF.MOD_ID + "_molecular_double_input";
    }
    @Override
    public @NotNull String getTitle() {
        return localizedName;
    }
    @Override
    public @NotNull String getModName() {
        return MolecularRF.MOD_NAME;
    }
    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }
    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MolecularRecipeJeiDoubleInput recipeWrapper, @NotNull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 5, 5);
        recipeLayout.getItemStacks().init(1, true, 25, 5);
        recipeLayout.getItemStacks().init(2, false, 15, 54);
        recipeLayout.getItemStacks().set(0, recipeWrapper.getInputs1());
        recipeLayout.getItemStacks().set(1, recipeWrapper.getInputs2());
        recipeLayout.getItemStacks().set(2, recipeWrapper.getOutputs());
    }
}