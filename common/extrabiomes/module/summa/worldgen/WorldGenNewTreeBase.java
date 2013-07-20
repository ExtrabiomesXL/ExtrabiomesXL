/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import extrabiomes.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenNewTreeBase extends WorldGenerator {

    public WorldGenNewTreeBase(boolean par1) {
        super(par1);
    }
    
    public boolean place1x1Trunk(int x, int y, int z, int height, ItemStack logs, World world){
    	// make sure that we can place the trunk
    	for(int y1 = y; y1 < y + height; y1++){
    		if(Block.blocksList[world.getBlockId(x, y1, z)] != null) return false;
    	}
    	
    	// Place the wood blocks
    	for(int y1 = y; y1 < y + height; y1++){
    		setBlockAndMetadata(world, x, y1, z, logs.itemID, logs.getItemDamage());
    	}
    	
    	return true;
    }
	
	public void placeLeavesCircle(double x, int y, double z, double r, ItemStack leaves, World world){
		double dist = r * r;
		
		for(double z1 = Math.floor(-r); z1 < r + 1; z1++){
			for(double x1 = Math.floor(-r); x1 < r + 1; x1++){
				int x2 = (int)(x1 + x);
				int z2 = (int)(z1 + z);
				
				final Block block = Block.blocksList[world.getBlockId(x2, y, z2)];
				
				if((((x1*x1) + (z1*z1)) <= dist) && (block == null || block.canBeReplacedByLeaves(world, x2, y, z2))){
					setBlockAndMetadata(world, x2, y, z2, leaves.itemID, leaves.getItemDamage());
				} else {
					//setBlockAndMetadata(world, x2, y, z2, 17, 0);
				}
			}
		}
	}
}