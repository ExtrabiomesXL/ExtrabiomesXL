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
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import extrabiomes.lib.BiomeSettings;

public class VanillaFloraGenerator implements IWorldGenerator
{
    
    private static final WorldGenerator vineGen = new WorldGenVines();
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        chunkX = chunkX << 4;
        chunkZ = chunkZ << 4;
        final BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
        
        final boolean biomeIsExtremeJungle = BiomeSettings.EXTREMEJUNGLE.getBiome().isPresent() && biome == BiomeSettings.EXTREMEJUNGLE.getBiome().get();
        
        if (biomeIsExtremeJungle)
        {
            generateVines(random, chunkX, chunkZ, world);
        }
        
        final boolean biomeIsMiniJungle = BiomeSettings.MINIJUNGLE.getBiome().isPresent() && biome == BiomeSettings.MINIJUNGLE.getBiome().get();
        final boolean biomeIsTemperateRainforest = BiomeSettings.TEMPORATERAINFOREST.getBiome().isPresent() && biome == BiomeSettings.TEMPORATERAINFOREST.getBiome().get();
        
        if (biomeIsExtremeJungle || biomeIsMiniJungle || biomeIsTemperateRainforest || biome == BiomeGenBase.jungle || biome == BiomeGenBase.jungleHills)
        {
            if (random.nextInt(48) == 0)
            {
                generateMelonPatch(world, random, chunkX, chunkZ);
            }
        }
        
    }
    
    private void generateMelonPatch(World world, Random rand, int x, int z)
    {
        x += rand.nextInt(16) + 8;
        final int y = rand.nextInt(128);
        z += rand.nextInt(16) + 8;
        new WorldGenMelon().generate(world, rand, x, y, z);
    }
    
    private void generateVines(Random rand, int x, int z, World world)
    {
        for (int i = 0; i < 50; ++i)
        {
            final int x1 = x + rand.nextInt(16) + 8;
            final int z1 = z + rand.nextInt(16) + 8;
            vineGen.generate(world, rand, x1, 64, z1);
        }
    }
}
