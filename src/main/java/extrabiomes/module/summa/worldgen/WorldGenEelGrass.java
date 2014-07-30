/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import extrabiomes.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenEelGrass extends WorldGenerator
{
    
    private final Block block;
    private final int metaData;
    
    WorldGenEelGrass(Block block, int metaData) {
        this.block = block;
        this.metaData = metaData;
    }
    
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int maxDepth = 4;
        while (world.isAirBlock(x, y, z) && y > 0) {
            y--;
        }

        Block b = world.getBlock(x, y, z);
        while((b.equals(Blocks.water) || b.equals(Blocks.flowing_water)) && y > 0 && maxDepth > 0) {
        	y--;
        	maxDepth--;
            b = world.getBlock(x, y, z);
        }
        
        y++;
        
        if (block.canBlockStay(world, x, y, z)) {
        	world.setBlock(x, y, z, block);
        } 

        return true;
    }
    
}
