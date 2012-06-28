package net.minecraft.src;

import java.util.Random;

public class BiomeGenRedwoodLush extends BiomeGenBase
{
	public BiomeGenRedwoodLush(int i)
	{
		super(i);
		biomeDecorator.treesPerChunk = 17;
		biomeDecorator.thickGrassPerChunk = 5;
		biomeDecorator.wheatGrassPerChunk = 8;
		biomeDecorator.rootPerChunk = 15;
		biomeDecorator.leafPilePerChunk = 30;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random random)
	{
		if (random.nextInt(2) == 0)
		{
			return worldGenRedwood;
		}
		else
		{
			return worldGenFirTree;
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