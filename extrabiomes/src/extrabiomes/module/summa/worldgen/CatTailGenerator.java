/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

public class CatTailGenerator implements IWorldGenerator {

	private final WorldGenerator	catTailGen;

	public CatTailGenerator(int blockID) {
		catTailGen = new WorldGenCatTail(blockID);
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

		if (biome == BiomeManager.greenswamp.get())
			for (int i = 0; i < 20; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int z = chunkZ + rand.nextInt(16) + 8;
				final int y = world.getHeightValue(x, z);
				catTailGen.generate(world, rand, x, y, z);
			}
	}
}
