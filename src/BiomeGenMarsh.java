package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenMarsh extends BiomeGenBase
{
    public BiomeGenMarsh(int par1)
    {
        super(par1);
        biomeDecorator.treesPerChunk = -999;
        biomeDecorator.grassPerChunk = 999;
        biomeDecorator.shortGrassPerChunk = -999;
        biomeDecorator.coverPerChunk = -999;
        biomeDecorator.thickGrassPerChunk = -999;
        biomeDecorator.flowersPerChunk = -999;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        WorldGenMarsh worldgenmarsh = new WorldGenMarsh();

        for (int i = 0; i < 127; i++)
        {
            int j = par3 + par2Random.nextInt(16) + 8;
            byte byte0 = 0;
            int k = par4 + par2Random.nextInt(16) + 8;
            worldgenmarsh.generate(par1World, par2Random, j, byte0, k);
        }

        WorldGenDirtBed worldgendirtbed = new WorldGenDirtBed();

        for (int i = 0; i < 256; i++)
        {
            int j = par3 + par2Random.nextInt(1) + 8;
            byte byte0 = 0;
            int k = par4 + par2Random.nextInt(1) + 8;
            worldgendirtbed.generate(par1World, par2Random, j, byte0, k);
        }
    }
}