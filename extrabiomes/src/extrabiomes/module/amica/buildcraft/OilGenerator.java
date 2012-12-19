
package extrabiomes.module.amica.buildcraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

@SuppressWarnings("deprecation")
public class OilGenerator implements IWorldGenerator {

	private final BuildcraftAPI	api;

	OilGenerator(BuildcraftAPI api) {
		this.api = api;
	}

    private void doPopulate(Random rand, World world, int x, int z) {
		final BiomeGenBase biome = world.getWorldChunkManager()
				.getBiomeGenAt(x, z);

		if ((BiomeManager.mountaindesert.isPresent()
				&& biome == BiomeManager.mountaindesert.get() || BiomeManager.wasteland
				.isPresent() && biome == BiomeManager.wasteland.get())
				&& rand.nextFloat() > 0.97)
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
							|| BiomeManager.wasteland.isPresent()
							&& (byte) id == BiomeManager.wasteland
									.get().topBlock)
						api.generateSurfaceDeposit(world, i, j, k, 3);

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
