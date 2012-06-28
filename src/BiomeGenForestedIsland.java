package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenForestedIsland extends BiomeGenBase
{

    public BiomeGenForestedIsland(int i)
    {
        super(i);
        biomeDecorator.treesPerChunk = 7;
        biomeDecorator.grassPerChunk = 3;
    }
}