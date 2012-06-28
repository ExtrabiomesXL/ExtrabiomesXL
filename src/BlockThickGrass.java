package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

public class BlockThickGrass extends BlockFlower implements ITextureProvider
{
    protected BlockThickGrass(int par1, int par2)
    {
    	super(par1, par2, Material.vine);
    	float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.7F, 0.5F + var3);
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
    
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor();
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
    
    public String getTextureFile()
    {
    	return "/ExtraBiomesXL/extrabiomes.png";
    }
}