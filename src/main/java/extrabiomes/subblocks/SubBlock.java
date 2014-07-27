package extrabiomes.subblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class SubBlock {

	protected String textureName;
	protected IIcon texture;
	
	protected int metaData = 0;
	protected Block parent = null;
	
	public SubBlock(String name) {
		textureName = name;
	}
	
	public String getUnlocalizedName() {
		return textureName;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + textureName);
    }
    
    public IIcon getIcon(int side, int metaData) {
        return texture;
    }
    
    public boolean canBlockStay(World world, int x, int y, int z) {
        return canPlaceBlockAt(world, x, y, z);
    }
    
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return true;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor, Block block) {
        if (!canBlockStay(world, x, y, z)) {
        	block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, Blocks.air);
        }
    }
    
    public void setBlock(Block block, int data) {
    	parent = block;
    	metaData = data;
    }
}
