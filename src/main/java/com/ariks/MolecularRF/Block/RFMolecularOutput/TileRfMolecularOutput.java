package com.ariks.MolecularRF.Block.RFMolecularOutput;

import com.ariks.MolecularRF.Block.Core.EnergyStorageMolecular;
import com.ariks.MolecularRF.Block.Core.TileExampleInventory;
import com.ariks.MolecularRF.Register.RegistryGui;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class TileRfMolecularOutput extends TileExampleInventory implements ITickable {
    private final EnergyStorageMolecular storage;
    private int currentRecipeID = -1;
    public long energyReceived;
    public long energyRequired;
    public long energyCollected;
    public boolean work;
    public TileRfMolecularOutput() {
        super(3);
        this.setSlotsForInsert(0);
        this.setSlotsForExtract(1,2);
        this.storage = new EnergyStorageMolecular(Integer.MAX_VALUE,0,Integer.MAX_VALUE);
    }
    private ArrayList<MolecularRecipeOutput> currentRecipe() {
        return MolecularRecipeOutput.getRecipes();
    }
    public void findRecipe() {
        if (currentRecipeID < 0) {
            ItemStack inputStack = getStackInSlot(0);
            ItemStack outputStack1 = getStackInSlot(1);
            ItemStack outputStack2 = getStackInSlot(2);
            if (!inputStack.isEmpty()) {
                for (int i = 0; i < currentRecipe().size(); i++) {
                    MolecularRecipeOutput recipe = currentRecipe().get(i);
                    if (recipe.getInput().isItemEqual(inputStack) && ItemStack.areItemStackTagsEqual(recipe.getInput(), inputStack)) {
                        if (inputStack.getCount() >= recipe.getInput().getCount()) {
                            if (outputStack1.isEmpty() || (outputStack1.isItemEqual(recipe.getRecipeOutput1()) && ItemStack.areItemStackTagsEqual(outputStack1, recipe.getRecipeOutput1()))) {
                                if (outputStack2.isEmpty() || (outputStack2.isItemEqual(recipe.getRecipeOutput2()) && ItemStack.areItemStackTagsEqual(outputStack2, recipe.getRecipeOutput2()))) {
                                    currentRecipeID = i;
                                    work = true;
                                    energyRequired = currentRecipe().get(currentRecipeID).getEnergy();
                                    decrStackSize(0, recipe.getInput().getCount());
                                    this.UpdateTile();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private void Work() {
        if (work && canOutputRecipeResult()) {
            storage.setCanReceiveEnergy(true);
            energyReceived = storage.getEnergyStored();
            if (energyReceived > 0) {
                storage.consumeEnergy((int) energyReceived);
                energyCollected += energyReceived;
                this.UpdateTile();
                this.RecipeOut();
            }
        }
    }
    private void Reset() {
        energyCollected = 0;
        energyRequired = 0;
        energyReceived = 0;
        currentRecipeID = -1;
        storage.setCanReceiveEnergy(false);
        work = false;
    }
    private boolean canOutputRecipeResult() {
        MolecularRecipeOutput recipe = currentRecipe().get(currentRecipeID);
        ItemStack outputStack1 = recipe.getRecipeOutput1();
        ItemStack outputStack2 = recipe.getRecipeOutput2();
        ItemStack currentOutputStack1 = getStackInSlot(1);
        ItemStack currentOutputStack2 = getStackInSlot(2);
        if (currentOutputStack1.isEmpty() || (currentOutputStack1.isItemEqual(outputStack1) && ItemStack.areItemStackTagsEqual(currentOutputStack1, outputStack1))) {
            int maxStackSize1 = currentOutputStack1.isEmpty() ? outputStack1.getMaxStackSize() : currentOutputStack1.getMaxStackSize();
            int currentCount1 = currentOutputStack1.isEmpty() ? 0 : currentOutputStack1.getCount();
            int outputCount1 = outputStack1.getCount();
            if (currentCount1 + outputCount1 <= maxStackSize1) {
                if (currentOutputStack2.isEmpty() || (currentOutputStack2.isItemEqual(outputStack2) && ItemStack.areItemStackTagsEqual(currentOutputStack2, outputStack2))) {
                    int maxStackSize2 = currentOutputStack2.isEmpty() ? outputStack2.getMaxStackSize() : currentOutputStack2.getMaxStackSize();
                    int currentCount2 = currentOutputStack2.isEmpty() ? 0 : currentOutputStack2.getCount();
                    int outputCount2 = outputStack2.getCount();
                    return currentCount2 + outputCount2 <= maxStackSize2;
                }
            }
        }
        return false;
    }
    private void RecipeOut() {
        if (energyCollected >= energyRequired) {
            MolecularRecipeOutput recipe = currentRecipe().get(currentRecipeID);
            ItemStack outputStack1 = recipe.getRecipeOutput1();
            ItemStack outputStack2 = recipe.getRecipeOutput2();
            if (getStackInSlot(1).isEmpty()) {
                setInventorySlotContents(1, outputStack1);
            } else if (getStackInSlot(1).isItemEqual(outputStack1) && ItemStack.areItemStackTagsEqual(getStackInSlot(1), outputStack1)) {
                getStackInSlot(1).grow(outputStack1.getCount());
            }
            if (getStackInSlot(2).isEmpty()) {
                setInventorySlotContents(2, outputStack2);
            } else if (getStackInSlot(2).isItemEqual(outputStack2) && ItemStack.areItemStackTagsEqual(getStackInSlot(2), outputStack2)) {
                getStackInSlot(2).grow(outputStack2.getCount());
            }
            this.Reset();
        }
    }
    @Override
    public void update() {
        if (!world.isRemote) {
            this.findRecipe();
            this.Work();
        }
    }
    @Override
    public int getValue(int id) {
        if(id == 1){
            return currentRecipeID;
        }
        return id;
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setLong("Stored",energyCollected);
        nbt.setLong("Er",energyRequired);
        nbt.setLong("Rt",energyReceived);
        nbt.setInteger("RecipeID",currentRecipeID);
        nbt.setBoolean("Work",work);
        return nbt;
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.energyRequired = nbt.getLong("Er");
        this.energyReceived = nbt.getLong("Rt");
        this.energyCollected = nbt.getLong("Stored");
        this.currentRecipeID = nbt.getInteger("RecipeID");
        this.work = nbt.getBoolean("Work");
        this.storage.setCanReceiveEnergy(work);
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return (T) storage;
        }
        return super.getCapability(capability, facing);
    }
    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
    @Override
    public String getGuiID() {
        return String.valueOf(RegistryGui.GUI_RF_MOLECULAR_OUTPUT);
    }
}