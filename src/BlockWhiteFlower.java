package net.minecraft.src;

import java.util.Random;

public class BlockWhiteFlower extends BlockFlower
{
    protected BlockWhiteFlower(int par1, int par2)
    {
        super(par1, par2, Material.plants);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);

	}
	
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_ExtraBiomesXL.whiteFlowerItem.shiftedIndex;
    }
    
    public int getRenderType()
    {
    	return 6;
    }
}