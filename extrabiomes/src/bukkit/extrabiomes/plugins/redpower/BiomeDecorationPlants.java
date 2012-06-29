
package extrabiomes.plugins.redpower;

import java.util.Random;

import net.minecraft.server.World;
import net.minecraft.server.WorldGenFlowers;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.plugins.PluginRedPower;

public class BiomeDecorationPlants implements IBiomeDecoration {
	private final int		iterations;
	private static boolean	generatePlants	= true;

	public BiomeDecorationPlants() {
		this(1);
	}

	public BiomeDecorationPlants(int i) {
		iterations = i;
	}

	public void decorate(World world, Random random, int i, int j) {
		for (int k = 0; k < iterations; k++) {
			final int l = i + random.nextInt(16) + 8;
			final int i1 = random.nextInt(128);
			final int j1 = j + random.nextInt(16) + 8;
			new WorldGenFlowers(PluginRedPower.idPlants).a(world,
					random, l, i1, j1);
		}
	}
}
