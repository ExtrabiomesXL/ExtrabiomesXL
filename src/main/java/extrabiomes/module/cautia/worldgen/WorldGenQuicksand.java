/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenQuicksand extends WorldGenerator
{
    
    private final int quicksandID;
    
    WorldGenQuicksand(int quicksandID)
    {
        this.quicksandID = quicksandID;
    }
    
    @Override
    public boolean generate(World world, Random rand, int x, int y,
            int z)
    {
        while ((world.isAirBlock(x, y, z) || !Block.isNormalCube(world
                .getBlockId(x, y, z))) && y > 2)
            y--;
        
        final int blockID = world.getBlock(x, y, z);
        if (blockID != Block.grass
                && blockID != Block.sand)
            return false;
        
        for (int x1 = -2; x1 <= 2; x1++)
            for (int z1 = -2; z1 <= 2; z1++)
                if (world.isAirBlock(x + x1, y - 1, z + z1)
                        && world.isAirBlock(x + x1, y - 2, z + z1))
                    return false;
        
        for (int x1 = -1; x1 <= 1; x1++)
            for (int z1 = -1; z1 <= 1; z1++)
                for (int y1 = -2; y1 <= 0; y1++)
                    world.setBlock(x + x1, y + y1, z + z1, quicksandID);
        
        return true;
    }
    
}
