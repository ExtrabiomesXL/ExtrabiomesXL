
package extrabiomes.plugin.terrainfeatures;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
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
	}
}
