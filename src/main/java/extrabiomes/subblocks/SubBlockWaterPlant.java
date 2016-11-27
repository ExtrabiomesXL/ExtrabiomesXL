package extrabiomes.subblocks;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
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
		groundBlocks.add(Blocks.DIRT);
	}
	
    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
    	final IBlockState top = world.getBlockState(pos.up());
        
    	// We need to be under water
    	if(maxHeight > 1 && top.equals(parent)){
    		
    	} else if(top.getMaterial() != Material.WATER) { 
    		return false;
    	}
    	
    	int offset = 1;
    	IBlockState base;
    	while(maxHeight >= offset) {
    		base = world.getBlockState(pos.down(offset));
    		if(groundBlocks.isEmpty() || groundBlocks.contains(base.getBlock())) {
    			return true;
    		} else if(!base.equals(parent)) {
    			return false;
    		}
    		
    		offset++;
    	}
    	
    	//LogHelper.info("" + offset);
        return false;
    }

	@Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState neighbor, IBlockState block) {
    	if (!neighbor.equals(parent) && !canBlockStay(world, pos)) {
        	world.setBlockState(pos, Blocks.WATER.getDefaultState());
        	block.getBlock().dropBlockAsItem(world, pos, block, 0);
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
