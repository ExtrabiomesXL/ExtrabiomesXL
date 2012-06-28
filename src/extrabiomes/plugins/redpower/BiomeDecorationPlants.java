package extrabiomes.plugins.redpower;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenFlowers;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.plugins.PluginRedPower;

public class BiomeDecorationPlants implements IBiomeDecoration {

	private final int iterations;
	private static boolean generatePlants = true;

	public BiomeDecorationPlants() {
		this(1);
	}

	public BiomeDecorationPlants(int iterations) {
		this.iterations = iterations;
	}

	@Override
	public void decorate(World world, Random rand, int chunkX, int chunkZ) {
		if (!generatePlants)
			return;
		if (PluginRedPower.idPlants == 0) {
			PluginRedPower.initBlockId();
			if (PluginRedPower.idPlants == 0) {
				generatePlants = false;
				return;
			}
		}

		for (int a = 0; a < iterations; a++) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(128);
			int z = chunkZ + rand.nextInt(16) + 8;
			(new WorldGenFlowers(PluginRedPower.idPlants)).generate(world,
					rand, x, y, z);
		}
	}

}
