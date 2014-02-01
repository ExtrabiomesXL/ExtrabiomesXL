/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomFlower.BlockType;
import extrabiomes.lib.BiomeSettings;

public class FlowerGenerator implements IWorldGenerator
{
	private final Map<BlockType, WorldGenerator>	flowerGens;
    
    public FlowerGenerator()
    {
    	flowerGens = Maps.newHashMap();
    }
    
	public void registerBlock(int blockID, Collection<BlockType> types) {
		for( BlockCustomFlower.BlockType type : types ) {
			// special cases
			final WorldGenerator gen;
			switch( type ) {
				case ROOT:
					gen = new WorldGenRoot(blockID, type.metadata());
					break;
				case TINY_CACTUS:
					gen = new WorldGenTinyCactus(blockID, type.metadata());
					break;
				default:
					gen = new WorldGenMetadataFlowers(blockID, type.metadata());
			}
			flowerGens.put(type, gen);
		}
	}
    
	protected void applyGenerator(BlockType type, World world, int chunkX,
			int chunkZ, Random rand, int times) {
		WorldGenerator gen = flowerGens.get(type);
		if (gen != null) {
			for (int i = 0; i < times; ++i) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				gen.generate(world, rand, x, y, z);
			}
		}
	}

	protected boolean biomeCheck(BiomeSettings settings, BiomeGenBase biome) {
		return settings.getBiome().isPresent()
				&& biome == settings.getBiome().get();
	}

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
        
		if (biomeCheck(BiomeSettings.ALPINE, biome)) {
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.IRIS_BLUE, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.IRIS_PURPLE, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.PANSY, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.VIOLET, world, chunkX, chunkZ, rand, 1);
		}

		if (biomeCheck(BiomeSettings.AUTUMNWOODS, biome))
		{
			applyGenerator(BlockType.ALLIUM, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.REDROVER, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.BIRCHFOREST, biome))
		{
			applyGenerator(BlockType.ALLIUM, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BLUEBELL, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DAISY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DANDELION, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.PANSY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.REDROVER, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.TULIP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.VIOLET, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.EXTREMEJUNGLE, biome))
		{
			applyGenerator(BlockType.GARDENIA, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.FORESTEDHILLS, biome))
		{
			applyGenerator(BlockType.ALLIUM, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BLUEBELL, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DAISY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DANDELION, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.IRIS_BLUE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.IRIS_PURPLE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LAVENDER, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LILY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.ORIENTAL_PINK_LILY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.TULIP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.VIOLET, world, chunkX, chunkZ, rand, 2);
		}
		   
		if (biomeCheck(BiomeSettings.FORESTEDISLAND, biome))
		{
			applyGenerator(BlockType.ALLIUM, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DAISY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DANDELION, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.IRIS_BLUE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.IRIS_PURPLE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LAVENDER, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LILY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.ORIENTAL_PINK_LILY, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.GREENHILLS, biome))
		{
			applyGenerator(BlockType.ALLIUM, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BACHELORS_BUTTON, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BLUEBELL, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DAISY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DANDELION, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.IRIS_BLUE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.IRIS_PURPLE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LILY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.ORIENTAL_PINK_LILY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.TULIP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.VIOLET, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.GREENSWAMP, biome))
		{
			applyGenerator(BlockType.ALLIUM, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DAISY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DANDELION, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.EELGRASS, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LILY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.MARSH_MARIGOLD, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.ORIENTAL_PINK_LILY, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.MEADOW, biome))
		{
			applyGenerator(BlockType.GERBERA_ORANGE, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.GERBERA_PINK, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.GERBERA_RED, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.GERBERA_YELLOW, world, chunkX, chunkZ, rand, 1);

			applyGenerator(BlockType.ALLIUM, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BACHELORS_BUTTON, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DAISY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.YARROW, world, chunkX, chunkZ, rand, 2);

			applyGenerator(BlockType.DANDELION, world, chunkX, chunkZ, rand, 4);
			applyGenerator(BlockType.POPPY, world, chunkX, chunkZ, rand, 4);
		}

		if (biomeCheck(BiomeSettings.MINIJUNGLE, biome))
		{
			applyGenerator(BlockType.GARDENIA, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.MOUNTAINTAIGA, biome))
		{
			applyGenerator(BlockType.PANSY, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.PINEFOREST, biome))
		{
			applyGenerator(BlockType.BLUEBELL, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.PANSY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.REDROVER, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.TULIP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.VIOLET, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.RAINFOREST, biome))
		{
			applyGenerator(BlockType.GARDENIA, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.REDWOODLUSH, biome))
		{
			applyGenerator(BlockType.BLUEBELL, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.TULIP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.VIOLET, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.SAVANNA, biome))
		{
			applyGenerator(BlockType.AMARYLLIS_PINK, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.AMARYLLIS_RED, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.AMARYLLIS_WHITE, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.CALLA_BLACK, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.GERBERA_ORANGE, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.GERBERA_PINK, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.GERBERA_RED, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.GERBERA_YELLOW, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.SNAPDRAGON, world, chunkX, chunkZ, rand, 1);

			applyGenerator(BlockType.ALLIUM, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DAISY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DANDELION, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.POPPY, world, chunkX, chunkZ, rand, 2);

			applyGenerator(BlockType.BELLS_OF_IRELAND, world, chunkX, chunkZ, rand, 4);
			applyGenerator(BlockType.LAVENDER, world, chunkX, chunkZ, rand, 4);
			applyGenerator(BlockType.YARROW, world, chunkX, chunkZ, rand, 4);
		}

		if (biomeCheck(BiomeSettings.SHRUBLAND, biome))
		{
			applyGenerator(BlockType.GERBERA_ORANGE, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.GERBERA_PINK, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.GERBERA_RED, world, chunkX, chunkZ, rand, 1);
			applyGenerator(BlockType.GERBERA_YELLOW, world, chunkX, chunkZ, rand, 1);

			applyGenerator(BlockType.ALLIUM, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BACHELORS_BUTTON, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BLUEBELL, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DAISY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DANDELION, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.SNAPDRAGON, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.TULIP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.YARROW, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.WOODLANDS, biome))
		{
			applyGenerator(BlockType.ALLIUM, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BACHELORS_BUTTON, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BLUEBELL, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DAISY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.DANDELION, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.IRIS_BLUE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.IRIS_PURPLE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LAVENDER, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LILY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.ORIENTAL_PINK_LILY, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.TULIP, world, chunkX, chunkZ, rand, 2);
		}
    
        ////////// nonstandard flowers //////////
        
		if (biomeCheck(BiomeSettings.MOUNTAINDESERT, biome))
        {
			applyGenerator(BlockType.TINY_CACTUS, world, chunkX, chunkZ, rand, 70);
        }
        
		if (biomeCheck(BiomeSettings.MOUNTAINRIDGE, biome))
        {
			applyGenerator(BlockType.TINY_CACTUS, world, chunkX, chunkZ, rand, 62);
        }
        
		if (biomeCheck(BiomeSettings.AUTUMNWOODS, biome)
				|| biomeCheck(BiomeSettings.SNOWYRAINFOREST, biome)
				|| biomeCheck(BiomeSettings.TEMPORATERAINFOREST, biome)
				|| biomeCheck(BiomeSettings.TUNDRA, biome))
        {
			applyGenerator(BlockType.TOADSTOOL, world, chunkX, chunkZ, rand, 2);
        }
    }
}