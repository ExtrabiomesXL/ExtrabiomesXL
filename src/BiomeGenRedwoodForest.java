package net.minecraft.src;

import java.util.Random;

public class BiomeGenRedwoodForest extends BiomeGenBase
{
	public BiomeGenRedwoodForest(int i)
	{
		super(i);
		biomeDecorator.treesPerChunk = 17;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random random)
	{
		return worldGenRedwood;
	}
}