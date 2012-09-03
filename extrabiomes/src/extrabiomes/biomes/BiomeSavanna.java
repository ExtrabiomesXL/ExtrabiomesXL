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


public class BiomeSavanna extends ExtrabiomeGenBase {

	private Optional<WorldGenerator>	treeGen	= Optional.absent();

	public BiomeSavanna(int id) {
		super(id);

		setColor(0xBFA243);
		setBiomeName("Savanna");
		temperature = 2.0F;
		rainfall = 0.0F;
		minHeight = 0.0F;
		maxHeight = 0.1F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.flowersPerChunk(1).grassPerChunk(17).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		if (!treeGen.isPresent())
			if (TerrainGenManager.enableAcaciaGen)
				treeGen = Optional.of(TerrainGenManager.treeFactory
						.get().makeTreeGenerator(false,
								ITreeFactory.TreeType.ACACIA));
			else
				treeGen = Optional
						.of((WorldGenerator) new WorldGenNoOp());

		return treeGen.get();
	}

}
