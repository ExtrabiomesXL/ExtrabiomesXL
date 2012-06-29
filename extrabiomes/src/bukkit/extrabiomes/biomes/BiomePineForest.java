
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;
import net.minecraft.server.WorldGenTaiga1;
import net.minecraft.server.WorldGenTaiga2;
import net.minecraft.server.WorldGenerator;

public class BiomePineForest extends ExtrabiomeGenBase {
	public BiomePineForest(int i) {
		super(i);
		b(0x469c7e);
		a("Pine Forest");
		F = BiomeBase.FOREST.F;
		G = BiomeBase.FOREST.G;
		D = 0.1F;
		E = 0.3F;
		K.add(new BiomeMeta(net.minecraft.server.EntityWolf.class, 5, 4, 4));
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(10).grassPerChunk(1).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator a(Random random) {
		return (WorldGenerator) (random.nextInt(3) != 0 ? new WorldGenTaiga2(
				false) : new WorldGenTaiga1());
	}
}
