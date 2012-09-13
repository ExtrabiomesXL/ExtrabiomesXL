
package extrabiomes.features;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDesertWells;
import net.minecraft.src.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

public class FeatureGenerator implements IWorldGenerator {

	private static final WorldGenerator	oasisGen	= new WorldGenOasis();

	@Override
	public void generate(Random random, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX,
				chunkX);

		if (biome == BiomeManager.mountainridge.get())
			for (int i = 0; i < 1000; i++) {
				final int x = chunkX + random.nextInt(16) + 8;
				final int z = chunkZ + random.nextInt(16) + 8;
				final int y = world.getTopSolidOrLiquidBlock(x, z);

				oasisGen.generate(world, random, x, y, z);
			}

		if (biome == BiomeManager.mountaindesert.get())
			if (random.nextInt(1000) == 0) {
				final int x = chunkX + random.nextInt(16) + 8;
				final int z = chunkZ + random.nextInt(16) + 8;
				final WorldGenDesertWells wells = new WorldGenDesertWells();
				wells.generate(world, random, x,
						world.getHeightValue(x, z) + 1, z);
			}

	}
}
