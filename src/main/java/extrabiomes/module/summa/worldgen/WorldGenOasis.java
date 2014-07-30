/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import extrabiomes.lib.BiomeSettings;

class WorldGenOasis extends WorldGenerator
{
    
    private static final int AVERAGE_OASIS = 7;
    
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        
        if (world.getBlock(x, y, z).getMaterial() != Material.water)
            return false;
        
        final int xzRadius = rand.nextInt(AVERAGE_OASIS - 2) + 2;
        final int yRadius = 2;
        
        for (int x1 = x - xzRadius; x1 <= x + xzRadius; x1++)
            for (int z1 = z - xzRadius; z1 <= z + xzRadius; z1++)
            {
                final int a = x1 - x;
                final int b = z1 - z;
                
                if (a * a + b * b > xzRadius * xzRadius)
                    continue;
                
                for (int y1 = y - yRadius; y1 <= y + yRadius; y1++)
                {
                    final Block blocktoReplace = world.getBlock(x1, y1, z1);
                    
                    if (blocktoReplace.equals(Blocks.stone)
                            || blocktoReplace.equals(Blocks.sand)
                            || blocktoReplace.equals(Blocks.sandstone)
                            || (BiomeSettings.MOUNTAINRIDGE.getBiome().isPresent() && blocktoReplace == BiomeSettings.MOUNTAINRIDGE.getBiome().get().topBlock))
                        world.setBlock(x1, y1, z1, Blocks.grass);
                }
            }
        
        return true;
    }
}
