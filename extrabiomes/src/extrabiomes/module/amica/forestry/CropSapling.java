/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.forestry;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import forestry.api.cultivation.ICropEntity;

public class CropSapling implements ICropEntity {

	private final World	world;
	private final int	x;
	private final int	y;
	private final int	z;
	private final int	blockID;
	private final int	metadata;

	public CropSapling(World world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		blockID = world.getBlockId(x, y, z);
		metadata = world.getBlockMetadata(x, y, z);
	}

	@Override
	public ArrayList doHarvest() {
		final ArrayList harvest = Block.blocksList[blockID]
				.getBlockDropped(world, x, y, z, metadata, 0);
		world.setBlockAndMetadataWithNotify(x, y, z, 0, 0);
		return harvest;
	}

	@Override
	public int[] getNextPosition() {
		int[] next = null;

		int count = 1;
		int blockid = world.getBlockId(x, y + count, z);
		while (Block.blocksList[blockid] != null
				&& Block.blocksList[blockid].isWood(world, x,
						y + count, z))
		{
			next = new int[] { x, y + count, z };
			count++;
			blockid = world.getBlockId(x, y + count, z);
		}

		if (next != null) return next;
		count = -1;
		blockid = world.getBlockId(x, y + count, z);
		while (Block.blocksList[blockid] != null
				&& Block.blocksList[blockid].isWood(world, x,
						y + count, z))
		{
			next = new int[] { x, y + count, z };
			count--;
			blockid = world.getBlockId(x, y + count, z);
		}

		return next;
	}

	@Override
	public boolean isHarvestable() {
		final int blockId = world.getBlockId(x, y, z);
		return Block.blocksList[blockId] != null
				&& Block.blocksList[blockId].isWood(world, x, y, z);
	}

}
