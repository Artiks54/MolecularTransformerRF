package com.ariks.MolecularRF.Block.Core;

import net.minecraftforge.energy.IEnergyStorage;

public class EnergyStorageMolecular implements IEnergyStorage {
    private int energy;
    private final int maxReceive;
    private final int maxExtract;
    private final int maxCapacity;
    private boolean canReceiveEnergy;
    public EnergyStorageMolecular(int maxReceive, int maxExtract, int maxCapacity) {
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.maxCapacity = maxCapacity;
    }
    public void consumeEnergy(int amount) {
        int energyConsumed = Math.min(energy, amount);
        energy -= energyConsumed;
    }
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energyReceived = Math.min(maxCapacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }
    @Override
    public int getEnergyStored() {
        return energy;
    }
    @Override
    public int getMaxEnergyStored() {
        return maxCapacity;
    }
    @Override
    public boolean canExtract() {
        return false;
    }
    @Override
    public boolean canReceive() {
        return canReceiveEnergy && this.maxReceive > 0;
    }
    public void setCanReceiveEnergy(boolean canReceiveEnergy) {
        this.canReceiveEnergy = canReceiveEnergy;
    }
}