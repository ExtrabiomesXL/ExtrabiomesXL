/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.worldgen;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

@SuppressWarnings("deprecation")
public class QuicksandGenerator implements IWorldGenerator {

	private final WorldGenQuicksand	genPit;

	public QuicksandGenerator(int quicksandID) {
		genPit = new WorldGenQuicksand(quicksandID);
	}

    @Override
	public void generate(Random random, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX,
				chunkZ);
		if (!BiomeManager.minijungle.isPresent()
				|| biome != BiomeManager.minijungle.get()) return;

		// 1 to 3 attempts with with a bias toward 2
		final int attempts = random.nextInt(1) + random.nextInt(1) + 1;
		for (int i = 0; i < attempts; i++)
			if (random.nextInt(1) == 0) {
				final int x = randomizedCoord(random, chunkX);
				final int z = randomizedCoord(random, chunkZ);
				genPit.generate(world, random, x,
						world.getHeightValue(x, z) + 1, z);
			}
	}

	private int randomizedCoord(Random random, int coord) {
		return coord + random.nextInt(16) + 8;
	}

}
