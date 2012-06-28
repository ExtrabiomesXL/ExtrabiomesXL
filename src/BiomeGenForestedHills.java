package net.minecraft.src;

public class BiomeGenForestedHills extends BiomeGenBase
{

    protected BiomeGenForestedHills(int i)
    {
        super(i);
        biomeDecorator.treesPerChunk = 7;
        biomeDecorator.flowersPerChunk = 1;
        biomeDecorator.grassPerChunk = 5;
    }
}