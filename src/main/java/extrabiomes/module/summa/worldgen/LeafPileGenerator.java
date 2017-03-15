/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import extrabiomes.lib.BiomeSettings;

public class LeafPileGenerator implements IWorldGenerator
{
    
    private final WorldGenerator leafPileGen;
    
    public LeafPileGenerator(Block block)
    {
        leafPileGen = new WorldGenLeafPile(block);
    }
    
    @Override
    public void generate(Random rand, int chunkX, int chunkZ,
            World world, IChunkGenerator chunkGenerator,
            IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        final Biome biome = world.getBiomeGenForCoords(new BlockPos(chunkX, 64, chunkZ));
        
        if (BiomeSettings.GREENSWAMP.getBiome().isPresent() && biome == BiomeSettings.GREENSWAMP.getBiome().get()
                || BiomeSettings.MOUNTAINRIDGE.getBiome().isPresent() && biome == BiomeSettings.MOUNTAINRIDGE.getBiome().get()
                || BiomeSettings.REDWOODLUSH.getBiome().isPresent() && biome == BiomeSettings.REDWOODLUSH.getBiome().get()
                || BiomeSettings.WOODLANDS.getBiome().isPresent() && biome == BiomeSettings.WOODLANDS.getBiome().get())
            for (int i = 0; i < 2; i++)
            {
                final int x = chunkX + rand.nextInt(16) + 8;
                final int y = rand.nextInt(128);
                final int z = chunkZ + rand.nextInt(16) + 8;
                leafPileGen.generate(world, rand, new BlockPos(x, y, z));
            }
    }
    
}
