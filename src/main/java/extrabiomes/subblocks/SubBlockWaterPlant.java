package extrabiomes.subblocks;

import java.util.LinkedList;
import java.util.List;

import extrabiomes.api.events.GetBiomeIDEvent;
import extrabiomes.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class SubBlockWaterPlant extends SubBlock {
	private int maxHeight;
	
	private List<Block> groundBlocks;

	/**
     * Creates an underwater plant subblock with a max height of one block.
     * 
     * @param Name A string name of the texture/unlocalized block name.
     */
	public SubBlockWaterPlant(String name) {
		this(name, 1);
	}

	/**
     * Creates an underwater plant subblock with a user defined max
     * block height.
     * 
     * @param Name A string name of the texture/unlocalized block name.
     * @param Height An integer specify the maxt height that this plant
     * can grow to.
     */
	public SubBlockWaterPlant(String name, int height) {
		super(name);
		maxHeight = height;
		
		groundBlocks = new LinkedList<Block>();
	
		// Add dirt by default
		groundBlocks.add(Blocks.dirt);
	}
	
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
    	Block base;
    	int offset = 1;
    	final Block top = world.getBlock(x, y + 1, z);
    	//final int curId = world.getBlock(x, y, z);
        
    	// We need to be under water
    	if(maxHeight > 1 && top.equals(parent) && this.metaData == world.getBlockMetadata(x, y + 1, z)){
    		
    	} else if(!top.equals(Blocks.water)) { 
    		return false;
    	}
    	
    	while(maxHeight >= offset) {
    		base = world.getBlock(x, y - offset, z);
    		if(groundBlocks.isEmpty() || groundBlocks.contains((Object)base)) {
    			return true;
    		} else if(!base.equals(parent) || this.metaData != world.getBlockMetadata(x, y - offset, z)) {
    			return false;
    		}
    		
    		offset++;
    	}
    	
    	//LogHelper.info("" + offset);
        return false;
    }

	@Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor, Block block) {
    	if (!neighbor.equals(parent) && !canBlockStay(world, x, y, z)) {
        	world.setBlock(x, y, z, Blocks.water);
        	block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        }
    }
	
	public SubBlockWaterPlant addPlaceableBlock(Block newBlock) {
		groundBlocks.add(newBlock);
		
		return this;
	}
	
	public SubBlockWaterPlant removePlaceableBlock(Block newBlock) {
		groundBlocks.remove(newBlock);
		
		return this;
	}
}
