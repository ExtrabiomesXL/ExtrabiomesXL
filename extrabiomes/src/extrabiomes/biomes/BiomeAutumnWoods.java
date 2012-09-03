/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

import com.google.common.base.Optional;

import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenNoOp;


public class BiomeAutumnWoods extends ExtrabiomeGenBase {

	static private boolean shouldChooseColor(final Random rand) {
		return rand.nextInt(3) == 0;
	}

	static private boolean shouldGrowBigTree(final Random rand) {
		return rand.nextInt(10) == 0;
	}

	public BiomeAutumnWoods(int id) {
		super(id);
		setColor(0xF29C11);
		setBiomeName("Autumn Woods");
		temperature = BiomeGenBase.forest.temperature;
		rainfall = BiomeGenBase.forest.rainfall;
		minHeight = 0.2F;
		maxHeight = 0.8F;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class,
				5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(9)
				.grassPerChunk(6).mushroomsPerChunk(3).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(final Random rand) {

		if (!TerrainGenManager.enableAutumnTreeGen)
			return new WorldGenNoOp();

		Optional<ITreeFactory.TreeType> tree = Optional.absent();
		final boolean bigTree = shouldGrowBigTree(checkNotNull(rand));

		if (shouldChooseColor(rand))
			tree = Optional
					.of(bigTree ? ITreeFactory.TreeType.BROWN_AUTUMN_BIG
							: ITreeFactory.TreeType.BROWN_AUTUMN);
		else if (shouldChooseColor(rand))
			tree = Optional
					.of(bigTree ? ITreeFactory.TreeType.ORANGE_AUTUMN_BIG
							: ITreeFactory.TreeType.ORANGE_AUTUMN);
		else if (shouldChooseColor(rand))
			tree = Optional
					.of(bigTree ? ITreeFactory.TreeType.PURPLE_AUTUMN_BIG
							: ITreeFactory.TreeType.PURPLE_AUTUMN);
		else if (shouldChooseColor(rand))
			tree = Optional
					.of(bigTree ? ITreeFactory.TreeType.YELLOW_AUTUMN_BIG
							: ITreeFactory.TreeType.YELLOW_AUTUMN);

		if (tree.isPresent())
			return TerrainGenManager.treeFactory.get()
					.makeTreeGenerator(false, tree.get());

		return bigTree ? new WorldGenBigTree(false)
				: new WorldGenTrees(false);
	}

}
