package net.minecraft.src;

import java.util.Random;

public class WorldGenTinyCactus extends WorldGenerator
{
    public WorldGenTinyCactus()
    {
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        for (int i = 0; i < 10; i++)
        {
            int j = (par3 + par2Random.nextInt(8)) - par2Random.nextInt(8);
            int k = (par4 + par2Random.nextInt(4)) - par2Random.nextInt(4);
            int l = (par5 + par2Random.nextInt(8)) - par2Random.nextInt(8);

            if (!par1World.isAirBlock(j, k, l))
            {
                continue;
            }

            int i1 = 1 + par2Random.nextInt(par2Random.nextInt(3) + 1);

            for (int j1 = 0; j1 < i1; j1++)
            {
                if (mod_ExtraBiomesXL.tinyCactus.canBlockStay(par1World, j, k + j1, l))
                {
                    par1World.setBlock(j, k + j1, l, mod_ExtraBiomesXL.tinyCactus.blockID);
                }
            }
        }

        return true;
    }
}
