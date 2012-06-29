
package extrabiomes.plugins.forestry;

import java.util.ArrayList;

import net.minecraft.server.World;
import net.minecraft.server.Block;
import forestry.api.cultivation.ICropEntity;

public class CropSapling implements ICropEntity {
	private final World	world;
	private final int	xCoord;
	private final int	yCoord;
	private final int	zCoord;
	private final int	blockid;

	public CropSapling(World world1, int i, int j, int k) {
		world = world1;
		xCoord = i;
		yCoord = j;
		zCoord = k;
		blockid = world1.getTypeId(i, j, k);
	}

	@Override
	public ArrayList doHarvest() {
		final ArrayList arraylist = Block.byId[blockid]
				.getBlockDropped(world, xCoord, yCoord, zCoord, 0, 0);
		world.setTypeIdAndData(xCoord, yCoord, zCoord, 0, 0);
		return arraylist;
	}

	@Override
	public int[] getNextPosition() {
		int ai[] = null;
		int i = 1;

		for (int j = world.getTypeId(xCoord, yCoord + i, zCoord); Block.byId[j] != null
				&& Block.byId[j].isWood(world, xCoord, yCoord + i,
						zCoord); j = world.getTypeId(xCoord,
				yCoord + i, zCoord))
		{
			ai = new int[] { xCoord, yCoord + i, zCoord };
			i++;
		}

		if (ai != null) return ai;

		i = -1;

		for (int k = world.getTypeId(xCoord, yCoord + i, zCoord); Block.byId[k] != null
				&& Block.byId[k].isWood(world, xCoord, yCoord + i,
						zCoord); k = world.getTypeId(xCoord,
				yCoord + i, zCoord))
		{
			ai = new int[] { xCoord, yCoord + i, zCoord };
			i--;
		}

		return ai;
	}

	@Override
	public boolean isHarvestable() {
		final int i = world.getTypeId(xCoord, yCoord, zCoord);
		return Block.byId[i] != null
				&& Block.byId[i].isWood(world, xCoord, yCoord, zCoord);
	}
}
