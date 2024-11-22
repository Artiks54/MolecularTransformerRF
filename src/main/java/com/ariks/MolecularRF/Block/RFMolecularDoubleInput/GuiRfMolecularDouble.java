package com.ariks.MolecularRF.Block.RFMolecularDoubleInput;

import com.ariks.MolecularRF.Block.Core.ExampleGuiContainer;
import com.ariks.MolecularRF.Gui.BarComponent;
import com.ariks.MolecularRF.util.EnergyFormat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;
import java.text.NumberFormat;

@SideOnly(Side.CLIENT)
public class GuiRfMolecularDouble extends ExampleGuiContainer {
    private final TileRfMolecularDoubleInput tile;
    public NumberFormat numberFormat = NumberFormat.getNumberInstance();
    private String ItemInput;
    private String ItemInput2;
    private String ItemOutput;
    private String EnergyNeed;
    private String Collected;
    private String RfPerTick;
    private String Time,Recipe;

    public GuiRfMolecularDouble(InventoryPlayer inventory, TileRfMolecularDoubleInput tileEntity, EntityPlayer player) {
        super(new ContainerRfMolecularDouble(inventory, tileEntity, player));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_molecular_double.png", 175, 167);
        BarComponent barComponent = new BarComponent(this, 1, 17, 24, 15, 0, 14, 29, "textures/gui/gui_component.png");
        barComponent.setSideDirection("down");
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        this.getRecipe();
    }
    private void getRecipe() {
        int RecipeID = tile.getValue(1);
        if (RecipeID >= 0) {
            int ic = MolecularRecipeDoubleInput.getRecipes().get(RecipeID).getInput1().getCount();
            int ic2 = MolecularRecipeDoubleInput.getRecipes().get(RecipeID).getInput2().getCount();
            int io = MolecularRecipeDoubleInput.getRecipes().get(RecipeID).getRecipeOutput().getCount();
            String ni = MolecularRecipeDoubleInput.getRecipes().get(RecipeID).getInput1().getDisplayName();
            String ni2 = MolecularRecipeDoubleInput.getRecipes().get(RecipeID).getInput2().getDisplayName();
            String no = MolecularRecipeDoubleInput.getRecipes().get(RecipeID).getRecipeOutput().getDisplayName();
            ItemInput = (ni + " *" + ic);
            ItemInput2 = (ni2 + " *" + ic2);
            ItemOutput = (no + " *" + io);
            EnergyNeed = (LS.StrEnergyRecipe + " " + EnergyFormat.formatNumber(tile.energyRequired));
            Collected = (LS.StrEnergy + " " + EnergyFormat.formatNumber(tile.energyCollected));
            RfPerTick = (LS.StrRFTick + " " + numberFormat.format(tile.energyReceived));
            Recipe = LS.StrRecipe;
            if (tile.energyReceived != 0) {
                long ticks = (tile.energyRequired - tile.energyCollected) / tile.energyReceived;
                long seconds = ticks / 20;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                seconds = seconds % 60;
                minutes = minutes % 60;
                Time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            }
            int progressPercentage = (int) ((tile.energyCollected * 100) / tile.energyRequired);
            setBarValue(1, progressPercentage, 100);
        } else {
            Recipe = LS.StrRecipeOff;
            ItemInput = "";
            ItemInput2 = "";
            ItemOutput = "";
            EnergyNeed = "";
            Collected = "";
            RfPerTick = "";
            Time = "";
            setBarValue(1, 0, 100);
        }
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        int xOffset = 55;
        int yOffset = 5;
        this.fontRenderer.drawStringWithShadow(Recipe, xOffset, yOffset, Color.GREEN.getRGB());
        this.fontRenderer.drawStringWithShadow(ItemInput, xOffset, yOffset+10, Color.GREEN.getRGB());
        this.fontRenderer.drawStringWithShadow(ItemInput2, xOffset, yOffset+20, Color.GREEN.getRGB());
        this.fontRenderer.drawStringWithShadow(ItemOutput, xOffset, yOffset + 30, Color.GREEN.getRGB());
        this.fontRenderer.drawStringWithShadow(EnergyNeed, xOffset, yOffset + 40, Color.GREEN.getRGB());
        this.fontRenderer.drawStringWithShadow(Collected, xOffset, yOffset + 50, Color.GREEN.getRGB());
        this.fontRenderer.drawStringWithShadow(RfPerTick, xOffset, yOffset + 60, Color.GREEN.getRGB());
        this.fontRenderer.drawStringWithShadow(Time, xOffset, yOffset + 70, Color.GREEN.getRGB());
    }
}