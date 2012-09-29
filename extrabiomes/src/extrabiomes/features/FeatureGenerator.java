
package extrabiomes.features;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDesertWells;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

public class FeatureGenerator implements IWorldGenerator {

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
			generateVines(random, chunkX, chunkZ, world);

		if (biome == BiomeManager.extremejungle.get()
				|| biome == BiomeManager.minijungle.get()
				|| biome == BiomeManager.temperaterainforest.get()
				|| biome == BiomeGenBase.jungle
				|| biome == BiomeGenBase.jungleHills)
			if (random.nextInt(48) == 0)
				generateMelonPatch(world, random, chunkX, chunkZ);

	}

	private void generateMelonPatch(World world, Random rand, int x,
			int z)
	{
		x += rand.nextInt(16) + 8;
		final int y = rand.nextInt(128);
		z += rand.nextInt(16) + 8;
		new WorldGenMelon().generate(world, rand, x, y, z);
	}

	private void generateVines(Random rand, int x, int z, World world) {
		for (int i = 0; i < 50; ++i) {
			final int x1 = x + rand.nextInt(16) + 8;
			final int z1 = z + rand.nextInt(16) + 8;
			vineGen.generate(world, rand, x1, 64, z1);
		}
	}
}
