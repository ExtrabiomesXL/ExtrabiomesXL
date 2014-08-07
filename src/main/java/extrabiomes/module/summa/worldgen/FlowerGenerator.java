/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import extrabiomes.Extrabiomes;
import extrabiomes.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.blocks.BlockCropBasic;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomFlower.BlockType;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings.Decoration;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.biome.ExtrabiomeGenBase;

public class FlowerGenerator implements IWorldGenerator
{
	private final Map<BlockType, WorldGenerator>						flowerGens	= Maps.newHashMap();
	private final Map<BiomeSettings, List<BlockCustomFlower.BlockType>>	flowerMaps	= Maps.newHashMap();
	private final Map<Element, WorldGenerator>							cropGens	= Maps.newHashMap();

	private static FlowerGenerator										INSTANCE;
    
	public static FlowerGenerator getInstance() {
		if (INSTANCE == null) INSTANCE = new FlowerGenerator();
		return INSTANCE;
	}

	private FlowerGenerator()
    {
		registerFlower(BiomeSettings.ALPINE, BlockType.BLUE_POPPY);
		registerFlower(BiomeSettings.ALPINE, BlockType.HYDRANGEA);
		registerFlower(BiomeSettings.ALPINE, BlockType.IRIS_BLUE);
		registerFlower(BiomeSettings.ALPINE, BlockType.IRIS_PURPLE);
		registerFlower(BiomeSettings.ALPINE, BlockType.PANSY);
		registerFlower(BiomeSettings.ALPINE, BlockType.VIOLET);

		registerFlower(BiomeSettings.AUTUMNWOODS, BlockType.ALLIUM);
		registerFlower(BiomeSettings.AUTUMNWOODS, BlockType.REDROVER);

		registerFlower(BiomeSettings.BIRCHFOREST, BlockType.ALLIUM);
		registerFlower(BiomeSettings.BIRCHFOREST, BlockType.BLUEBELL);
		registerFlower(BiomeSettings.BIRCHFOREST, BlockType.BUTTERCUP);
		registerFlower(BiomeSettings.BIRCHFOREST, BlockType.DAISY);
		registerFlower(BiomeSettings.BIRCHFOREST, BlockType.DANDELION);
		registerFlower(BiomeSettings.BIRCHFOREST, BlockType.HYDRANGEA);
		registerFlower(BiomeSettings.BIRCHFOREST, BlockType.PANSY);
		registerFlower(BiomeSettings.BIRCHFOREST, BlockType.REDROVER);
		registerFlower(BiomeSettings.BIRCHFOREST, BlockType.TULIP);
		registerFlower(BiomeSettings.BIRCHFOREST, BlockType.VIOLET);

		registerFlower(BiomeSettings.EXTREMEJUNGLE, BlockType.BELLADONNA);
		registerFlower(BiomeSettings.EXTREMEJUNGLE, BlockType.GARDENIA);
		registerFlower(BiomeSettings.EXTREMEJUNGLE, BlockType.GERBERA_ORANGE);
		registerFlower(BiomeSettings.EXTREMEJUNGLE, BlockType.GERBERA_PINK);
		registerFlower(BiomeSettings.EXTREMEJUNGLE, BlockType.GERBERA_RED);
		registerFlower(BiomeSettings.EXTREMEJUNGLE, BlockType.GERBERA_YELLOW);
		registerFlower(BiomeSettings.EXTREMEJUNGLE, BlockType.ORIENTAL_PINK_LILY);

		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.ALLIUM);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.BLUEBELL);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.DAISY);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.DANDELION);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.HYDRANGEA);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.IRIS_BLUE);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.IRIS_PURPLE);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.LAVENDER);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.LILY);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.ORIENTAL_PINK_LILY);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.TULIP);
		registerFlower(BiomeSettings.FORESTEDHILLS, BlockType.VIOLET);

		registerFlower(BiomeSettings.FORESTEDISLAND, BlockType.ALLIUM);
		registerFlower(BiomeSettings.FORESTEDISLAND, BlockType.BLUE_POPPY);
		registerFlower(BiomeSettings.FORESTEDISLAND, BlockType.DAISY);
		registerFlower(BiomeSettings.FORESTEDISLAND, BlockType.DANDELION);
		registerFlower(BiomeSettings.FORESTEDISLAND, BlockType.IRIS_BLUE);
		registerFlower(BiomeSettings.FORESTEDISLAND, BlockType.IRIS_PURPLE);
		registerFlower(BiomeSettings.FORESTEDISLAND, BlockType.LAVENDER);
		registerFlower(BiomeSettings.FORESTEDISLAND, BlockType.LILY);
		registerFlower(BiomeSettings.FORESTEDISLAND, BlockType.ORIENTAL_PINK_LILY);

		registerFlower(BiomeSettings.GREENHILLS, BlockType.ALLIUM);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.BACHELORS_BUTTON);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.BLUEBELL);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.BLUE_POPPY);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.DAISY);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.DANDELION);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.IRIS_BLUE);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.IRIS_PURPLE);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.LILY);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.ORIENTAL_PINK_LILY);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.TULIP);
		registerFlower(BiomeSettings.GREENHILLS, BlockType.VIOLET);

		registerFlower(BiomeSettings.GREENSWAMP, BlockType.ALLIUM);
		registerFlower(BiomeSettings.GREENSWAMP, BlockType.BELLADONNA);
		registerFlower(BiomeSettings.GREENSWAMP, BlockType.BLUE_POPPY);
		registerFlower(BiomeSettings.GREENSWAMP, BlockType.BUTTERCUP);
		registerFlower(BiomeSettings.GREENSWAMP, BlockType.DAISY);
		registerFlower(BiomeSettings.GREENSWAMP, BlockType.DANDELION);
		//registerFlower(BiomeSettings.GREENSWAMP, BlockType.EELGRASS);
		registerFlower(BiomeSettings.GREENSWAMP, BlockType.LILY);
		registerFlower(BiomeSettings.GREENSWAMP, BlockType.MARSH_MARIGOLD);
		registerFlower(BiomeSettings.GREENSWAMP, BlockType.ORIENTAL_PINK_LILY);

		registerFlower(BiomeSettings.MEADOW, BlockType.ALLIUM);
		registerFlower(BiomeSettings.MEADOW, BlockType.BACHELORS_BUTTON);
		registerFlower(BiomeSettings.MEADOW, BlockType.BUTTERCUP);
		registerFlower(BiomeSettings.MEADOW, BlockType.DAISY);
		registerFlower(BiomeSettings.MEADOW, BlockType.DANDELION);
		registerFlower(BiomeSettings.MEADOW, BlockType.GERBERA_ORANGE);
		registerFlower(BiomeSettings.MEADOW, BlockType.GERBERA_PINK);
		registerFlower(BiomeSettings.MEADOW, BlockType.GERBERA_RED);
		registerFlower(BiomeSettings.MEADOW, BlockType.GERBERA_YELLOW);
		registerFlower(BiomeSettings.MEADOW, BlockType.HYDRANGEA);
		registerFlower(BiomeSettings.MEADOW, BlockType.POPPY);
		registerFlower(BiomeSettings.MEADOW, BlockType.YARROW);

		registerFlower(BiomeSettings.MINIJUNGLE, BlockType.BELLADONNA);
		registerFlower(BiomeSettings.MINIJUNGLE, BlockType.BLUE_POPPY);
		registerFlower(BiomeSettings.MINIJUNGLE, BlockType.GARDENIA);
		registerFlower(BiomeSettings.MINIJUNGLE, BlockType.GERBERA_ORANGE);
		registerFlower(BiomeSettings.MINIJUNGLE, BlockType.GERBERA_PINK);
		registerFlower(BiomeSettings.MINIJUNGLE, BlockType.GERBERA_RED);
		registerFlower(BiomeSettings.MINIJUNGLE, BlockType.GERBERA_YELLOW);
		registerFlower(BiomeSettings.MINIJUNGLE, BlockType.ORIENTAL_PINK_LILY);

		registerFlower(BiomeSettings.MOUNTAINTAIGA, BlockType.PANSY);

		registerFlower(BiomeSettings.PINEFOREST, BlockType.BLUEBELL);
		registerFlower(BiomeSettings.PINEFOREST, BlockType.BUTTERCUP);
		registerFlower(BiomeSettings.PINEFOREST, BlockType.PANSY);
		registerFlower(BiomeSettings.PINEFOREST, BlockType.REDROVER);
		registerFlower(BiomeSettings.PINEFOREST, BlockType.TULIP);
		registerFlower(BiomeSettings.PINEFOREST, BlockType.VIOLET);

		registerFlower(BiomeSettings.RAINFOREST, BlockType.BELLADONNA);
		registerFlower(BiomeSettings.RAINFOREST, BlockType.GARDENIA);
		registerFlower(BiomeSettings.RAINFOREST, BlockType.GERBERA_ORANGE);
		registerFlower(BiomeSettings.RAINFOREST, BlockType.GERBERA_PINK);
		registerFlower(BiomeSettings.RAINFOREST, BlockType.GERBERA_RED);
		registerFlower(BiomeSettings.RAINFOREST, BlockType.GERBERA_YELLOW);
		registerFlower(BiomeSettings.RAINFOREST, BlockType.ORIENTAL_PINK_LILY);

		registerFlower(BiomeSettings.REDWOODLUSH, BlockType.BLUEBELL);
		registerFlower(BiomeSettings.REDWOODLUSH, BlockType.TULIP);
		registerFlower(BiomeSettings.REDWOODLUSH, BlockType.VIOLET);

		registerFlower(BiomeSettings.SAVANNA, BlockType.ALLIUM);
		registerFlower(BiomeSettings.SAVANNA, BlockType.AMARYLLIS_PINK);
		registerFlower(BiomeSettings.SAVANNA, BlockType.AMARYLLIS_RED);
		registerFlower(BiomeSettings.SAVANNA, BlockType.AMARYLLIS_WHITE);
		registerFlower(BiomeSettings.SAVANNA, BlockType.BELLS_OF_IRELAND);
		registerFlower(BiomeSettings.SAVANNA, BlockType.CALLA_BLACK);
		registerFlower(BiomeSettings.SAVANNA, BlockType.CALLA_WHITE);
		registerFlower(BiomeSettings.SAVANNA, BlockType.DAISY);
		registerFlower(BiomeSettings.SAVANNA, BlockType.DANDELION);
		registerFlower(BiomeSettings.SAVANNA, BlockType.GERBERA_ORANGE);
		registerFlower(BiomeSettings.SAVANNA, BlockType.GERBERA_PINK);
		registerFlower(BiomeSettings.SAVANNA, BlockType.GERBERA_RED);
		registerFlower(BiomeSettings.SAVANNA, BlockType.GERBERA_YELLOW);
		registerFlower(BiomeSettings.SAVANNA, BlockType.HYDRANGEA);
		registerFlower(BiomeSettings.SAVANNA, BlockType.LAVENDER);
		registerFlower(BiomeSettings.SAVANNA, BlockType.POPPY);
		registerFlower(BiomeSettings.SAVANNA, BlockType.SNAPDRAGON);
		registerFlower(BiomeSettings.SAVANNA, BlockType.YARROW);

		registerFlower(BiomeSettings.SHRUBLAND, BlockType.ALLIUM);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.BACHELORS_BUTTON);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.BLUEBELL);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.DAISY);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.DANDELION);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.GERBERA_ORANGE);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.GERBERA_PINK);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.GERBERA_RED);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.GERBERA_YELLOW);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.HYDRANGEA);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.SNAPDRAGON);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.TULIP);
		registerFlower(BiomeSettings.SHRUBLAND, BlockType.YARROW);

		registerFlower(BiomeSettings.WOODLANDS, BlockType.ALLIUM);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.BACHELORS_BUTTON);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.BLUEBELL);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.BLUE_POPPY);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.DAISY);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.DANDELION);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.IRIS_BLUE);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.IRIS_PURPLE);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.LAVENDER);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.LILY);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.ORIENTAL_PINK_LILY);
		registerFlower(BiomeSettings.WOODLANDS, BlockType.TULIP);
    }
    
	public void registerBlock(Block block, Collection<BlockType> types) {
		for( BlockCustomFlower.BlockType type : types ) {
			// special cases
			final WorldGenerator gen;
			switch( type ) {
				case ROOT:
					gen = new WorldGenRoot(block, type.metadata());
					break;
				case TINY_CACTUS:
					gen = new WorldGenTinyCactus(block, type.metadata());
					break;
				default:
					gen = new WorldGenMetadataFlowers(block, type.metadata());
			}
			flowerGens.put(type, gen);
		}
	}

	// special gen for wild crops
	public void registerCrop(Element element) {
		if (element.isPresent()) {
			final WorldGenerator gen = new WorldGenMetadataFlowers(
					Block.getBlockFromItem(element.get().getItem()), BlockCropBasic.MAX_GROWTH_STAGE);
			cropGens.put(element, gen);
		}
	}

	protected void registerFlower(BiomeSettings settings, BlockType type) {
		if (!settings.getBiome().isPresent()) return;

        BiomeGenBase biome = settings.getBiome().get();
        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.addGrassPlant(type.block(), type.metadata(), type.weight(), biome);

		final List<BlockType> list;
		if (flowerMaps.containsKey(settings)) {
			list = flowerMaps.get(settings);
		} else {
			list = Lists.newArrayList();
			flowerMaps.put(settings, list);
		}
		list.add(type);
	}
    
	protected int applyGenerator(BlockType type, World world, int chunkX, int chunkZ, Random rand, int times) {
		final WorldGenerator gen = flowerGens.get(type);
		int count = 0;
		if (gen != null) {
			for (int i = 0; i < times; ++i) {
				final int x = chunkX + rand.nextInt(16) + 8;
				final int y = rand.nextInt(128);
				final int z = chunkZ + rand.nextInt(16) + 8;
				if (gen.generate(world, rand, x, y, z)) ++count;
			}
		}
		return count;
	}

	protected boolean applyGenerator(BlockType type, World world, int chunkX, int chunkZ, Random rand) {
		final WorldGenerator gen = flowerGens.get(type);
		if (gen != null) {
			return applyGenerator(gen, world, chunkX, chunkZ, rand);
		}
		return false;
	}

	protected boolean applyGenerator(WorldGenerator gen, World world, int chunkX, int chunkZ, Random rand) {
		if (gen != null) {
			final int x = chunkX + rand.nextInt(16) + 8;
			final int y = rand.nextInt(128);
			final int z = chunkZ + rand.nextInt(16) + 8;
			return gen.generate(world, rand, x, y, z);
		}
		return false;
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
        
		final BiomeSettings settings = BiomeSettings.findBiomeSettings(biome.biomeID);
		if (settings != null && biomeCheck(settings, biome) && flowerMaps.containsKey(settings)) {
			final ExtrabiomeGenBase eBiome = (ExtrabiomeGenBase)biome; 
			final int maxFlowers = eBiome.getDecorationSettings().getSetting(Decoration.NEW_FLOWERS);
			if( maxFlowers > 0 ) {
				List<BlockType> map = flowerMaps.get(settings);
				//LogHelper.finer("[FG] "+eBiome.getDecorationSettings()+" = "+maxFlowers+", "+map.size()+" varieties");
				for (int flowers = 0; flowers < maxFlowers; ++flowers) {
					final int idx = rand.nextInt(map.size());
					final BlockType type = map.get(idx);
					//LogHelper.finest("[FG] "+eBiome.biomeName+" > "+flowers+"/"+maxFlowers+" = "+type);
					applyGenerator(type, world, chunkX, chunkZ, rand);
				}
			}
		}

		// //////// nonstandard flowers //////////

		if (biomeCheck(BiomeSettings.MOUNTAINDESERT, biome)
				|| biomeCheck(BiomeSettings.MOUNTAINRIDGE, biome)) {
			applyGenerator(BlockType.TINY_CACTUS, world, chunkX, chunkZ, rand, 64);
		}

		if (biomeCheck(BiomeSettings.AUTUMNWOODS, biome)
				|| biomeCheck(BiomeSettings.PINEFOREST, biome)
				|| biomeCheck(BiomeSettings.TEMPORATERAINFOREST, biome)) {
			applyGenerator(BlockType.TOADSTOOL, world, chunkX, chunkZ, rand, 2);
		}

		if (biomeCheck(BiomeSettings.GREENHILLS, biome)
				|| biomeCheck(BiomeSettings.FORESTEDHILLS, biome)
				|| biomeCheck(BiomeSettings.FORESTEDISLAND, biome)
				|| biomeCheck(BiomeSettings.BIRCHFOREST, biome)
				|| biomeCheck(BiomeSettings.PINEFOREST, biome)
				|| biomeCheck(BiomeSettings.MEADOW, biome)
				|| biomeCheck(BiomeSettings.WOODLANDS, biome)) {
			final WorldGenerator gen = cropGens.get(Element.PLANT_STRAWBERRY);
			for (int x = 0; x < 4; ++x)
				applyGenerator(gen, world, chunkX, chunkZ, rand);
		}
    }
}