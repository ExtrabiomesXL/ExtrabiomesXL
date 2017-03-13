package extrabiomes.subblocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SubBlock {

	protected String textureName;
	
	protected IBlockState parent = null;
	
	public SubBlock(String name) {
		textureName = name;
	}
	
	public String getUnlocalizedName() {
		return textureName;
	}
    
    public boolean canBlockStay(World world, BlockPos pos) {
        return canPlaceBlockAt(world, pos);
    }
    
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return true;
    }

    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState neighbor, IBlockState block) {
        if (!canBlockStay(world, pos)) {
        	block.getBlock().dropBlockAsItem(world, pos, block, 0);
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }
    
    public void setBlock(IBlockState block) {
    	parent = block;
    }
}
