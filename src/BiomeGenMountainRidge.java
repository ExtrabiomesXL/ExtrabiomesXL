package net.minecraft.src;

import java.util.Random;

public class BiomeGenMountainRidge extends BiomeGenBase
{
    public BiomeGenMountainRidge(int i)
    {
        super(i);
        spawnableCreatureList.clear();
        topBlock = (byte)mod_ExtraBiomesXL.redRock.blockID;
        fillerBlock = (byte)mod_ExtraBiomesXL.redRock.blockID;
        biomeDecorator.brownGrassPerChunk = 100;
        biomeDecorator.brownGrassShortPerChunk = 100;
        biomeDecorator.tinyCactiPerChunk = 10;
        biomeDecorator.oasisPerChunk = 999;
        biomeDecorator.grassPerChunk = 999;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if (random.nextInt(10) == 0)
        {
            return worldGenBluff;
        }
        else
        {
            return worldGenSavanna;
        }
    }
}
