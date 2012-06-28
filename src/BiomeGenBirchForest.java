package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenBirchForest extends BiomeGenBase
{

    public BiomeGenBirchForest(int i)
    {
        super(i);
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 5, 4, 4));
        biomeDecorator.treesPerChunk = 7;
        biomeDecorator.grassPerChunk = 1;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if(random.nextInt(1) == 0)
        {
            return worldGenForest;
        }
        if(random.nextInt(9001) == 0)
        {
            return worldGenBigTree;
        } else
        {
            return worldGenTrees;
        }
    }
}