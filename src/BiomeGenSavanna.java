package net.minecraft.src;

import java.util.Random;

public class BiomeGenSavanna extends BiomeGenBase
{

    protected BiomeGenSavanna(int i)
    {
        super(i);
        biomeDecorator.treesPerChunk = 0;
        biomeDecorator.flowersPerChunk = 1;
        biomeDecorator.grassPerChunk = 17;
        biomeDecorator.purpleFlowerPerChunk = 1;
    }
    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        return worldGenSavanna;
    }
}