package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

public class BlockShortGrass extends BlockFlower implements ITextureProvider
{
    protected BlockShortGrass(int par1, int par2)
    {
    	super(par1, par2, Material.vine);
    	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F);
    }

    public int getBlockColor()
    {
        double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerGrass.getGrassColor(var1, var3);
    }
    
    public int getRenderColor(int par1)
    {
        return par1 == 0 ? 16777215 : ColorizerFoliage.getFoliageColorBasic();
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
    
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        return var5 == 0 ? 16777215 : par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor();
    }
    
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return 1 + par2Random.nextInt(par1 * 2 + 1);
    }
    
    public String getTextureFile()
    {
    	return "/ExtraBiomesXL/extrabiomes.png";
    }
}