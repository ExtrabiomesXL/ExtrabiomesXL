
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.WorldGenGroundBush;
import net.minecraft.server.WorldGenerator;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeShrubland extends ExtrabiomeGenBase {
	public BiomeShrubland(int i) {
		super(i);
		b(0x51b57d);
		a("Shrubland");
		F = 0.4F;
		G = 0.6F;
		D = 0.1F;
		E = 0.3F;
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(0).flowersPerChunk(3)
				.grassPerChunk(1).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator a(Random random) {
		return (WorldGenerator) (random.nextInt(3) > 1 ? new WorldGenNoOp()
				: new WorldGenGroundBush(3, random.nextInt(3)));
	}
}
