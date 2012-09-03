/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.WorldGenerator;

import com.google.common.base.Optional;

import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenNoOp;


public class BiomeRedwoodForest extends ExtrabiomeGenBase {

	private Optional<WorldGenerator>	treeGen	= Optional.absent();

	public BiomeRedwoodForest(int id) {
		super(id);

		setColor(0x0BD626);
		setBiomeName("Redwood Forest");
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
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		if (!treeGen.isPresent())
			if (TerrainGenManager.enableRedwoodGen)
				treeGen = Optional.of(TerrainGenManager.treeFactory
						.get().makeTreeGenerator(false,
								ITreeFactory.TreeType.REDWOOD));
			else
				treeGen = Optional
						.of((WorldGenerator) new WorldGenNoOp());

		return treeGen.get();
	}

}
