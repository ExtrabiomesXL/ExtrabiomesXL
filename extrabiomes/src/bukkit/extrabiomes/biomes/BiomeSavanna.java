
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.WorldGenerator;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeSavanna extends ExtrabiomeGenBase {
	private WorldGenerator	treeGen;

	public BiomeSavanna(int i) {
		super(i);
		treeGen = null;
		b(0xbfa243);
		a("Savanna");
		F = 2.0F;
		G = 0.0F;
		D = 0.0F;
		E = 0.1F;
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(0).flowersPerChunk(1)
				.grassPerChunk(17).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator a(Random random) {
		if (treeGen == null)
			if (TerrainGenManager.enableAcaciaGen)
				treeGen = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								false,
								extrabiomes.api.ITreeFactory.TreeType.ACACIA);
			else
				treeGen = new WorldGenNoOp();

		return treeGen;
	}
}
