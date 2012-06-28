package net.minecraft.src;

import java.util.Random;

public class WorldGenLeafPile extends WorldGenerator
{
    private int pileID;
    private int pileMetadata;

    public WorldGenLeafPile(int par1, int par2)
    {
        pileID = par1;
        pileMetadata = par2;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        for (int i = 0; ((i = par1World.getBlockId(par3, par4, par5)) == 0 || i == Block.leaves.blockID) && par4 > 0; par4--) { }

        for (int j = 0; j < 128; j++)
        {
            int k = (par3 + par2Random.nextInt(8)) - par2Random.nextInt(8);
            int l = (par4 + par2Random.nextInt(4)) - par2Random.nextInt(4);
            int i1 = (par5 + par2Random.nextInt(8)) - par2Random.nextInt(8);

            if (par1World.isAirBlock(k, l, i1) && ((Block)Block.blocksList[pileID]).canBlockStay(par1World, k, l, i1))
            {
                par1World.setBlockAndMetadata(k, l, i1, pileID, pileMetadata);
            }
        }

        return true;
    }
}