
package extrabiomes.module.amica.buildcraft;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

public class OilGenerator implements IWorldGenerator {

	public OilGenerator(int oilStillID) {}

	private void doPopulate(Random rand, World world, int x, int z) {
		final BiomeGenBase biome = world.getWorldChunkManager()
				.getBiomeGenAt(x, z);

		if (biome.biomeID == BiomeGenBase.sky.biomeID
				|| biome.biomeID == BiomeGenBase.hell.biomeID) return;

		if ((biome == BiomeManager.mountaindesert.get() || biome == BiomeManager.wasteland
				.get()) && rand.nextFloat() > 0.97)
		{
			// Generate a small deposit

			final int startX = rand.nextInt(10) + 2;
			final int startZ = rand.nextInt(10) + 2;

			for (int j = 128; j > 65; --j) {
				final int i = startX + x;
				final int k = startZ + z;

				final int id = world.getBlockId(i, j, k);
				if (id != 0) {
					if (id == Block.sand.blockID
							|| (byte)id == BiomeManager.wasteland.get().topBlock)
						BuildcraftPlugin.generateSurfaceDeposit(world,
								i, j, k, 3);

					break;
				}
			}
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;

		doPopulate(random, world, chunkX, chunkZ);
	}

}
