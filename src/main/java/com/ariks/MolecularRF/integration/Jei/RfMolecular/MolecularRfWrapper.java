package com.ariks.MolecularRF.integration.Jei.RfMolecular;

import com.ariks.MolecularRF.Block.RfMolecular.MolecularRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import org.jetbrains.annotations.NotNull;

public class MolecularRfWrapper implements IRecipeWrapperFactory<MolecularRecipe> {

    @Override
    public @NotNull IRecipeWrapper getRecipeWrapper(@NotNull MolecularRecipe MolecularRecipe) {
        return new MolecularRecipeJei(MolecularRecipe);
    }
}
