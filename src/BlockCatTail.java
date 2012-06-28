package net.minecraft.src;

import java.util.Random;

public class BlockCatTail extends Block
{
    protected BlockCatTail(int par1, int par2)
    {
    	super(par1, Material.plants);
        float f = 0.375F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        setTickRandomly(true);
        
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.isAirBlock(par2, par3 + 1, par4))
        {
            int i;

            for (i = 1; par1World.getBlockId(par2, par3 - i, par4) == blockID; i++) { }

            if (i < 3)
            {
                int j = par1World.getBlockMetadata(par2, par3, par4);

                if (j == 15)
                {
                    par1World.setBlockWithNotify(par2, par3 + 1, par4, blockID);
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, 0);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, j + 1);
                }
            }
        }
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        int i = par1World.getBlockId(par2, par3 - 1, par4);

        if (i == blockID)
        {
            return true;
        }

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

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        checkBlockCoordValid(par1World, par2, par3, par4);
    }

    protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4)
    {
        if (!canBlockStay(par1World, par2, par3, par4))
        {
            dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockWithNotify(par2, par3, par4, 0);
        }
    }

    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return canPlaceBlockAt(par1World, par2, par3, par4);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int i)
    {
        return null;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_ExtraBiomesXL.catTailItem.shiftedIndex;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 6;
    }
}
