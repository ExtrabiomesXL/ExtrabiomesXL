/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import extrabiomes.lib.BiomeSettings;

public class QuicksandGenerator implements IWorldGenerator
{
    
    private final WorldGenQuicksand genPit;
    
    public QuicksandGenerator(Block quicksand)
    {
        genPit = new WorldGenQuicksand(quicksand);
    }
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ,
            World world, IChunkGenerator chunkGenerator,
            IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        final Biome biome = world.getBiomeGenForCoords(new BlockPos(chunkX, 64, chunkZ));
        if (!BiomeSettings.MINIJUNGLE.getBiome().isPresent() || biome != BiomeSettings.MINIJUNGLE.getBiome().get())
            return;
        
        // 1 to 3 attempts with with a bias toward 2
        final int attempts = random.nextInt(1) + random.nextInt(1) + 1;
        for (int i = 0; i < attempts; i++)
            if (random.nextBoolean())
            {
                final int x = randomizedCoord(random, chunkX);
                final int z = randomizedCoord(random, chunkZ);
                final BlockPos pos = new BlockPos(x, 0, z);
                genPit.generate(world, random, pos.up());
            }
    }
    
    private int randomizedCoord(Random random, int coord)
    {
        return coord + random.nextInt(16) + 8;
    }
    
}
