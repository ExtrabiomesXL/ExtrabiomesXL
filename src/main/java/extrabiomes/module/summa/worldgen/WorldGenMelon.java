/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenMelon extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int x, int y,
            int z)
    {
        for (int i = 0; i < 64; ++i)
        {
            final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
            final int y1 = y + rand.nextInt(4) - rand.nextInt(4);
            final int z1 = z + rand.nextInt(8) - rand.nextInt(8);
            
            if (world.isAirBlock(x1, y1, z1)
                    && world.getBlock(x1, y1 - 1, z1) == Blocks.grass
                    && Blocks.pumpkin.canPlaceBlockAt(world, x1, y1, z1))
                world.setBlock(x1, y1, z1, Blocks.melon_block);
        }
        
        return true;
    }
}
