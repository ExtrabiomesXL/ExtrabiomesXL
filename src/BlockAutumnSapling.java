package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

public class BlockAutumnSapling extends BlockFlower implements ITextureProvider
{
    protected BlockAutumnSapling(int par1, int par2)
    {
        super(par1, par2);
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.isRemote)
        {
            return;
        }

        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
        {
            int i = par1World.getBlockMetadata(par2, par3, par4);

            if ((i & 8) == 0)
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, i | 8);
            }
            else
            {
                growTree(par1World, par2, par3, par4, par5Random);
            }
        }
    }

    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int i = par1World.getBlockMetadata(par2, par3, par4) & 3;
        Object obj = null;
        int j = 0;
        int k = 0;
		int sap = par5Random.nextInt(5);
        boolean flag = false;

		if (sap == 0)
        {
        obj = new WorldGenTreesBrown(true);
        }
        if (sap == 1)
        {
        obj = new WorldGenTreesBrown(true);
        }
        if (sap == 2)
        {
         obj = new WorldGenTreesRed(true);
        }
        if (sap == 3)
        {
         obj = new WorldGenTreesOrange(true);
        }
        if (sap == 4)
        {
         obj = new WorldGenTreesYellow(true);
        }
        if (sap == 5)
        {
         obj = new WorldGenTreesYellow(true);
        }

        if (flag)
        {
            par1World.setBlock(par2 + j, par3, par4 + k, 0);
            par1World.setBlock(par2 + j + 1, par3, par4 + k, 1);
            par1World.setBlock(par2 + j, par3, par4 + k + 1, 2);
            par1World.setBlock(par2 + j + 1, par3, par4 + k + 1, 3);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, 0);
        }

        if (!((WorldGenerator)(obj)).generate(par1World, par5Random, par2 + j, par3, par4 + k))
        {
            if (flag)
            {
                par1World.setBlockAndMetadata(par2 + j, par3, par4 + k, blockID, i);
                par1World.setBlockAndMetadata(par2 + j + 1, par3, par4 + k, blockID, i);
                par1World.setBlockAndMetadata(par2 + j, par3, par4 + k + 1, blockID, i);
                par1World.setBlockAndMetadata(par2 + j + 1, par3, par4 + k + 1, blockID, i);
            }
            else
            {
                par1World.setBlockAndMetadata(par2, par3, par4, blockID, i);
            }
        }
    }

    protected int damageDropped(int par1)
    {
        return par1 & 3;
    }
    
    public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
    }
    
    public String getTextureFile()
    {
            return "/ExtraBiomesXL/extrabiomes.png";
    }
}