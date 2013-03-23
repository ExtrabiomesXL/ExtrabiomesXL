/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

@SuppressWarnings("deprecation")
public class MountainRidgeGenerator implements IWorldGenerator {

	private static final WorldGenerator	oasisGen	= new WorldGenOasis();
	private static final WorldGenerator silverfishGen = new WorldGenMinable(Block.silverfish.blockID, 8); 

	@Override
	public void generate(Random random, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX,
				chunkX);

		if (BiomeManager.mountainridge.isPresent()
				&& biome == BiomeManager.mountainridge.get())
		{
			trimPondsInGrass(random, chunkX, chunkZ, world);
			generateEmeraldOre(random, chunkX, chunkZ, world);
		}
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
							world, x1, y1, z1, Block.stone.blockID))
				world.setBlock(x1, y1, z1, Block.oreEmerald.blockID);
		}
		
		for (int i = 0; i< 7; i++) {
			final int x1 = x + rand.nextInt(16);
			final int y1 = rand.nextInt(64);
			final int z1 = z + rand.nextInt(16);
			silverfishGen.generate(world, rand, x1, y1, z1);
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
