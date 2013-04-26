/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.google.common.base.Optional;

import extrabiomes.api.BiomeManager;

abstract class ExtrabiomeGenBase extends BiomeGenBase {

	protected ExtrabiomeGenBase(int id) {
		super(id);
	}

    @Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		final Optional<? extends WorldGenerator> grassGen = BiomeManager
				.chooseRandomGrassGenforBiome(rand, this);
		if (grassGen.isPresent()) return grassGen.get();
		return super.getRandomWorldGenForGrass(rand);
	}

    @Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		final Optional<? extends WorldGenerator> treeGen = BiomeManager
				.chooseRandomTreeGenforBiome(rand, this);
		if (treeGen.isPresent()) return treeGen.get();
		return super.getRandomWorldGenForTrees(rand);
	}
}
