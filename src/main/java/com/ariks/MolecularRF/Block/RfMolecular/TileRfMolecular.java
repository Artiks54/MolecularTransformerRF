package com.ariks.MolecularRF.Block.RfMolecular;

import com.ariks.MolecularRF.Block.Core.TileExampleInventory;
import com.ariks.MolecularRF.Block.Core.EnergyStorageMolecular;
import com.ariks.MolecularRF.Register.RegistryGui;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class TileRfMolecular extends TileExampleInventory implements ITickable {
    private final EnergyStorageMolecular storage;
    private int currentRecipeID = -1;
    public long energyReceived;
    public long energyRequired;
    public long energyCollected;
    public boolean work;
    public TileRfMolecular() {
        super(2);
        this.setSlotsForInsert(0);
        this.setSlotsForExtract(1);
        this.storage = new EnergyStorageMolecular(Integer.MAX_VALUE,0,Integer.MAX_VALUE);
    }
    private ArrayList<MolecularRecipe> currentRecipe() {
        return MolecularRecipe.getRecipes();
    }
    public void findRecipe() {
        if (currentRecipeID < 0) {
            ItemStack inputStack = getStackInSlot(0);
            ItemStack outputStack = getStackInSlot(1);
            if (!inputStack.isEmpty()) {
                for (int i = 0; i < currentRecipe().size(); i++) {
                    MolecularRecipe recipe = currentRecipe().get(i);
                    if (recipe.getInput().isItemEqual(inputStack) && ItemStack.areItemStackTagsEqual(recipe.getInput(), inputStack)) {
                        if (inputStack.getCount() >= recipe.getInput().getCount()) {
                            if (outputStack.isEmpty() || (outputStack.isItemEqual(recipe.getRecipeOutput()) && ItemStack.areItemStackTagsEqual(outputStack, recipe.getRecipeOutput()))) {
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
        ItemStack outputStack = currentRecipe().get(currentRecipeID).getRecipeOutput();
        ItemStack currentOutputStack = getStackInSlot(1);
        if (currentOutputStack.isEmpty()) {
            return true;
        } else if (currentOutputStack.isItemEqual(outputStack) && ItemStack.areItemStackTagsEqual(currentOutputStack, outputStack)) {
            int maxStackSize = currentOutputStack.getMaxStackSize();
            int currentCount = currentOutputStack.getCount();
            int outputCount = outputStack.getCount();
            return currentCount + outputCount <= maxStackSize;
        } else {
            return false;
        }
    }
    private void RecipeOut() {
        if (energyCollected >= energyRequired) {
            ItemStack outputStack = currentRecipe().get(currentRecipeID).getRecipeOutput();
            if (getStackInSlot(1).isEmpty()) {
                setInventorySlotContents(1, outputStack);
            } else {
                getStackInSlot(1).grow(outputStack.getCount());
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

    @Override
    public @NotNull AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
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
        return String.valueOf(RegistryGui.GUI_RF_MOLECULAR);
    }
}