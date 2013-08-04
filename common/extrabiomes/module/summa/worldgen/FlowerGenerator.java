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
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.DecorationSettings;

@SuppressWarnings("deprecation")
public class FlowerGenerator implements IWorldGenerator {

	private final WorldGenerator	autumnShrubGen;
	private final WorldGenerator	hydrangeaGen;
	private final WorldGenerator	buttercupGen;
	private final WorldGenerator	lavenderGen;
	private final WorldGenerator	rootGen;
	private final WorldGenerator	tinyCactusGen;
	private final WorldGenerator	toadStoolGen;
	private final WorldGenerator	callaGen;

	public FlowerGenerator(int flowerID) {
		autumnShrubGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.AUTUMN_SHRUB.metadata());
		hydrangeaGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.HYDRANGEA.metadata());
		buttercupGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.ORANGE.metadata());
		lavenderGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.PURPLE.metadata());
		rootGen = new WorldGenRoot(flowerID, BlockCustomFlower.BlockType.ROOT.metadata());
		tinyCactusGen = new WorldGenTinyCactus(flowerID, BlockCustomFlower.BlockType.TINY_CACTUS.metadata());
		toadStoolGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.TOADSTOOL.metadata());
		callaGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.WHITE.metadata());
	}

    @Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
		
		if (BiomeManager.birchforest.isPresent() && biome == BiomeManager.birchforest.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				hydrangeaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);
			}
		}
		
		if (BiomeManager.forestedhills.isPresent() && biome == BiomeManager.forestedhills.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				hydrangeaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				lavenderGen.generate(world, rand, x, y, z);
			}
		}
		
		if (BiomeManager.forestedhills.isPresent() && biome == BiomeManager.forestedhills.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				hydrangeaGen.generate(world, rand, x, y, z);
			}
		}
		
		if (BiomeManager.forestedisland.isPresent() && biome == BiomeManager.forestedisland.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				hydrangeaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);
			}
		}

		if (BiomeManager.autumnwoods.isPresent() && biome == BiomeManager.autumnwoods.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);
			}
		}

		if (BiomeManager.autumnwoods.isPresent() && biome == BiomeManager.autumnwoods.get() || BiomeManager.snowyrainforest.isPresent() && biome == BiomeManager.snowyrainforest.get() || BiomeManager.temperaterainforest.isPresent() && biome == BiomeManager.temperaterainforest.get() || BiomeManager.tundra.isPresent() && biome == BiomeManager.tundra.get()) {
			for (int i = 0; i < 2; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				toadStoolGen.generate(world, rand, x, y, z);
			}
		}

		if (BiomeManager.greenhills.isPresent() && biome == BiomeManager.greenhills.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				hydrangeaGen.generate(world, rand, x, y, z);
			}
		}

		if (BiomeManager.greenswamp.isPresent() && biome == BiomeManager.greenswamp.get()) {
			for (int i = 0; i < 2; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);
			}
			
			for (int i = 0; i < 5; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				rootGen.generate(world, rand, x, y, z);
			}
		}
		
		if (BiomeManager.minijungle.isPresent() && biome == BiomeManager.minijungle.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);
			}
		}

		if (BiomeManager.mountaindesert.isPresent() && biome == BiomeManager.mountaindesert.get()) {
			for (int i = 0; i < 70; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				tinyCactusGen.generate(world, rand, x, y, z);
			}
		}
		
		if (BiomeManager.mountainridge.isPresent() && biome == BiomeManager.mountainridge.get()) {
			for (int i = 0; i < 62; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				tinyCactusGen.generate(world, rand, x, y, z);
			}
		}
		
		if (BiomeManager.pineforest.isPresent() && biome == BiomeManager.pineforest.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				hydrangeaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);
			}
		}
		
		if (BiomeManager.rainforest.isPresent() && biome == BiomeManager.rainforest.get()) {
			for (int i = 0; i < 4; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);
			}
		}
		
		if (BiomeManager.redwoodforest.isPresent() && biome == BiomeManager.redwoodforest.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				hydrangeaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);
			}
		}

		if (BiomeManager.redwoodlush.isPresent() && biome == BiomeManager.redwoodlush.get()) { 
			for (int i = 0; i < 5; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				rootGen.generate(world, rand, x, y, z);
			}
			
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				hydrangeaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);
			}
		}

		if (BiomeManager.savanna.isPresent() && biome == BiomeManager.savanna.get()) {
			for (int i = 0; i < 6; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);
			}
			
			for (int i = 0; i < 8; i++) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				lavenderGen.generate(world, rand, x, y, z);
			}
		}

		if (BiomeManager.shrubland.isPresent() && biome == BiomeManager.shrubland.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				lavenderGen.generate(world, rand, x, y, z);
			}
		}
		
		if (BiomeManager.temperaterainforest.isPresent() && biome == BiomeManager.temperaterainforest.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				hydrangeaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);
			}
		}
		
		if (BiomeManager.woodlands.isPresent() && biome == BiomeManager.woodlands.get()) {
			for (int i = 0; i < 2; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int y = rand.nextInt(128);
				int z = chunkZ + rand.nextInt(16) + 8;
				buttercupGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				hydrangeaGen.generate(world, rand, x, y, z);

				x = chunkX + rand.nextInt(16) + 8;
				y = rand.nextInt(128);
				z = chunkZ + rand.nextInt(16) + 8;
				callaGen.generate(world, rand, x, y, z);
			}
		}
	}
}