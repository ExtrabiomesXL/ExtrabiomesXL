package net.minecraft.server;

import extrabiomes.Extrabiomes;
import forge.NetworkMod;
import java.util.Random;

public class mod_ExtrabiomesXL extends NetworkMod
{
    public mod_ExtrabiomesXL()
    {
    }

    public int addFuel(int i, int j)
    {
        return Extrabiomes.addFuel(i, j);
    }

    public boolean clientSideRequired()
    {
        return Extrabiomes.clientSideRequired();
    }

    public void generateSurface(World world, Random random, int i, int j)
    {
        Extrabiomes.generateSurface(world, random, i, j);
    }

    public String getName()
    {
        return Extrabiomes.getName();
    }

    public String getPriorities()
    {
        return Extrabiomes.getPriorities();
    }

    public String getVersion()
    {
        return Extrabiomes.getVersion();
    }

    public void load()
    {
        Extrabiomes.load();
    }

    public void modsLoaded()
    {
        Extrabiomes.modsLoaded(this);
    }

    public void takenFromCrafting(EntityHuman entityhuman, ItemStack itemstack, IInventory iinventory)
    {
        Extrabiomes.takenFromCrafting(entityhuman, itemstack, iinventory);
    }

    public String toString()
    {
        return (new StringBuilder()).append(getName()).append(" ").append(Extrabiomes.getVersionNumber()).toString();
    }
}
