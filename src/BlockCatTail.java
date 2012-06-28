package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

public class BlockCatTail extends BlockFlower implements ITextureProvider
{
    protected BlockCatTail(int par1, int par2)
    {
        super(par1, par2, Material.plants);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
    }
    
    public String getTextureFile()
    {
            return "/ExtraBiomesXL/extrabiomes.png";
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
    	int i = par1World.getBlockId(par2, par3 - 1, par4);

    	if (i != Block.grass.blockID && i != Block.dirt.blockID)
    	{
    		return false;
    	}

    	if (par1World.getBlockMaterial(par2 - 1, par3 - 1, par4) == Material.water)
    	{
    		return true;
    	}

    	if (par1World.getBlockMaterial(par2 + 1, par3 - 1, par4) == Material.water)
    	{
    		return true;
    	}

    	if (par1World.getBlockMaterial(par2, par3 - 1, par4 - 1) == Material.water)
    	{
    		return true;
    	}

    	return par1World.getBlockMaterial(par2, par3 - 1, par4 + 1) == Material.water;
    }
    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_ExtraBiomesXL.catTailItem.shiftedIndex;
    }
    
    public int getRenderType()
    {
    	return 6;
    }
}