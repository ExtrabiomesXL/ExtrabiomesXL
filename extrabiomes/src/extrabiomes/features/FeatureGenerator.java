
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
			generateVines(random, chunkX, chunkZ, world);

		if (biome == BiomeManager.marsh.get())
			generateMarsh(random, chunkX, chunkZ, world);

		if (biome == BiomeManager.mountainridge.get()) {
			trimPondsInGrass(random, chunkX, chunkZ, world);
			generateEmeraldOre(random, chunkX, chunkZ, world);
		}

		if (biome == BiomeManager.mountaindesert.get())
			generateRareDesrtWell(random, chunkX, chunkZ, world);

	}

	private void generateEmeraldOre(Random rand, int x, int z,
			World world)
	{
		final int veins = 3 + rand.nextInt(6);

		for (int i = 0; i < veins; ++i) {
			final int x1 = x + rand.nextInt(16);
			final int y1 = rand.nextInt(28) + 4;
			final int z1 = z + rand.nextInt(16);
			final int id = world.getBlockId(x1, y1, z1);

			if (id != 0
					&& Block.blocksList[id].isGenMineableReplaceable(
							world, x1, y1, z1))
				world.setBlock(x1, y1, z1, Block.oreEmerald.blockID);
		}
	}

	private void generateMarsh(Random rand, int x, int z, World world) {
		for (int i = 0; i < 127; i++) {
			final int x1 = x + rand.nextInt(16) + 8;
			final int z1 = z + rand.nextInt(16) + 8;
			genMarsh.generate(world, rand, x1, 0, z1);
		}

		for (int i = 0; i < 256; i++) {
			final int x1 = x + rand.nextInt(1) + 8;
			final int z1 = z + rand.nextInt(1) + 8;
			genDirtBed.generate(world, rand, x1, 0, z1);
		}
	}

	private void generateRareDesrtWell(Random rand, int x, int z,
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

	private void generateVines(Random rand, int x, int z, World world) {
		for (int i = 0; i < 50; ++i) {
			final int x1 = x + rand.nextInt(16) + 8;
			final int z1 = z + rand.nextInt(16) + 8;
			vineGen.generate(world, rand, x1, 64, z1);
		}
	}

	private void trimPondsInGrass(Random rand, int x, int z, World world)
	{
		for (int i = 0; i < 1000; i++) {
			final int x1 = x + rand.nextInt(16) + 8;
			final int z1 = z + rand.nextInt(16) + 8;
			final int y1 = world.getTopSolidOrLiquidBlock(x1, z1);

			oasisGen.generate(world, rand, x1, y1, z1);
		}
	}
}
