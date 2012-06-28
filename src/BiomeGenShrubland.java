package net.minecraft.src;

public class BiomeGenShrubland extends BiomeGenBase
{

    protected BiomeGenShrubland(int i)
    {
        super(i);
        biomeDecorator.treesPerChunk = -999;
        biomeDecorator.flowersPerChunk = 3;
        biomeDecorator.grassPerChunk = -999;
        biomeDecorator.shortGrassPerChunk = 7;
        biomeDecorator.coverPerChunk = 9;
    }
}