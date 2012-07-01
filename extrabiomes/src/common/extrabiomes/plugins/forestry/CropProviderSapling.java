package extrabiomes.plugins.forestry;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import forestry.api.core.ForestryAPI;
import forestry.api.core.ForestryBlock;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.TerrainGenManager;

public class CropProviderSapling implements ICropProvider {

	@Override
	public boolean doPlant(ItemStack germling, World world, int x, int y, int z) {
		int blockid = world.getBlockId(x, y, z);

		if (blockid != 0) {
			return false;
		}

		int below = world.getBlockId(x, y - 1, z);
		int meta = world.getBlockMetadata(x, y - 1, z);
		if (below != ForestryBlock.soil.blockID || (meta & 0x3) != 0) {
			return false;
		}
		world.setBlockAndMetadataWithNotify(x, y, z,
				ExtrabiomesBlock.sapling.blockID, 0);
		return true;
	}

	@Override
	public ICropEntity getCrop(World world, int x, int y, int z) {
		return new CropSapling(world, x, y, z);
	}

	@Override
	public ItemStack[] getWindfall() {
		ArrayList windfall = new ArrayList();
		windfall.add(new ItemStack(ExtrabiomesBlock.sapling));

		for (ItemStack fruit : ForestryAPI.loggerWindfall) {
			windfall.add(fruit);
		}
		return (ItemStack[]) windfall.toArray(new ItemStack[0]);
	}

	@Override
	public boolean isCrop(World world, int x, int y, int z) {
		int id = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		return id == ExtrabiomesBlock.sapling.blockID
				|| (Block.blocksList[id] != null && Block.blocksList[id]
						.isWood(world, x, y, z));
	}

	@Override
	public boolean isGermling(ItemStack germling) {
		return germling.itemID == ExtrabiomesBlock.sapling.blockID;
	}

}
