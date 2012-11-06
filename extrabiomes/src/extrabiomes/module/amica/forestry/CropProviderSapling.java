/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.forestry;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import extrabiomes.api.Stuff;
import extrabiomes.module.summa.block.BlockCustomSapling;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;

public class CropProviderSapling implements ICropProvider {

	@Override
	public boolean isGermling(ItemStack germling) {
		return germling.itemID == Stuff.sapling.get().blockID && germling.getItemDamage() != BlockCustomSapling.BlockType.REDWOOD.metadata();
	}

	@Override
	public boolean isCrop(World world, int x, int y, int z) {
		int id = world.getBlockId(x, y, z);
		return id == 0 || Block.blocksList[id] == null ? false : Block.blocksList[id]
						.isWood(world, x, y, z);
	}

	@Override
	public ItemStack[] getWindfall() {
		ArrayList windfall = new ArrayList();
		for (BlockCustomSapling.BlockType type: BlockCustomSapling.BlockType.values()) {
			windfall.add(new ItemStack(Stuff.sapling.get(),1,type.metadata()));
		}
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
				Stuff.sapling.get().blockID, germling.getItemDamage());
		return true;
	}

	@Override
	public ICropEntity getCrop(World world, int x, int y, int z) {
		   return new CropSapling(world, x, y, z);
	}

}
