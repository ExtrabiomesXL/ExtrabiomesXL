package net.minecraft.src;

import java.util.Random;

public class BlockHydrangea extends BlockFlower
{
    protected BlockHydrangea(int par1, int par2)
    {
        super(par1, par2, Material.plants);
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}
	
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_ExtraBiomesXL.hydrangeaItem.shiftedIndex;
    }
}