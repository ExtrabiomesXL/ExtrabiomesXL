
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;
import net.minecraft.server.WorldGenBigTree;
import net.minecraft.server.WorldGenTrees;
import net.minecraft.server.WorldGenerator;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeAutumnWoods extends ExtrabiomeGenBase {
	private static boolean shouldChooseColor(Random random) {
		return random.nextInt(3) == 0;
	}

	private static boolean shouldGrowBigTree(Random random) {
		return random.nextInt(10) == 0;
	}

	public BiomeAutumnWoods(int i) {
		super(i);
		b(0xf29c11);
		a("Autumn Woods");
		F = BiomeBase.FOREST.F;
		G = BiomeBase.FOREST.G;
		D = 0.2F;
		E = 0.8F;
		K.add(new BiomeMeta(net.minecraft.server.EntityWolf.class, 5, 4, 4));
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(9).grassPerChunk(6)
				.mushroomsPerChunk(3).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator a(Random random) {
		if (!TerrainGenManager.enableAutumnTreeGen)
			return new WorldGenNoOp();

		extrabiomes.api.ITreeFactory.TreeType treetype = null;
		final boolean flag = shouldGrowBigTree(random);

		if (shouldChooseColor(random))
			treetype = flag ? extrabiomes.api.ITreeFactory.TreeType.BROWN_AUTUMN_BIG
					: extrabiomes.api.ITreeFactory.TreeType.BROWN_AUTUMN;
		else if (shouldChooseColor(random))
			treetype = flag ? extrabiomes.api.ITreeFactory.TreeType.ORANGE_AUTUMN_BIG
					: extrabiomes.api.ITreeFactory.TreeType.ORANGE_AUTUMN;
		else if (shouldChooseColor(random))
			treetype = flag ? extrabiomes.api.ITreeFactory.TreeType.PURPLE_AUTUMN_BIG
					: extrabiomes.api.ITreeFactory.TreeType.PURPLE_AUTUMN;
		else if (shouldChooseColor(random))
			treetype = flag ? extrabiomes.api.ITreeFactory.TreeType.YELLOW_AUTUMN_BIG
					: extrabiomes.api.ITreeFactory.TreeType.YELLOW_AUTUMN;

		if (treetype != null)
			return TerrainGenManager.treeFactory.makeTreeGenerator(
					false, treetype);
		else
			return (WorldGenerator) (flag ? new WorldGenBigTree(false)
					: new WorldGenTrees(false));
	}
}
