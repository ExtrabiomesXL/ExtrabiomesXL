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
    	for(int y1 = y+1; y1 < y + height; y1++) {
    		if(Block.blocksList[world.getBlockId(x, y1, z)] != null) return false;
    	}
    	
    	// Place the wood blocks
    	for(int y1 = y; y1 < y + height; y1++) {
    		setBlockAndMetadata(world, x, y1, z, logs.itemID, logs.getItemDamage());
    	}
    	
    	return true;
    }
    
    public boolean place2x2Trunk(int x, int y, int z, int height, ItemStack logs, World world) {
    	for(int y1 = y+1; y1 < y + height; y1++) {
    		if(Block.blocksList[world.getBlockId(x, y1, z)] != null) return false;
    		if(Block.blocksList[world.getBlockId(x+1, y1, z)] != null) return false;
    		if(Block.blocksList[world.getBlockId(x, y1, z+1)] != null) return false;
    		if(Block.blocksList[world.getBlockId(x+1, y1, z+1)] != null) return false;
    	}
    	
    	for(int y1 = y; y1 < y + height; y1++) {
    		setBlockAndMetadata(world, x, y1, z, logs.itemID, 0);
    		setBlockAndMetadata(world, x+1, y1, z, logs.itemID, 1);
    		setBlockAndMetadata(world, x, y1, z+1, logs.itemID, 3);
    		setBlockAndMetadata(world, x+1, y1, z+1, logs.itemID, 2);
    	}
    	
    	return true;
    }
    
    public boolean placeKnee(int x, int y, int z, int height, int direction, ItemStack logs, ItemStack knees, World world) {
    	if(direction > 3) return false;
    	
    	int orientation = 0;
    	
    	switch(direction) {
    		case 0:
    			orientation = 4;
    			break;
    		case 1:
    			orientation = 9;
    			break;
    		case 2:
    			orientation = 5;
    			break;
    		case 3:
    			orientation = 8;
    			break;
    		default:
    			break;
    	}
    	
    	for(int y1 = y; y1 < y + height; y1++){
			//if(Block.blocksList[world.getBlockId(x, y1, z)] != null) return false;
		}
		
		for(int y1 = y; y1 < y + height - 1; y1++){
			setBlockAndMetadata(world, x, y1, z, logs.itemID, logs.getItemDamage());
		}
		
		// Place the knee on top
		setBlockAndMetadata(world, x, y + height -1, z, knees.itemID, orientation);
    	
    	return true;
    }
    
    public boolean placeBlockLine(int[] start, int[] end, ItemStack logs, World world) {
    	if(start.length != 3 || end.length != 3) return false;
    	
    	// Get the direction vector
    	int[] direction = {
    		start[0] - end[0],
    		start[1] - end[1],
    		start[2] - end[2]
    	};
    	int inc = 1;
    	
    	
    	if(Math.abs(direction[2]) > Math.abs(direction[1]) && Math.abs(direction[2]) > Math.abs(direction[0])) {
    		// We are going to use the y axis as our major axis
    		if(direction[2] >= 0){
	    		for (int z = start[2]; z >= end[2]; z--){
	    			double m = (z - start[2]) / (double)direction[2];
	    			int x = (int)(start[0] + (direction[0] * m));
	    			int y = (int)(start[1] + (direction[1] * m));
	    			if(Block.blocksList[world.getBlockId(x, y, z)] == null) setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 8);
	    		}
    		} else {
	    		for (int z = start[2]; z <= end[2]; z++){
	    			double m = (z - start[2]) / (double)direction[2];
	    			int x = (int)(start[0] + (direction[0] * m));
	    			int y = (int)(start[1] + (direction[1] * m));
	    			if(Block.blocksList[world.getBlockId(x, y, z)] == null) setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 8);
	    		}
    		}
    	} else if (Math.abs(direction[0]) > Math.abs(direction[1])) {
    		// Treverse along the x axis
    		if(direction[0] >= 0){
	    		for (int x = start[0]; x >= end[0]; x--){
	    			double m = (x - start[0]) / (double)direction[0];
	    			int z = (int)(start[2] + (direction[2] * m));
	    			int y = (int)(start[1] + (direction[1] * m));
	    			if(Block.blocksList[world.getBlockId(x, y, z)] == null) setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 4);
	    		}
    		} else {
	    		for (int x = start[0]; x <= end[0]; x++){
	    			double m = (x - start[0]) / (double)direction[0];
	    			int z = (int)(start[2] + (direction[2] * m));
	    			int y = (int)(start[1] + (direction[1] * m));
	    			if(Block.blocksList[world.getBlockId(x, y, z)] == null) setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 4);
	    		}
    		}
    	} else {
    		// We will use the y axis as our major axis
    		if(direction[1] >= 0) {
	    		for (int y = start[1]; y >= end[1]; y--){
	    			double m = (y - start[1]) / (double)direction[1];
	    			int x = (int)(start[0] + (direction[0] * m));
	    			int z = (int)(start[2] + (direction[2] * m));
	    			if(Block.blocksList[world.getBlockId(x, y, z)] == null) setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage());
	    		}
    		} else {
	    		for (int y = start[1]; y <= end[1]; y++){
	    			double m = (y - start[1]) / (double)direction[1];
	    			int x = (int)(start[0] + (direction[0] * m));
	    			int z = (int)(start[2] + (direction[2] * m));
	    			if(Block.blocksList[world.getBlockId(x, y, z)] == null) setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage());
	    		}
    		}
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
				}
			}
		}
	}
}