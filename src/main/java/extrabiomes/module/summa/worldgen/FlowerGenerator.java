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

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
        
        if (BiomeSettings.BIRCHFOREST.getBiome().isPresent() && biome == BiomeSettings.BIRCHFOREST.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.FORESTEDHILLS.getBiome().isPresent() && biome == BiomeSettings.FORESTEDHILLS.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LAVENDER, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.FORESTEDHILLS.getBiome().isPresent() && biome == BiomeSettings.FORESTEDHILLS.getBiome().get())
        {
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.FORESTEDISLAND.getBiome().isPresent() && biome == BiomeSettings.FORESTEDISLAND.getBiome().get())
        {
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.AUTUMNWOODS.getBiome().isPresent() && biome == BiomeSettings.AUTUMNWOODS.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.AUTUMNWOODS.getBiome().isPresent() && biome == BiomeSettings.AUTUMNWOODS.getBiome().get()
                || BiomeSettings.SNOWYRAINFOREST.getBiome().isPresent() && biome == BiomeSettings.SNOWYRAINFOREST.getBiome().get()
                || BiomeSettings.TEMPORATERAINFOREST.getBiome().isPresent() && biome == BiomeSettings.TEMPORATERAINFOREST.getBiome().get()
                || BiomeSettings.TUNDRA.getBiome().isPresent() && biome == BiomeSettings.TUNDRA.getBiome().get())
        {
			applyGenerator(BlockType.TOADSTOOL, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.GREENHILLS.getBiome().isPresent() && biome == BiomeSettings.GREENHILLS.getBiome().get())
        {
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.GREENSWAMP.getBiome().isPresent() && biome == BiomeSettings.GREENSWAMP.getBiome().get())
        {
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.MINIJUNGLE.getBiome().isPresent() && biome == BiomeSettings.MINIJUNGLE.getBiome().get())
        {
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.MOUNTAINDESERT.getBiome().isPresent() && biome == BiomeSettings.MOUNTAINDESERT.getBiome().get())
        {
			applyGenerator(BlockType.TINY_CACTUS, world, chunkX, chunkZ, rand, 70);
        }
        
        if (BiomeSettings.MOUNTAINRIDGE.getBiome().isPresent() && biome == BiomeSettings.MOUNTAINRIDGE.getBiome().get())
        {
			applyGenerator(BlockType.TINY_CACTUS, world, chunkX, chunkZ, rand, 62);
        }
        
        if (BiomeSettings.PINEFOREST.getBiome().isPresent() && biome == BiomeSettings.PINEFOREST.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.RAINFOREST.getBiome().isPresent() && biome == BiomeSettings.RAINFOREST.getBiome().get())
        {
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 4);
        }
        
        if (BiomeSettings.REDWOODFOREST.getBiome().isPresent() && biome == BiomeSettings.REDWOODFOREST.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.REDWOODLUSH.getBiome().isPresent() && biome == BiomeSettings.REDWOODLUSH.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.SAVANNA.getBiome().isPresent() && biome == BiomeSettings.SAVANNA.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 6);
			applyGenerator(BlockType.LAVENDER, world, chunkX, chunkZ, rand, 8);
        }
        
        if (BiomeSettings.SHRUBLAND.getBiome().isPresent() && biome == BiomeSettings.SHRUBLAND.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LAVENDER, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.TEMPORATERAINFOREST.getBiome().isPresent() && biome == BiomeSettings.TEMPORATERAINFOREST.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.WOODLANDS.getBiome().isPresent() && biome == BiomeSettings.WOODLANDS.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
        }
        
        if (BiomeSettings.MEADOW.getBiome().isPresent() && biome == BiomeSettings.MEADOW.getBiome().get())
        {
			applyGenerator(BlockType.BUTTERCUP, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.HYDRANGEA, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.CALLA_WHITE, world, chunkX, chunkZ, rand, 2);
			applyGenerator(BlockType.LAVENDER, world, chunkX, chunkZ, rand, 2);
        }
    }
}