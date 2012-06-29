
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.WorldGenerator;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeRedwoodForest extends ExtrabiomeGenBase {
	private WorldGenerator	treeGen;

	public BiomeRedwoodForest(int i) {
		super(i);
		treeGen = null;
		b(0xbd626);
		a("Redwood Forest");
		F = 1.1F;
		G = 1.4F;
		D = 0.9F;
		E = 1.5F;
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(17).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator a(Random random) {
		if (treeGen == null)
			if (TerrainGenManager.enableRedwoodGen)
				treeGen = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								false,
								extrabiomes.api.ITreeFactory.TreeType.REDWOOD);
			else
				treeGen = new WorldGenNoOp();

		return treeGen;
	}
}
