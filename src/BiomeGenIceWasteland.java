package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenIceWasteland extends BiomeGenBase
{

    public BiomeGenIceWasteland(int i)
    {
        super(i);
        spawnableCreatureList.clear();
        topBlock = (byte)Block.blockSnow.blockID;
        fillerBlock = (byte)Block.blockSnow.blockID;
        biomeDecorator.treesPerChunk = -999;
    }
}