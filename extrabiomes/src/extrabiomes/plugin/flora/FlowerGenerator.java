/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.flora;

import static extrabiomes.plugin.flora.FlowerType.AUTUMN_SHRUB;
import static extrabiomes.plugin.flora.FlowerType.HYDRANGEA;
import static extrabiomes.plugin.flora.FlowerType.ORANGE;
import static extrabiomes.plugin.flora.FlowerType.PURPLE;
import static extrabiomes.plugin.flora.FlowerType.ROOT;
import static extrabiomes.plugin.flora.FlowerType.TINY_CACTUS;
import static extrabiomes.plugin.flora.FlowerType.TOADSTOOL;
import static extrabiomes.plugin.flora.FlowerType.WHITE;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.api.BiomeManager;

public class FlowerGenerator implements IWorldGenerator {

	private final WorldGenerator	autumnShrubGen;
	private final WorldGenerator	hydrangeaGen;
	private final WorldGenerator	orangeGen;
	private final WorldGenerator	purpleGen;
	private final WorldGenerator	rootGen;
	private final WorldGenerator	tinyCactusGen;
	private final WorldGenerator	toadStoolGen;
	private final WorldGenerator	whiteGen;

	FlowerGenerator(int flowerID) {
		autumnShrubGen = new WorldGenMetadataFlowers(flowerID,
				AUTUMN_SHRUB.metadata());
		hydrangeaGen = new WorldGenMetadataFlowers(flowerID,
				HYDRANGEA.metadata());
		orangeGen = new WorldGenMetadataFlowers(flowerID,
				ORANGE.metadata());
		purpleGen = new WorldGenMetadataFlowers(flowerID,
				PURPLE.metadata());
		rootGen = new WorldGenRoot(flowerID, ROOT.metadata());
		tinyCactusGen = new WorldGenTinyCactus(flowerID,
				TINY_CACTUS.metadata());
		toadStoolGen = new WorldGenMetadataFlowers(flowerID,
				TOADSTOOL.metadata());
		whiteGen = new WorldGenMetadataFlowers(flowerID,
				WHITE.metadata());
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ,
			World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX,
				chunkX);

		if (biome == BiomeManager.autumnwoods.get())
			for (int i = 0; i < 2; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				autumnShrubGen.generate(world, rand, x, y, z);
			}

		if (biome == BiomeManager.autumnwoods.get()
				|| biome == BiomeManager.snowyrainforest.get()
				|| biome == BiomeManager.temperaterainforest.get()
				|| biome == BiomeManager.tundra.get())
			for (int i = 0; i < 2; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				toadStoolGen.generate(world, rand, x, y, z);
			}

		if (biome == BiomeManager.greenhills.get()) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(128);
			int z = chunkZ + rand.nextInt(16) + 8;
			orangeGen.generate(world, rand, x, y, z);

			x = chunkX + rand.nextInt(16) + 8;
			y = rand.nextInt(128);
			z = chunkZ + rand.nextInt(16) + 8;
			whiteGen.generate(world, rand, x, y, z);
		}

		if (biome == BiomeManager.greenswamp.get()) {
			final int x = chunkX + rand.nextInt(16) + 8;
			final int y = rand.nextInt(128);
			final int z = chunkZ + rand.nextInt(16) + 8;
			hydrangeaGen.generate(world, rand, x, y, z);
		}

		if (biome == BiomeManager.greenswamp.get()
				|| biome == BiomeManager.redwoodlush.get())
			for (int i = 0; i < 5; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				rootGen.generate(world, rand, x, y, z);
			}

		if (biome == BiomeManager.mountainridge.get())
			for (int i = 0; i < 10; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				tinyCactusGen.generate(world, rand, x, y, z);
			}

		if (biome == BiomeManager.savanna.get()) {
			final int x = chunkX + rand.nextInt(16) + 8;
			final int y = rand.nextInt(128);
			final int z = chunkZ + rand.nextInt(16) + 8;
			purpleGen.generate(world, rand, x, y, z);
		}
	}

}
