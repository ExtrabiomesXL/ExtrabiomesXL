/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.quicksand;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

public class WorldGenerator implements IWorldGenerator {

	private final WorldGenPit	genPit;

	WorldGenerator(int quickSandID) {
		genPit = new WorldGenPit(quickSandID);
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
		if (biome != BiomeManager.minijungle.get()) return;

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
