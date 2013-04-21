/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import extrabiomes.Extrabiomes;

class BlockQuicksand extends Block {

	BlockQuicksand(int id) {
		super(id, Material.sand);
		setHardness(4.0F);
		setStepSound(Block.soundSandFootstep);
		//setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(Extrabiomes.tabsEBXL);
	}
	
	private Icon texture;
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){
    	texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "quicksand");
    }
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return texture;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,
			int x, int y, int z)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y,
			int z, Entity entity)
	{
		entity.setInWeb();
	}
}
