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
import extrabiomes.blocks.BlockCustomVine;
import extrabiomes.lib.BiomeSettings;

public class VineGenerator implements IWorldGenerator
{
    private final WorldGenerator vineGen;
    private final BiomeSettings[] biomeList;
    
    // which vines do we generate in?
    private final BiomeSettings[] DEFAULT_BIOME_LIST = {
    	BiomeSettings.GREENSWAMP, BiomeSettings.MINIJUNGLE
    };
    
    public VineGenerator(BlockCustomVine block)
    {
        this(block, null);
    }
    
    public VineGenerator(BlockCustomVine block, BiomeSettings[] biomeList)
    {
    	vineGen = new WorldGenCustomVine(block);
    	this.biomeList = (biomeList == null ? DEFAULT_BIOME_LIST : biomeList);
    }
    
    private boolean biomeCheck(BiomeGenBase biome) {
		final BiomeSettings settings = BiomeSettings.findBiomeSettings(biome.biomeID);    	
		if( settings != null ) {
			for( final BiomeSettings goodBiome : biomeList ) {
				if( settings == goodBiome )
					return settings.getBiome().isPresent() && biome == settings.getBiome().get();
			}
		}
		return false;
    }
    
    @Override
    public void generate(Random rand, int chunkX, int chunkZ,
            World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
        
        if( !biomeCheck(biome) ) return;

        //LogHelper.info("Gloriosa starting generation in " + biome.biomeName);
		
		for (int i = 0; i < 20; i++)
        {
            final int x = chunkX + rand.nextInt(16) + 8;
            final int z = chunkZ + rand.nextInt(16) + 8;
            final int y = world.getHeightValue(x, z);
            vineGen.generate(world, rand, x, y, z);
        }
    }
}
