package extrabiomes.plugins.forestry;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.forestry.api.cultivation.ICropEntity;
import extrabiomes.api.TerrainGenManager;

public class CropSapling implements ICropEntity {

	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private int blockid;

	public CropSapling(World world, int x, int y, int z) {
		this.world = world;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		this.blockid = world.getBlockId(x, y, z);
	}

	@Override
	public ArrayList<ItemStack> doHarvest() {
		ArrayList harvest = Block.blocksList[blockid].getBlockDropped(world,
				xCoord, yCoord, zCoord, 0, 0);
		world.setBlockAndMetadataWithNotify(xCoord, yCoord, zCoord, 0, 0);
		return harvest;
	}

	@Override
	public int[] getNextPosition() {
		int[] next = null;

		int count = 1;
		int blockid = world.getBlockId(xCoord, yCoord + count, zCoord);
		while (Block.blocksList[blockid] != null
				&& Block.blocksList[blockid].isWood(world, xCoord, yCoord
						+ count, zCoord)) {
			next = new int[] { xCoord, yCoord + count, zCoord };
			count++;
			blockid = world.getBlockId(xCoord, yCoord + count, zCoord);
		}

		if (next != null) {
			return next;
		}
		count = -1;
		blockid = world.getBlockId(xCoord, yCoord + count, zCoord);
		while (Block.blocksList[blockid] != null
				&& Block.blocksList[blockid].isWood(world, xCoord, yCoord
						+ count, zCoord)) {
			next = new int[] { xCoord, yCoord + count, zCoord };
			count--;
			blockid = world.getBlockId(xCoord, yCoord + count, zCoord);
		}

		return next;
	}

	@Override
	public boolean isHarvestable() {
		int blockId = world.getBlockId(xCoord, yCoord, zCoord);
		return Block.blocksList[blockId] != null
				&& Block.blocksList[blockId].isWood(world, xCoord, yCoord,
						zCoord);
	}

}
