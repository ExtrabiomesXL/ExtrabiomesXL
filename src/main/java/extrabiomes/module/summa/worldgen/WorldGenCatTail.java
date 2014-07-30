/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenCatTail extends WorldGenerator
{
    private final Block block;
    
    WorldGenCatTail(Block block)
    {
        this.block = block;
    }
    
    @Override
    public boolean generate(World world, Random rand, int x, int y,
            int z)
    {
        Block b = world.getBlock(x, y, z);
        while(y > 0 && (b == null || b.isLeaves(world, x, y, z) || b.isAir(world, x, y, z))) {
            y--;
            b = world.getBlock(x, y, z);
        }
        
        y++;
        
        for (int l = 0; l < 20; l++)
        {
            final int x1 = x + rand.nextInt(4) - rand.nextInt(4);
            final int z1 = z + rand.nextInt(4) - rand.nextInt(4);
            if (!world.isAirBlock(x1, y, z1)
                    || world.getBlock(x1 - 1, y - 1, z1).getMaterial() != Material.water
                    && world.getBlock(x1 + 1, y - 1, z1).getMaterial() != Material.water
                    && world.getBlock(x1, y - 1, z1 - 1).getMaterial() != Material.water
                    && world.getBlock(x1, y - 1, z1 + 1).getMaterial() != Material.water)
                continue;
            
            final int i1 = 1 + rand.nextInt(rand.nextInt(1) + 1);
            
            for (int i = 0; i < i1; i++)
                if (block.canBlockStay(world, x1,
                        y + i, z1))
                    world.setBlock(x1, y + i, z1, block);
        }
        
        return true;
    }
    
}
