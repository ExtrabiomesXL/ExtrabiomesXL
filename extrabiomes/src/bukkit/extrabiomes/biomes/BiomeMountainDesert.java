
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.Block;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenDesertWell;

public class BiomeMountainDesert extends ExtrabiomeGenBase {
	public BiomeMountainDesert(int i) {
		super(i);
		b(0xfa9418);
		a("Mountainous Desert");
		F = BiomeBase.DESERT_HILLS.F;
		G = BiomeBase.DESERT_HILLS.G;
		D = 0.4F;
		E = 1.4F;
		A = (byte) Block.SAND.id;
		B = (byte) Block.SAND.id;
		K.clear();
		disableRain();
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(0).deadBushPerChunk(2)
				.reedsPerChunk(50).cactiPerChunk(10).build();
	}

	public void a(World world, Random random, int i, int j) {
		super.a(world, random, i, j);

		if (random.nextInt(1000) == 0) {
			final int k = i + random.nextInt(16) + 8;
			final int l = j + random.nextInt(16) + 8;
			final WorldGenDesertWell worldgendesertwell = new WorldGenDesertWell();
			worldgendesertwell.a(world, random, k,
					world.getHighestBlockYAt(k, l) + 1, l);
		}
	}
}
