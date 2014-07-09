package extrabiomes.subblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class SubBlock {

	protected String textureName;
	protected IIcon texture;
	
	protected int metaData = 0;
	protected int parentId = 0;
	
	public SubBlock(String name) {
		textureName = name;
	}
	
	public String getUnlocalizedName() {
		return textureName;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIIcons(IIconRegister IIconRegister) {
        texture = IIconRegister.registerIIcon(Extrabiomes.TEXTURE_PATH + textureName);
    }
    
    public IIcon getIIcon(int side, int metaData) {
        return texture;
    }
    
    public boolean canBlockStay(World world, int x, int y, int z) {
        return canPlaceBlockAt(world, x, y, z);
    }
    
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return true;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, int idNeighbor, Block block) {
        if (!canBlockStay(world, x, y, z)) {
        	block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, 0);
        }
    }
    
    public void setBlockIds(int id, int data) {
    	parentId = id;
    	metaData = data;
    }
}
