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
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;

public class FlowerGenerator implements IWorldGenerator
{
    private final WorldGenerator hydrangeaGen;
    private final WorldGenerator buttercupGen;
    private final WorldGenerator lavenderGen;
    @SuppressWarnings("unused")
	private final WorldGenerator rootGen;
    private final WorldGenerator tinyCactusGen;
    private final WorldGenerator toadStoolGen;
    private final WorldGenerator callaGen;
    
    public FlowerGenerator(int flowerID)
    {
        //new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.AUTUMN_SHRUB.metadata());
        hydrangeaGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.HYDRANGEA.metadata());
        buttercupGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.BUTTERCUP.metadata());
        lavenderGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.LAVENDER.metadata());
        rootGen = new WorldGenRoot(flowerID, BlockCustomFlower.BlockType.ROOT.metadata());
        tinyCactusGen = new WorldGenTinyCactus(flowerID, BlockCustomFlower.BlockType.TINY_CACTUS.metadata());
        toadStoolGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.TOADSTOOL.metadata());
        callaGen = new WorldGenMetadataFlowers(flowerID, BlockCustomFlower.BlockType.CALLA_WHITE.metadata());
    }
    
	protected void applyGenerator(WorldGenerator gen, World world, int chunkX,
			int chunkZ, Random rand) {
		final int x = chunkX + rand.nextInt(16) + 8;
		final int y = rand.nextInt(128);
		final int z = chunkZ + rand.nextInt(16) + 8;
		final boolean succ = gen.generate(world, rand, x, y, z);
		LogHelper.info("applyGenerator = " + succ + ", " + gen);
	}

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
        
        if (BiomeSettings.BIRCHFOREST.getBiome().isPresent() && biome == BiomeSettings.BIRCHFOREST.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.FORESTEDHILLS.getBiome().isPresent() && biome == BiomeSettings.FORESTEDHILLS.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
				applyGenerator(lavenderGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.FORESTEDHILLS.getBiome().isPresent() && biome == BiomeSettings.FORESTEDHILLS.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.FORESTEDISLAND.getBiome().isPresent() && biome == BiomeSettings.FORESTEDISLAND.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.AUTUMNWOODS.getBiome().isPresent() && biome == BiomeSettings.AUTUMNWOODS.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.AUTUMNWOODS.getBiome().isPresent() && biome == BiomeSettings.AUTUMNWOODS.getBiome().get()
                || BiomeSettings.SNOWYRAINFOREST.getBiome().isPresent() && biome == BiomeSettings.SNOWYRAINFOREST.getBiome().get()
                || BiomeSettings.TEMPORATERAINFOREST.getBiome().isPresent() && biome == BiomeSettings.TEMPORATERAINFOREST.getBiome().get()
                || BiomeSettings.TUNDRA.getBiome().isPresent() && biome == BiomeSettings.TUNDRA.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(toadStoolGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.GREENHILLS.getBiome().isPresent() && biome == BiomeSettings.GREENHILLS.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.GREENSWAMP.getBiome().isPresent() && biome == BiomeSettings.GREENSWAMP.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
            }
            
            /*
            for (int i = 0; i < 5; i++)
            {
                final int x = chunkX + rand.nextInt(16) + 8;
                final int y = rand.nextInt(128);
                final int z = chunkZ + rand.nextInt(16) + 8;
                rootGen.generate(world, rand, x, y, z);
            }
            */
        }
        
        if (BiomeSettings.MINIJUNGLE.getBiome().isPresent() && biome == BiomeSettings.MINIJUNGLE.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.MOUNTAINDESERT.getBiome().isPresent() && biome == BiomeSettings.MOUNTAINDESERT.getBiome().get())
        {
            for (int i = 0; i < 70; i++)
            {
				applyGenerator(tinyCactusGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.MOUNTAINRIDGE.getBiome().isPresent() && biome == BiomeSettings.MOUNTAINRIDGE.getBiome().get())
        {
            for (int i = 0; i < 62; i++)
            {
				applyGenerator(tinyCactusGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.PINEFOREST.getBiome().isPresent() && biome == BiomeSettings.PINEFOREST.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.RAINFOREST.getBiome().isPresent() && biome == BiomeSettings.RAINFOREST.getBiome().get())
        {
            for (int i = 0; i < 4; i++)
            {
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.REDWOODFOREST.getBiome().isPresent() && biome == BiomeSettings.REDWOODFOREST.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.REDWOODLUSH.getBiome().isPresent() && biome == BiomeSettings.REDWOODLUSH.getBiome().get())
        {
            /*for (int i = 0; i < 5; i++)
            {
                final int x = chunkX + rand.nextInt(16) + 8;
                final int y = rand.nextInt(128);
                final int z = chunkZ + rand.nextInt(16) + 8;
                rootGen.generate(world, rand, x, y, z);
            }//*/
            
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.SAVANNA.getBiome().isPresent() && biome == BiomeSettings.SAVANNA.getBiome().get())
        {
            for (int i = 0; i < 6; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
            }
            
            for (int i = 0; i < 8; i++)
            {
				applyGenerator(lavenderGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.SHRUBLAND.getBiome().isPresent() && biome == BiomeSettings.SHRUBLAND.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
				applyGenerator(lavenderGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.TEMPORATERAINFOREST.getBiome().isPresent() && biome == BiomeSettings.TEMPORATERAINFOREST.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.WOODLANDS.getBiome().isPresent() && biome == BiomeSettings.WOODLANDS.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
            }
        }
        
        if (BiomeSettings.MEADOW.getBiome().isPresent() && biome == BiomeSettings.MEADOW.getBiome().get())
        {
            for (int i = 0; i < 2; i++)
            {
				applyGenerator(buttercupGen, world, chunkX, chunkZ, rand);
				applyGenerator(hydrangeaGen, world, chunkX, chunkZ, rand);
				applyGenerator(callaGen, world, chunkX, chunkZ, rand);
				applyGenerator(lavenderGen, world, chunkX, chunkZ, rand);
            }
        }
    }
}