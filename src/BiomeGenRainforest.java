package net.minecraft.src;

import java.util.Random;

public class BiomeGenRainforest extends BiomeGenBase
{

    protected BiomeGenRainforest(int i)
    {
        super(i);
        biomeDecorator.treesPerChunk = 7;
        biomeDecorator.flowersPerChunk = 2;
        biomeDecorator.grassPerChunk = 4;
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if(random.nextInt(99999) == 0)
        {
            return worldGenForest;
        }
        if(random.nextInt(4) == 0)
        {
            return worldGenBigTree;
        } else
        {
            return worldGenTrees;
        }
    }
    
    public WorldGenerator func_48410_b(Random par1Random)
    {
        if (par1Random.nextInt(4) == 0)
        {
            return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
        }
        else
        {
            return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
        }
    }
}