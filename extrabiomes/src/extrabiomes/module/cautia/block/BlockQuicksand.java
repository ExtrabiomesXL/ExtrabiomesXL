/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import extrabiomes.Extrabiomes;

class BlockQuicksand extends Block {

	BlockQuicksand(int id) {
		super(id, 1, Material.sand);
		setHardness(4.0F);
		setStepSound(Block.soundSandFootstep);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(Extrabiomes.tabsEBXL);
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
