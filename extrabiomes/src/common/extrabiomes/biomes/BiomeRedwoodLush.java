/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeRedwoodLush extends ExtrabiomeGenBase {

	private WorldGenerator	worldGenRedwood	= null;
	private WorldGenerator	worldGenFirTree	= null;

	public BiomeRedwoodLush(int id) {
		super(id);
		setColor(0x4AC758);
		setBiomeName("Lush Redwoods");
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.9F;
		maxHeight = 1.5F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17)
				.build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
		if (par1Random.nextInt(4) == 0)
			return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
		return super.getRandomWorldGenForGrass(par1Random);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		if (random.nextInt(2) == 0) {
			if (worldGenRedwood == null)
				if (TerrainGenManager.enableRedwoodGen)
					worldGenRedwood = TerrainGenManager.treeFactory
							.makeTreeGenerator(false,
									ITreeFactory.TreeType.REDWOOD);
				else
					worldGenRedwood = new WorldGenNoOp();
			return worldGenRedwood;
		}

		if (worldGenFirTree == null)
			if (TerrainGenManager.enableFirGen)
				worldGenFirTree = TerrainGenManager.treeFactory
						.makeTreeGenerator(false,
								ITreeFactory.TreeType.FIR);
			else
				worldGenFirTree = new WorldGenNoOp();
		return worldGenFirTree;

	}

}
