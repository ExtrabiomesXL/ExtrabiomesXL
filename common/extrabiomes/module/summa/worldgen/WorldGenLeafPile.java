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

class WorldGenLeafPile extends WorldGenerator
{
    
    private final int blockID;
    
    WorldGenLeafPile(int blockID)
    {
        this.blockID = blockID;
    }
    
    @Override
    public boolean generate(World world, Random rand, int x, int y,
            int z)
    {
        int i;
        while ((Block.blocksList[i = world.getBlockId(x, y, z)] == null || Block.blocksList[i]
                .isLeaves(world, x, y, z)) && y > 0)
            y--;
        
        for (int j = 0; j < 128; j++)
        {
            final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
            final int y1 = y + rand.nextInt(4) - rand.nextInt(4);
            final int z1 = z + rand.nextInt(8) - rand.nextInt(8);
            
            if (world.isAirBlock(x1, y1, z1)
                    && Block.blocksList[blockID].canBlockStay(world,
                            x1, y1, z1))
                world.setBlock(x1, y1, z1, blockID);
        }
        
        return true;
    }
}
