package com.ariks.MolecularRF.Block.RFMolecularOutput;

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
public class GuiRfMolecularOutput extends ExampleGuiContainer {
    private final TileRfMolecularOutput tile;
    public NumberFormat numberFormat = NumberFormat.getNumberInstance();
    private String ItemInput;
    private String ItemOutput;
    private String ItemOutput2;
    private String EnergyNeed;
    private String Collected;
    private String RfPerTick;
    private String Time;

    public GuiRfMolecularOutput(InventoryPlayer inventory, TileRfMolecularOutput tileEntity, EntityPlayer player) {
        super(new ContainerRfMolecularOutput(inventory, tileEntity, player));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_molecular_output.png", 205, 170);
        BarComponent barComponent = new BarComponent(this, 1, 17, 24, 30, 0, 14, 29, "textures/gui/gui_component.png");
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
            int ic = MolecularRecipeOutput.getRecipes().get(RecipeID).getInput().getCount();
            int ic2 = MolecularRecipeOutput.getRecipes().get(RecipeID).getRecipeOutput1().getCount();
            int io = MolecularRecipeOutput.getRecipes().get(RecipeID).getRecipeOutput2().getCount();
            String ni = MolecularRecipeOutput.getRecipes().get(RecipeID).getInput().getDisplayName();
            String ni2 = MolecularRecipeOutput.getRecipes().get(RecipeID).getRecipeOutput1().getDisplayName();
            String no = MolecularRecipeOutput.getRecipes().get(RecipeID).getRecipeOutput2().getDisplayName();
            ItemInput = (ni + " *" + ic);
            ItemOutput2 = (ni2 + " *" + ic2);
            ItemOutput = (no + " *" + io);
            EnergyNeed = (LS.StrEnergyRecipe + " " + EnergyFormat.formatNumber(tile.energyRequired));
            Collected = (LS.StrEnergy + " " + EnergyFormat.formatNumber(tile.energyCollected));
            RfPerTick = (LS.StrRFTick + " " + numberFormat.format(tile.energyReceived));
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
            ItemInput = "";
            ItemOutput2 = "";
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
        this.fontRenderer.drawStringWithShadow(ItemInput, xOffset, yOffset, Color.BLUE.getRGB());
        this.fontRenderer.drawStringWithShadow(ItemOutput, xOffset, yOffset + 10, Color.BLUE.getRGB());
        this.fontRenderer.drawStringWithShadow(ItemOutput2, xOffset, yOffset + 20, Color.BLUE.getRGB());
        this.fontRenderer.drawStringWithShadow(EnergyNeed, xOffset, yOffset + 30, Color.BLUE.getRGB());
        this.fontRenderer.drawStringWithShadow(Collected, xOffset, yOffset + 40, Color.BLUE.getRGB());
        this.fontRenderer.drawStringWithShadow(RfPerTick, xOffset, yOffset + 50, Color.BLUE.getRGB());
        this.fontRenderer.drawStringWithShadow(Time, xOffset, yOffset + 60, Color.BLUE.getRGB());
    }
}