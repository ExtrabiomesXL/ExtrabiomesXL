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
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenEelGrass extends WorldGenerator
{
    
    private final int blockID;
    private final int metaData;
    
    WorldGenEelGrass(int blockID, int metaData) {
        this = blockID;
        this.metaData = metaData;
    }
    
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int i;
        int maxDepth = 4;
        while ((Block.blocksList[i = world.getBlock(x, y, z)] == null) && y > 0) {
            y--;
        }
        
        while(((i = world.getBlock(x, y, z)) == Block.waterMoving || i == Block.waterStill) && y > 0 && maxDepth > 0) {
        	y--;
        	maxDepth--;
        }
        
        y++;
        
        if (Block.blocksList[blockID].canBlockStay(world, x, y, z)) {
        	world.setBlock(x, y, z, blockID);
        } 

        return true;
    }
    
}
