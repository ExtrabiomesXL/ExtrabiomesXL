/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.block;

import java.util.ArrayList;

import extrabiomes.Extrabiomes;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

class BlockQuicksand extends Block {

	BlockQuicksand(int id) {
		super(id, 1, Material.sand);
		setHardness(4.0F);
		setStepSound(Block.soundSandFootstep);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(Extrabiomes.extrabiomesTab);
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
