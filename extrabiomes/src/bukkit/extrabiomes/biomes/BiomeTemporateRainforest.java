
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;
import net.minecraft.server.WorldGenGrass;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeTemporateRainforest extends ExtrabiomeGenBase {
	protected WorldGenerator	treeGen;
	protected WorldGenerator	treeGen2;

	public BiomeTemporateRainforest(int i) {
		super(i);
		treeGen = null;
		treeGen2 = null;
		b(0x338235);
		a("Temperate Rainforest");
		F = 0.6F;
		G = 0.9F;
		D = 0.4F;
		E = 1.5F;
		K.add(new BiomeMeta(net.minecraft.server.EntityWolf.class, 5, 4, 4));
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(17).mushroomsPerChunk(2)
				.grassPerChunk(16).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator a(Random random) {
		if (random.nextInt(3) == 0) {
			if (treeGen == null)
				if (TerrainGenManager.enableFirGen)
					treeGen = TerrainGenManager.treeFactory
							.makeTreeGenerator(
									false,
									extrabiomes.api.ITreeFactory.TreeType.FIR);
				else
					treeGen = new WorldGenNoOp();

			return treeGen;
		}

		if (treeGen2 == null)
			if (TerrainGenManager.enableFirGen)
				treeGen2 = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								false,
								extrabiomes.api.ITreeFactory.TreeType.FIR_HUGE);
			else
				treeGen2 = new WorldGenNoOp();

		return treeGen2;
	}

	public WorldGenerator b(Random random) {
		if (random.nextInt(4) == 0)
			return new WorldGenGrass(Block.LONG_GRASS.id, 2);
		else
			return super.b(random);
	}
}
