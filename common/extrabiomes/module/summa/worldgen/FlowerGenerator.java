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
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;
import extrabiomes.blocks.BlockCustomFlower;

@SuppressWarnings("deprecation")
public class FlowerGenerator implements IWorldGenerator {

	private final WorldGenerator	autumnShrubGen;
	private final WorldGenerator	hydrangeaGen;
	private final WorldGenerator	orangeGen;
	private final WorldGenerator	purpleGen;
	private final WorldGenerator	rootGen;
	private final WorldGenerator	tinyCactusGen;
	private final WorldGenerator	toadStoolGen;
	private final WorldGenerator	whiteGen;

	public FlowerGenerator(int flowerID) {
		autumnShrubGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.AUTUMN_SHRUB.metadata());
		hydrangeaGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.HYDRANGEA.metadata());
		orangeGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.ORANGE.metadata());
		purpleGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.PURPLE.metadata());
		rootGen = new WorldGenRoot(flowerID, BlockCustomFlower.BlockType.ROOT.metadata());
		tinyCactusGen = new WorldGenTinyCactus(flowerID, BlockCustomFlower.BlockType.TINY_CACTUS.metadata());
		toadStoolGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.TOADSTOOL.metadata());
		whiteGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.WHITE.metadata());
	}

    @Override
	public void generate(Random rand, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX,
				chunkZ);

		if (BiomeManager.autumnwoods.isPresent()
				&& biome == BiomeManager.autumnwoods.get())
			for (int i = 0; i < 2; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				autumnShrubGen.generate(world, rand, x, y, z);
			}

		if (BiomeManager.autumnwoods.isPresent()
				&& biome == BiomeManager.autumnwoods.get()
				|| BiomeManager.snowyrainforest.isPresent()
				&& biome == BiomeManager.snowyrainforest.get()
				|| BiomeManager.temperaterainforest.isPresent()
				&& biome == BiomeManager.temperaterainforest.get()
				|| BiomeManager.tundra.isPresent()
				&& biome == BiomeManager.tundra.get())
			for (int i = 0; i < 2; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				toadStoolGen.generate(world, rand, x, y, z);
			}

		if (BiomeManager.greenhills.isPresent()
				&& biome == BiomeManager.greenhills.get())
			for (int i = 0; i < 3; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				orangeGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				whiteGen.generate(world, rand, x, y, z);
			}

		if (BiomeManager.greenswamp.isPresent()
				&& biome == BiomeManager.greenswamp.get())
		{
			final int x = chunkX + rand.nextInt(16) + 8;
			final int y = rand.nextInt(128);
			final int z = chunkZ + rand.nextInt(16) + 8;
			hydrangeaGen.generate(world, rand, x, y, z);
		}

		if (BiomeManager.greenswamp.isPresent()
				&& biome == BiomeManager.greenswamp.get()
				|| BiomeManager.redwoodlush.isPresent()
				&& biome == BiomeManager.redwoodlush.get())
			for (int i = 0; i < 5; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				rootGen.generate(world, rand, x, y, z);
			}

		if (BiomeManager.mountainridge.isPresent()
				&& biome == BiomeManager.mountainridge.get())
		{
			for (int i = 0; i < 50; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				tinyCactusGen.generate(world, rand, x, y, z);
			}

			for (int i = 0; i < 12; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				tinyCactusGen.generate(world, rand, x, y, z);
			}
		}

		if (BiomeManager.savanna.isPresent()
				&& biome == BiomeManager.savanna.get())
			for (int i = 0; i < 15; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				purpleGen.generate(world, rand, x, y, z);
			}
	}

}
