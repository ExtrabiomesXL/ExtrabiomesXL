/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import extrabiomes.lib.BiomeSettings;

public class LegendOakGenerator implements IWorldGenerator
{
    
    private final WorldGenerator treeGen;
    
    public LegendOakGenerator()
    {
        treeGen = new WorldGenLegendOak(false);
    }
    
    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        int rarity = 0;
        final Biome biome = world.getBiomeGenForCoords(chunkX, chunkX);
        
        if (biome == Biomes.FOREST || biome == Biomes.FOREST_HILLS
                || BiomeSettings.FORESTEDHILLS.getBiome().isPresent() && biome == BiomeSettings.FORESTEDHILLS.getBiome().get()
                || BiomeSettings.RAINFOREST.getBiome().isPresent() && biome == BiomeSettings.RAINFOREST.getBiome().get())
        {
            rarity = 100;
        }
        
        if (biome == Biomes.PLAINS || biome == Biomes.EXTREME_HILLS_EDGE)
        {
            rarity = 1000;
        }
        
        if (rarity == 0)
            return;
        
        if (rand.nextInt(rarity) == 0)
        {
            final int x = chunkX + rand.nextInt(16) + 8;
            final int z = chunkZ + rand.nextInt(16) + 8;
            final int y = world.getHeightValue(x, z);
            treeGen.generate(world, rand, x, y, z);
        }
    }
}
