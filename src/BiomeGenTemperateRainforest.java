package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenTemperateRainforest extends BiomeGenBase
{

    public BiomeGenTemperateRainforest(int i)
    {
        super(i);
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 5, 4, 4));
        biomeDecorator.treesPerChunk = 17;
        biomeDecorator.grassPerChunk = 25;
        biomeDecorator.shortGrassPerChunk = 5;
        biomeDecorator.coverPerChunk = 2;
        biomeDecorator.thickGrassPerChunk = 10;
        biomeDecorator.mushroomsPerChunk = 4;
        biomeDecorator.toadstoolsPerChunk = 2;
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
            return worldGenFirTree;
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