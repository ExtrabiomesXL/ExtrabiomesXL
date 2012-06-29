
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.WorldGenerator;
import extrabiomes.terrain.WorldGenCustomSwamp;

public class BiomeGreenSwamp extends ExtrabiomeGenBase {
	private static WorldGenerator	genCustomSwampTree	= null;

	public BiomeGreenSwamp(int i) {
		super(i);
		b(0x68c474);
		a("Green Swamplands");
		F = BiomeBase.SWAMPLAND.F - 0.1F;
		G = BiomeBase.SWAMPLAND.G;
		D = -0.2F;
		E = 0.1F;
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(4).flowersPerChunk(0)
				.deadBushPerChunk(0).mushroomsPerChunk(8)
				.reedsPerChunk(10).clayPerChunk(1).waterlilyPerChunk(4)
				.sandPerChunk(0, 0).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator a(Random random) {
		if (random.nextInt(5) == 0) return Q;

		if (genCustomSwampTree == null)
			genCustomSwampTree = new WorldGenCustomSwamp();

		return genCustomSwampTree;
	}
}
