package com.ariks.MolecularRF.integration.Jei.RfMolecularDoubleInput;

import com.ariks.MolecularRF.Block.RFMolecularDoubleInput.MolecularRecipeDoubleInput;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import org.jetbrains.annotations.NotNull;

public class MolecularRfWrapperDoubleInput implements IRecipeWrapperFactory<MolecularRecipeDoubleInput> {

    @Override
    public @NotNull IRecipeWrapper getRecipeWrapper(@NotNull MolecularRecipeDoubleInput MolecularRecipeDoubleInput) {
        return new MolecularRecipeJeiDoubleInput(MolecularRecipeDoubleInput);
    }
}
