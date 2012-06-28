package net.minecraft.src;

import java.util.List;

public class BiomeGenWasteland extends BiomeGenBase
{

    public BiomeGenWasteland(int i)
    {
        super(i);
        spawnableCreatureList.clear();
        topBlock = (byte)mod_ExtraBiomesXL.crackedSand.blockID;
        fillerBlock = (byte)mod_ExtraBiomesXL.crackedSand.blockID;
        biomeDecorator.treesPerChunk = -999;
        biomeDecorator.deadBushPerChunk = 3;
        biomeDecorator.reedsPerChunk = -999;
        biomeDecorator.deadGrassPerChunk = 9;
        biomeDecorator.deadGrassYPerChunk = 9;
        biomeDecorator.deadTallGrassPerChunk = 7;
        waterColorMultiplier = 0xF08000;
    }
}

//Thanks for the idea, WhitesBlacks!