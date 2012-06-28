package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenAlps extends BiomeGenBase {

	public BiomeGenAlps(int i) {
		super(i);
		topBlock = (byte) Block.stone.blockID;
		fillerBlock = (byte) Block.stone.blockID;
		maxHeight = 2.5F;
		biomeDecorator.treesPerChunk = 7;
		biomeDecorator.grassPerChunk = -999;
		biomeDecorator.shortGrassPerChunk = -999;
		biomeDecorator.coverPerChunk = -999;
		biomeDecorator.flowersPerChunk = -999;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		return worldGenAlps;
	}
}