package extrabiomes.module.amica.buildcraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.lib.BiomeSettings;

public class OilGenerator implements IWorldGenerator
{
    
    private final BuildcraftAPI api;
    
    OilGenerator(BuildcraftAPI api)
    {
        this.api = api;
    }
    
    private void doPopulate(Random rand, World world, int x, int z)
    {
        final BiomeGenBase biome = world.getWorldChunkManager()
                .getBiomeGenAt(x, z);
        
        if (((BiomeSettings.MOUNTAINDESERT.getBiome().isPresent() && biome == BiomeSettings.MOUNTAINDESERT.getBiome().get())
                || (BiomeSettings.WASTELAND.getBiome().isPresent() && biome == BiomeSettings.WASTELAND.getBiome().get()))
                && rand.nextFloat() > 0.97)
        {
            // Generate a small deposit
            
            final int startX = rand.nextInt(10) + 2;
            final int startZ = rand.nextInt(10) + 2;
            
            for (int j = 128; j > 65; --j)
            {
                final int i = startX + x;
                final int k = startZ + z;
                
                final Block block = world.getBlock(i, j, k);
                if (!block.isAir(world, i, j, k))
                {
                    if (block.equals(Blocks.sand)
                            || (BiomeSettings.WASTELAND.getBiome().isPresent() && block.equals(BiomeSettings.WASTELAND.getBiome().get().topBlock)))
                        api.generateSurfaceDeposit(world, rand, i, j, k, 3);
                    
                    break;
                }
            }
        }
    }
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ,
            World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        
        doPopulate(random, world, chunkX, chunkZ);
    }
    
}
