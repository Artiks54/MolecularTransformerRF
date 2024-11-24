package com.ariks.MolecularRF.integration.Jei.RFMolecularOutput;

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

public class MolecularRfRecipeCategoryOutput implements IRecipeCategory<MolecularRecipeJeiOutput> {
    private final IDrawable background;
    private final String localizedName;
    private final IDrawableAnimated progressBar;

    public MolecularRfRecipeCategoryOutput(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(MolecularRF.MOD_ID, "textures/gui/gui_molecular_output_jei.png");
        background = guiHelper.createDrawable(location, 0, 0, 176, 77);
        localizedName = RegistryBlock.RF_Molecular_Output.getLocalizedName();

        ResourceLocation progressLocation = new ResourceLocation(MolecularRF.MOD_ID, "textures/gui/gui_component.png");
        IDrawableStatic progressDrawable = guiHelper.createDrawable(progressLocation, 30, 0, 14, 29);
        progressBar = guiHelper.createAnimatedDrawable(progressDrawable, 100, IDrawableAnimated.StartDirection.TOP, false);
    }
    @Override
    public void drawExtras(@NotNull Minecraft minecraft) {
        progressBar.draw(minecraft, 17, 24);
    }
    @Override
    public @NotNull String getUid() {
        return MolecularRF.MOD_ID + "_molecular_output";
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
    public void setRecipe(IRecipeLayout recipeLayout, MolecularRecipeJeiOutput recipeWrapper, @NotNull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 15, 5);
        recipeLayout.getItemStacks().init(1, false, 5, 54);
        recipeLayout.getItemStacks().init(2, false, 25, 54);
        recipeLayout.getItemStacks().set(0, recipeWrapper.getInputs());
        recipeLayout.getItemStacks().set(1, recipeWrapper.getOutputs1());
        recipeLayout.getItemStacks().set(2, recipeWrapper.getOutputs2());
    }
}