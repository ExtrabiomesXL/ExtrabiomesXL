/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

import com.google.common.base.Optional;

import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenNoOp;


public class BiomeRedwoodLush extends ExtrabiomeGenBase {

	private Optional<WorldGenerator>	worldGenRedwood	= Optional
																.absent();
	private Optional<WorldGenerator>	worldGenFirTree	= Optional
																.absent();

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
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		if (checkNotNull(rand).nextInt(4) == 0)
			return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
		return super.getRandomWorldGenForGrass(rand);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		if (checkNotNull(rand).nextInt(2) == 0) {
			if (!worldGenRedwood.isPresent())
				if (TerrainGenManager.enableRedwoodGen)
					worldGenRedwood = Optional
							.of(TerrainGenManager.treeFactory
									.get()
									.makeTreeGenerator(
											false,
											ITreeFactory.TreeType.REDWOOD));
				else
					worldGenRedwood = Optional
							.of((WorldGenerator) new WorldGenNoOp());
			return worldGenRedwood.get();
		}

		if (!worldGenFirTree.isPresent())
			if (TerrainGenManager.enableFirGen)
				worldGenFirTree = Optional
						.of(TerrainGenManager.treeFactory.get()
								.makeTreeGenerator(false,
										ITreeFactory.TreeType.FIR));
			else
				worldGenFirTree = Optional
						.of((WorldGenerator) new WorldGenNoOp());
		return worldGenFirTree.get();
	}

}
