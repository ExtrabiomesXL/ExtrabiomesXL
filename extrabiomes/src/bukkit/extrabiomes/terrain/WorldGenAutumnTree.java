
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;

import org.bukkit.BlockChangeDelegate;

import extrabiomes.api.TerrainGenManager;

public class WorldGenAutumnTree extends WorldGenerator {
	private static boolean isBlockSuitableForGrowing(
			BlockChangeDelegate world, int i, int j, int k)
	{
		final int l = world.getTypeId(i, j, k);
		return TerrainGenManager.treesCanGrowOnIDs.contains(Integer
				.valueOf(l));
	}

	private static boolean isRoomToGrow(BlockChangeDelegate world,
			int i, int j, int k, int l)
	{
		for (int i1 = j; i1 <= j + 1 + l; i1++) {
			if (i1 < 0 || i1 >= 256) return false;

			byte byte0 = 1;

			if (i1 == j) byte0 = 0;

			if (i1 >= j + 1 + l - 2) byte0 = 2;

			for (int j1 = i - byte0; j1 <= i + byte0; j1++)
				for (int k1 = k - byte0; k1 <= k + byte0; k1++) {
					final int l1 = world.getTypeId(j1, i1, k1);

					if (Block.byId[l1] != null
							&& !Block.byId[l1].isLeaves(world, j1, i1,
									k1)
							&& l1 != Block.GRASS.id
							&& !Block.byId[l1]
									.isWood(world, j1, i1, k1))
						return false;
				}
		}

		return true;
	}

	private final ItemStack	leaf;

	private final ItemStack	wood;

	public WorldGenAutumnTree(boolean flag, ItemStack itemstack,
			ItemStack itemstack1)
	{
		super(flag);
		leaf = itemstack;
		wood = itemstack1;
	}

	@Override
	public boolean a(World arg0, Random arg1, int arg2, int arg3,
			int arg4)
	{
		return generate((BlockChangeDelegate) arg0, arg1, arg2, arg3,
				arg4);
	}

	public boolean generate(BlockChangeDelegate world, Random random,
			int i, int j, int k)
	{
		final int l = random.nextInt(3) + 4;

		if (j < 1 || j + l + 1 > 256) return false;

		if (!isBlockSuitableForGrowing(world, i, j - 1, k))
			return false;

		if (!isRoomToGrow(world, i, j, k, l))
			return false;
		else {
			world.setRawTypeId(i, j - 1, k, Block.DIRT.id);
			growLeaves(world, random, i, j, k, l);
			growTrunk(world, i, j, k, l);
			return true;
		}
	}

	private void growLeaves(BlockChangeDelegate world, Random random,
			int i, int j, int k, int l)
	{
		for (int i1 = j - 3 + l; i1 <= j + l; i1++) {
			final int j1 = i1 - (j + l);
			final int k1 = 1 - j1 / 2;

			for (int l1 = i - k1; l1 <= i + k1; l1++) {
				final int i2 = l1 - i;

				for (int j2 = k - k1; j2 <= k + k1; j2++) {
					final int k2 = j2 - k;
					final Block block = Block.byId[world.getTypeId(l1,
							i1, j2)];

					if ((Math.abs(i2) != k1 || Math.abs(k2) != k1 || random
							.nextInt(2) != 0 && j1 != 0)
							&& (block == null || block
									.canBeReplacedByLeaves(world, l1,
											i1, j2)))
						setTypeAndData(world, l1, i1, j2,
								leaf.getItem().id, leaf.getData());
				}
			}
		}
	}

	private void growTrunk(BlockChangeDelegate world, int i, int j,
			int k, int l)
	{
		for (int i1 = 0; i1 < l; i1++) {
			final int j1 = world.getTypeId(i, j + i1, k);

			if (Block.byId[j1] == null
					|| Block.byId[j1].isLeaves(world, i, j + i1, k))
				setTypeAndData(world, i, j + i1, k, wood.getItem().id,
						wood.getData());
		}
	}
}
