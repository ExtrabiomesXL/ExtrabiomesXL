/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

@SuppressWarnings("deprecation")
public class MountainDesertGenerator implements IWorldGenerator {

	private static final WorldGenerator	vineGen	= new WorldGenVines();

    @Override
	public void generate(Random random, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX,
				chunkX);

		if (BiomeManager.mountaindesert.isPresent() && biome == BiomeManager.mountaindesert.get())
			generateRareDesertWell(random, chunkX, chunkZ, world);
	}

	private void generateRareDesertWell(Random rand, int x, int z,
			World world)
	{
		if (rand.nextInt(1000) == 0) {
			final int x1 = x + rand.nextInt(16) + 8;
			final int z1 = z + rand.nextInt(16) + 8;
			final WorldGenDesertWells wells = new WorldGenDesertWells();
			wells.generate(world, rand, x1,
					world.getHeightValue(x1, z1) + 1, z1);
		}
	}
}
