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
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

import com.google.common.base.Optional;

import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenNoOp;


public class BiomeTemporateRainforest extends ExtrabiomeGenBase {

	protected Optional<WorldGenerator>	treeGen		= Optional.absent();
	protected Optional<WorldGenerator>	treeGen2	= Optional.absent();

	public BiomeTemporateRainforest(int id) {
		super(id);

		setColor(0x338235);
		setBiomeName("Temperate Rainforest");
		temperature = 0.6F;
		rainfall = 0.9F;
		minHeight = 0.4F;
		maxHeight = 1.5F;

		spawnableCreatureList.add(new SpawnListEntry(
				net.minecraft.src.EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17)
				.mushroomsPerChunk(2).grassPerChunk(16).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		if (checkNotNull(rand).nextInt(4) == 0)
			return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
		return super.getRandomWorldGenForGrass(rand);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		if (checkNotNull(rand).nextInt(3) == 0) {
			if (!treeGen.isPresent())
				if (TerrainGenManager.enableFirGen)
					treeGen = Optional.of(TerrainGenManager.treeFactory
							.get().makeTreeGenerator(false,
									ITreeFactory.TreeType.FIR));
				else
					treeGen = Optional
							.of((WorldGenerator) new WorldGenNoOp());

			return treeGen.get();
		}
		if (!treeGen2.isPresent())
			if (TerrainGenManager.enableFirGen)
				treeGen2 = Optional.of(TerrainGenManager.treeFactory
						.get().makeTreeGenerator(false,
								ITreeFactory.TreeType.FIR_HUGE));
			else
				treeGen2 = Optional
						.of((WorldGenerator) new WorldGenNoOp());

		return treeGen2.get();
	}

}
