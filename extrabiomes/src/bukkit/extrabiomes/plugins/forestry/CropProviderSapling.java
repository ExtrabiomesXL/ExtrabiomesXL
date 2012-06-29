
package extrabiomes.plugins.forestry;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;
import extrabiomes.api.ExtrabiomesBlock;
import forestry.api.core.ForestryAPI;
import forestry.api.core.ForestryBlock;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;

public class CropProviderSapling implements ICropProvider {
	public CropProviderSapling() {}

	public boolean doPlant(ItemStack itemstack, World world, int i,
			int j, int k)
	{
		final int l = world.getTypeId(i, j, k);

		if (l != 0) return false;

		final int i1 = world.getTypeId(i, j - 1, k);
		final int j1 = world.getData(i, j - 1, k);

		if (i1 != ForestryBlock.soil.id || (j1 & 3) != 0)
			return false;
		else {
			world.setTypeIdAndData(i, j, k,
					ExtrabiomesBlock.sapling.id, 0);
			return true;
		}
	}

	public ICropEntity getCrop(World world, int i, int j, int k) {
		return new CropSapling(world, i, j, k);
	}

	@Override
	public ItemStack[] getWindfall() {
		final ArrayList arraylist = new ArrayList();
		arraylist.add(new ItemStack(ExtrabiomesBlock.sapling));
		ItemStack itemstack;

		for (final Iterator iterator = ForestryAPI.loggerWindfall
				.iterator(); iterator.hasNext(); arraylist
				.add(itemstack))
			itemstack = (ItemStack) iterator.next();

		return (ItemStack[]) arraylist.toArray(new ItemStack[0]);
	}

	public boolean isCrop(World world, int i, int j, int k) {
		final int l = world.getTypeId(i, j, k);
		final int i1 = world.getData(i, j, k);
		return l == ExtrabiomesBlock.sapling.id
				|| Block.byId[l] != null
				&& Block.byId[l].isWood(world, i, j, k);
	}

	@Override
	public boolean isGermling(ItemStack itemstack) {
		return itemstack.id == ExtrabiomesBlock.sapling.id;
	}
}
