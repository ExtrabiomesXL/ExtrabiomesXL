package net.minecraft.src;

import java.util.Random;

public class WorldGenPit extends WorldGenerator
{
    public WorldGenPit()
    {
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        for (; par1World.isAirBlock(par3, par4, par5) && par4 > 2; par4--) { }

        int i = par1World.getBlockId(par3, par4, par5);

        if (i != Block.sand.blockID)
        {
            return false;
        }

        for (int j = -2; j <= 2; j++)
        {
            for (int k1 = -2; k1 <= 2; k1++)
            {
                if (par1World.isAirBlock(par3 + j, par4 - 1, par5 + k1) && par1World.isAirBlock(par3 + j, par4 - 2, par5 + k1))
                {
                    return false;
                }
            }
        }

        par1World.setBlock(par3, par4, par5, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3 - 1, par4, par5, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3 + 1, par4, par5, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3, par4, par5 - 1, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3, par4, par5 + 1, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3, par4 - 1, par5, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3 - 1, par4 - 1, par5, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3 + 1, par4 - 1, par5, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3, par4 - 1, par5 - 1, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3, par4 - 1, par5 + 1, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3, par4 - 2, par5, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3 - 1, par4 - 2, par5, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3 + 1, par4 - 2, par5, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3, par4 - 2, par5 - 1, mod_ExtraBiomesXL.quicksand.blockID);
        par1World.setBlock(par3, par4 - 2, par5 + 1, mod_ExtraBiomesXL.quicksand.blockID);

        return true;
    }
}