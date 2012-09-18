/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.flora;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

public class LeafPileGenerator implements IWorldGenerator {

	private final WorldGenerator	leafPileGen;

	LeafPileGenerator(int blockID) {
		leafPileGen = new WorldGenLeafPile(blockID);
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX,
				chunkX);

		if (biome == BiomeManager.greenswamp.get()
				|| biome == BiomeManager.mountainridge.get()
				|| biome == BiomeManager.redwoodlush.get()
				|| biome == BiomeManager.woodlands.get())
			for (int i = 0; i < 2; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				leafPileGen.generate(world, rand, x, y, z);
			}
	}

}
