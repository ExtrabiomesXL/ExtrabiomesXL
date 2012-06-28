package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenWoodlands extends BiomeGenBase
{
    public BiomeGenWoodlands(int i)
    {
        super(i);
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 5, 4, 4));
        biomeDecorator.treesPerChunk = 8;
        biomeDecorator.grassPerChunk = 3;
        biomeDecorator.shortGrassPerChunk = 1;
        biomeDecorator.leafPilePerChunk = 30;
    }
}
