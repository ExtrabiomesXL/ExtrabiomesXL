package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenAutumnWoods extends BiomeGenBase
{

    public BiomeGenAutumnWoods(int i)
    {
        super(i);
        biomeDecorator.treesPerChunk = 9;
        biomeDecorator.grassPerChunk = 6;
        biomeDecorator.mushroomsPerChunk = 3;
        biomeDecorator.toadstoolsPerChunk = 2;
		biomeDecorator.autumnShrubPerChunk = 2;
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if (random.nextInt(3) == 0)
        {
            return worldGenTreesBrown;
        }
        if (random.nextInt(3) == 0)
        {
            return worldGenTreesOrange;
        }
        if (random.nextInt(3) == 0)
        {
            return worldGenTreesRed;
        }
        if (random.nextInt(3) == 0)
        {
            return worldGenTreesYellow;
        }
        else
        {
            return worldGenTrees;
        }
    }
}