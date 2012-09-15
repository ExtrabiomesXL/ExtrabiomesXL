
package extrabiomes.features;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDesertWells;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

public class FeatureGenerator implements IWorldGenerator {

	private static final WorldGenerator	oasisGen	= new WorldGenOasis();
	private static final WorldGenerator	genMarsh	= new WorldGenMarshGrass();
	private static final WorldGenerator	genDirtBed	= new WorldGenMarshDirt();
	private static final WorldGenerator	vineGen		= new WorldGenVines();

	@Override
	public void generate(Random random, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX,
				chunkX);

		if (biome == BiomeManager.extremejungle.get())
			for (int i = 0; i < 50; ++i) {
				final int x1 = chunkX + random.nextInt(16) + 8;
				final int z1 = chunkZ + random.nextInt(16) + 8;
				vineGen.generate(world, random, x1, 64, z1);
			}

		if (biome == BiomeManager.marsh.get()) {
			for (int i = 0; i < 127; i++) {
				final int x = chunkX + random.nextInt(16) + 8;
				final int z = chunkZ + random.nextInt(16) + 8;
				genMarsh.generate(world, random, x, 0, z);
			}

			for (int i = 0; i < 256; i++) {
				final int x = chunkX + random.nextInt(1) + 8;
				final int z = chunkZ + random.nextInt(1) + 8;
				genDirtBed.generate(world, random, x, 0, z);
			}
		}

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
