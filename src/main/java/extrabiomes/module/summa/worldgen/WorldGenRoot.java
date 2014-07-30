/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenRoot extends WorldGenerator
{
    
    private final Block block;
    private final int metadata;
    
    WorldGenRoot(Block block, int metadata)
    {
        this.block = block;
        this.metadata = metadata;
    }
    
    @Override
    public boolean generate(World world, Random rand, int x, int y,
            int z)
    {
        for (int i = 0; i < 10; i++)
        {
            final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
            final int y1 = y + rand.nextInt(4) - rand.nextInt(4);
            final int z1 = z + rand.nextInt(8) - rand.nextInt(8);
            
            if (!world.isAirBlock(x1, y1, z1))
                continue;
            
            final int i1 = 1 + rand.nextInt(rand.nextInt(3) + 1);
            
            for (int y2 = 0; y2 < i1; y2++)
                if (block.canBlockStay(world, x1,
                        y1 + y2, z1))
                    world.setBlock(x1, y1 + y2, z1, block,
                            metadata, 2);
        }
        
        return true;
    }
}
