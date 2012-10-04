/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.tool;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import extrabiomes.api.ITurnableLog;

public class LogTurner extends Item {

	public LogTurner(int id) {
		super(id);
		setIconIndex(112);
		setMaxStackSize(1);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public boolean isItemTool(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX,
			float hitY, float hitZ)
	{
		final int blockID = world.getBlockId(x, y, z);
		if (blockID == 0 || Block.blocksList[blockID] == null
				|| blockID != Block.wood.blockID
				&& !(Block.blocksList[blockID] instanceof ITurnableLog))
			return super.onItemUseFirst(stack, player, world, x, y, z,
					side, hitX, hitY, hitZ);

		if (blockID == Block.wood.blockID) {
			final int metadata = world.getBlockMetadata(x, y, z);
			int orientation = metadata & 12;
			final int type = metadata & 3;

			orientation = orientation == 0 ? 4 : orientation == 4 ? 8
					: 0;
			world.setBlockAndMetadata(x, y, z, blockID, type
					| orientation);
		} else
			((ITurnableLog) Block.blocksList[blockID]).onLogTurner(
					world, x, y, z);

		return true;
	}
}
