package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenTemperateRainforest extends BiomeGenBase
{

    public BiomeGenTemperateRainforest(int i)
    {
        super(i);
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 5, 4, 4));
        biomeDecorator.treesPerChunk = 17;
        biomeDecorator.grassPerChunk = 16;
        biomeDecorator.coverPerChunk = 3;
        biomeDecorator.thickGrassPerChunk = 6;
        biomeDecorator.mushroomsPerChunk = 2;
        biomeDecorator.toadstoolsPerChunk = 2;
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random random)
	{
		if (random.nextInt(3) == 0)
		{
			return worldGenFirTree;
		}
		else
		{
			return worldGenFirTree2;
		}
	}
    
    public WorldGenerator func_48410_b(Random par1Random)
    {
        if (par1Random.nextInt(4) == 0)
        {
            return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
        }
        else
        {
            return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
        }
    }
}