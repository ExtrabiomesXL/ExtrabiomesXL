package net.minecraft.src;

import java.util.Random;

public class WorldGenBrownGrass extends WorldGenerator
{
    private int brownGrassID;

    public WorldGenBrownGrass(int par1)
    {
        brownGrassID = par1;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        for (int i = 0; ((i = par1World.getBlockId(par3, par4, par5)) == 0 || i == Block.leaves.blockID) && par4 > 0; par4--) { }

        for (int j = 0; j < 4; j++)
        {
            int k = (par3 + par2Random.nextInt(8)) - par2Random.nextInt(8);
            int l = (par4 + par2Random.nextInt(4)) - par2Random.nextInt(4);
            int i1 = (par5 + par2Random.nextInt(8)) - par2Random.nextInt(8);

            if (par1World.isAirBlock(k, l, i1) && ((BlockFlower)Block.blocksList[brownGrassID]).canBlockStay(par1World, k, l, i1))
            {
                par1World.setBlock(k, l, i1, brownGrassID);
            }
        }

        return true;
    }
}