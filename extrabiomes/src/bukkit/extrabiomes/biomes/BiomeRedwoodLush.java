
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.WorldGenGrass;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeRedwoodLush extends ExtrabiomeGenBase {
	private WorldGenerator	worldGenRedwood;
	private WorldGenerator	worldGenFirTree;

	public BiomeRedwoodLush(int i) {
		super(i);
		worldGenRedwood = null;
		worldGenFirTree = null;
		b(0x4ac758);
		a("Lush Redwoods");
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
		if (random.nextInt(2) == 0) {
			if (worldGenRedwood == null)
				if (TerrainGenManager.enableRedwoodGen)
					worldGenRedwood = TerrainGenManager.treeFactory
							.makeTreeGenerator(
									false,
									extrabiomes.api.ITreeFactory.TreeType.REDWOOD);
				else
					worldGenRedwood = new WorldGenNoOp();

			return worldGenRedwood;
		}

		if (worldGenFirTree == null)
			if (TerrainGenManager.enableFirGen)
				worldGenFirTree = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								false,
								extrabiomes.api.ITreeFactory.TreeType.FIR);
			else
				worldGenFirTree = new WorldGenNoOp();

		return worldGenFirTree;
	}

	public WorldGenerator b(Random random) {
		if (random.nextInt(4) == 0)
			return new WorldGenGrass(Block.LONG_GRASS.id, 2);
		else
			return super.b(random);
	}
}
