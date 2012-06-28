package net.minecraft.src;

import java.util.Random;

public class WorldGenMarsh extends WorldGenerator
{
    public WorldGenMarsh()
    {
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int i = par3;
        int j = par5;
        label0:

        for (; par4 < 63; par4++)
        {
            if (!par1World.isAirBlock(par3, par4, par5))
            {
                int k = 2;

                do
                {
                    if (k > 5)
                    {
                        continue label0;
                    }

                    if (Block.dirt.canPlaceBlockOnSide(par1World, par3, par4, par5, k))
                    {
                        par1World.setBlockAndMetadata(par3, par4, par5, Block.grass.blockID, 1 << Direction.vineGrowth[Facing.faceToSide[k]]);
                        continue label0;
                    }

                    k++;
                }
                while (true);
            }

            par3 = (i + par2Random.nextInt(4)) - par2Random.nextInt(4);
            par5 = (j + par2Random.nextInt(4)) - par2Random.nextInt(4);
        }

        return true;
    }
}
