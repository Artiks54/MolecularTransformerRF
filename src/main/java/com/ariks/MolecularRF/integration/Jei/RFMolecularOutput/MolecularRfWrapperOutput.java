package com.ariks.MolecularRF.integration.Jei.RFMolecularOutput;

import com.ariks.MolecularRF.Block.RFMolecularOutput.MolecularRecipeOutput;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import org.jetbrains.annotations.NotNull;

public class MolecularRfWrapperOutput implements IRecipeWrapperFactory<MolecularRecipeOutput> {

    @Override
    public @NotNull IRecipeWrapper getRecipeWrapper(@NotNull MolecularRecipeOutput MolecularRecipeOutput) {
        return new MolecularRecipeJeiOutput(MolecularRecipeOutput);
    }
}
