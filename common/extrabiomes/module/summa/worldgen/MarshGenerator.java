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

public class MarshGenerator implements IWorldGenerator
{
    
    private static final WorldGenerator genMarsh   = new WorldGenMarshGrass();
    private static final WorldGenerator genDirtBed = new WorldGenMarshDirt();
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ,
            World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX,
                chunkZ);
        
        if (BiomeSettings.MARSH.getBiome().isPresent() && biome == BiomeSettings.MARSH.getBiome().get())
            generateMarsh(random, chunkX, chunkZ, world);
        
    }
    
    private void generateMarsh(Random rand, int x, int z, World world)
    {
        for (int i = 0; i < 127; i++)
        {
            final int x1 = x + rand.nextInt(16) + 8;
            final int z1 = z + rand.nextInt(16) + 8;
            genMarsh.generate(world, rand, x1, 0, z1);
        }
        
        for (int i = 0; i < 256; i++)
        {
            final int x1 = x + rand.nextInt(1) + 8;
            final int z1 = z + rand.nextInt(1) + 8;
            genDirtBed.generate(world, rand, x1, 0, z1);
        }
    }
}
