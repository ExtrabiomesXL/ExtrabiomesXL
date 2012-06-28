package net.minecraft.src;

import java.util.Random;

public class BlockOrangeFlower extends BlockFlower
{
    protected BlockOrangeFlower(int par1, int par2)
    {
        super(par1, par2, Material.plants);
        float f = 0.4F;
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}
	
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_ExtraBiomesXL.orangeFlowerItem.shiftedIndex;
    }
}