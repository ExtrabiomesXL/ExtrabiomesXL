package net.minecraft.src;

import java.util.Random;

public class BlockCoverGrass extends BlockFlower
{
    protected BlockCoverGrass(int par1, int par2)
    {
    	super(par1, par2, Material.vine);
    	setBlockBounds(0.0F, 0.0F, 0.0F, 0.01F, 0.125F, 0.01F);
    }

    public int getBlockColor()
    {
        double d = 0.5D;
        double d1 = 1.0D;
        return ColorizerGrass.getGrassColor(d, d1);
    }
    
    public int getRenderColor(int par1)
    {
            return ColorizerFoliage.getFoliageColorBasic();
    }
    
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int i = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

        return par1IBlockAccess.func_48454_a(par2, par4).func_48415_j();
    }
    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        if (par2Random.nextInt(8) == 0)
        {
            return Item.seeds.shiftedIndex;
        }
        else
        {
            return -1;
        }
    }
    
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return 1 + par2Random.nextInt(par1 * 2 + 1);
    }
}
