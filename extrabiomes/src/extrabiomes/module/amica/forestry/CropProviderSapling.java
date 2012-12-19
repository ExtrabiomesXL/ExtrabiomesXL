/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.forestry;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Lists;

import extrabiomes.helpers.ForestryModHelper;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;

public class CropProviderSapling implements ICropProvider {

	@Override
	public boolean isGermling(ItemStack germling) {
		return ForestryModHelper.isGermling(germling);
	}

	@Override
	public boolean isCrop(World world, int x, int y, int z) {
		int id = world.getBlockId(x, y, z);
		return id == 0 || Block.blocksList[id] == null ? false : Block.blocksList[id]
						.isWood(world, x, y, z);
	}

	@Override
	public ItemStack[] getWindfall() {
		ArrayList windfall = Lists.newArrayList(ForestryModHelper.getSaplings());

		for (Object item : ForestryPlugin.loggerWindfall) {
			windfall.add(item);
		}
		return (ItemStack[]) windfall.toArray(new ItemStack[0]);
	}

	private static int forestrySoilBlockID = 0;
	private static int forestrySoilID() {
		if (forestrySoilBlockID == 0) {
			forestrySoilBlockID = ForestryPlugin.getBlock("soil").itemID;
		}
		return forestrySoilBlockID;
	}
	
	@Override
	public boolean doPlant(ItemStack germling, World world, int x,
			int y, int z)
	{
		int blockid = world.getBlockId(x, y, z);

		if (blockid != 0) {
			return false;
		}
		if (!isGermling(germling))
			return false;

		int below = world.getBlockId(x, y - 1, z);
		int meta = world.getBlockMetadata(x, y - 1, z);
		if (below != forestrySoilID() || (meta & 0x3) != 0) {
			return false;
		}
		world.setBlockAndMetadataWithNotify(x, y, z,
				germling.itemID, germling.getItemDamage());
		return true;
	}

	@Override
	public ICropEntity getCrop(World world, int x, int y, int z) {
		   return new CropSapling(world, x, y, z);
	}

}
