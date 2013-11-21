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
import extrabiomes.lib.BiomeSettings;

public class LegendOakGenerator implements IWorldGenerator
{
    
    private final WorldGenerator treeGen;
    
    public LegendOakGenerator()
    {
        treeGen = new WorldGenLegendOak(false);
    }
    
    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        int rarity = 0;
        final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkX);
        
        if (biome == BiomeGenBase.forest || biome == BiomeGenBase.forestHills
                || BiomeSettings.FORESTEDHILLS.getBiome().isPresent() && biome == BiomeSettings.FORESTEDHILLS.getBiome().get()
                || BiomeSettings.FORESTEDISLAND.getBiome().isPresent() && biome == BiomeSettings.FORESTEDISLAND.getBiome().get()
                || BiomeSettings.RAINFOREST.getBiome().isPresent() && biome == BiomeSettings.RAINFOREST.getBiome().get())
        {
            rarity = 100;
        }
        
        if (biome == BiomeGenBase.plains || biome == BiomeGenBase.extremeHillsEdge)
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
