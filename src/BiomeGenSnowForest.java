package net.minecraft.src;

public class BiomeGenSnowForest extends BiomeGenBase
{

    protected BiomeGenSnowForest(int i)
    {
        super(i);
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 5, 4, 4));
        biomeDecorator.treesPerChunk = 8;
        biomeDecorator.flowersPerChunk = 1;
        biomeDecorator.grassPerChunk = 4;
    }
}