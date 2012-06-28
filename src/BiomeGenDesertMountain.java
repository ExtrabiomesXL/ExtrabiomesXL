package net.minecraft.src;

import java.util.Random;

public class BiomeGenDesertMountain extends BiomeGenBase
{
    public BiomeGenDesertMountain(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.sand.blockID;
        this.fillerBlock = (byte)Block.sand.blockID;
        this.biomeDecorator.treesPerChunk = -999;
        this.biomeDecorator.deadBushPerChunk = 4;
        this.biomeDecorator.reedsPerChunk = 70;
        this.biomeDecorator.cactiPerChunk = 13;
        this.biomeDecorator.tinyCactiPerChunk = 6;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);

        if (par2Random.nextInt(1000) == 0)
        {
            int var5 = par3 + par2Random.nextInt(16) + 8;
            int var6 = par4 + par2Random.nextInt(16) + 8;
            WorldGenDesertWells var7 = new WorldGenDesertWells();
            var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
        }
    }
}
