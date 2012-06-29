
package extrabiomes.terrain;

import java.util.Random;

import org.bukkit.BlockChangeDelegate;

import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import extrabiomes.api.TerrainGenManager;

public class WorldGenFirTree extends WorldGenerator {
	final int	blockLeaf;
	final int	metaLeaf;
	final int	blockWood;
	final int	metaWood;

	public WorldGenFirTree(boolean flag) {
		super(flag);
		blockLeaf = TerrainGenManager.blockFirLeaves.id;
		metaLeaf = TerrainGenManager.metaFirLeaves;
		blockWood = TerrainGenManager.blockFirWood.id;
		metaWood = TerrainGenManager.metaFirWood;
	}

	@Override
	public boolean a(World arg0, Random arg1, int arg2, int arg3,
			int arg4)
	{
		return generate((BlockChangeDelegate) arg0, arg1, arg2, arg3,
				arg4);
	}

	public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k) {
		final int l = world.getTypeId(i, j - 1, k);
		final int i1 = random.nextInt(8) + 24;

		if (!TerrainGenManager.treesCanGrowOnIDs.contains(Integer
				.valueOf(l)) || j >= 256 - i1 - 1) return false;

		if (j < 1 || j + i1 + 1 > 256) return false;

		final int j1 = 1 + random.nextInt(12);
		final int k1 = 2 + random.nextInt(6);

		for (int l1 = j; l1 <= j + 1 + i1; l1++) {
			if (l1 < 0 || l1 >= 256) return false;

			int j2 = 1;

			if (l1 - j < j1)
				j2 = 0;
			else
				j2 = k1;

			for (int l2 = i - j2; l2 <= i + j2; l2++)
				for (int i3 = k - j2; i3 <= k + j2; i3++) {
					final int l3 = world.getTypeId(l2, l1, i3);

					if (Block.byId[l3] != null
							&& !Block.byId[l3].isLeaves(world, l2, l1,
									i3)) return false;
				}
		}

		world.setRawTypeId(i, j - 1, k, Block.DIRT.id);
		int i2 = random.nextInt(2);
		int k2 = 1;
		boolean flag = false;

		for (int j3 = 0; j3 <= i1 - j1; j3++) {
			final int i4 = j + i1 - j3;

			for (int k4 = i - i2; k4 <= i + i2; k4++) {
				final int i5 = k4 - i;

				for (int j5 = k - i2; j5 <= k + i2; j5++) {
					final int k5 = j5 - k;
					final Block block = Block.byId[world.getTypeId(k4,
							i4, j5)];

					if ((Math.abs(i5) != i2 || Math.abs(k5) != i2 || i2 <= 0)
							&& (block == null || block
									.canBeReplacedByLeaves(world, k4,
											i4, j5)))
						setTypeAndData(world, k4, i4, j5, blockLeaf,
								metaLeaf);
				}
			}

			if (i2 >= k2) {
				i2 = flag ? 1 : 0;
				flag = true;

				if (++k2 > k1) k2 = k1;
			} else
				i2++;
		}

		final int k3 = random.nextInt(3);

		for (int j4 = 0; j4 < i1 - k3; j4++) {
			final int l4 = world.getTypeId(i, j + j4, k);

			if (Block.byId[l4] == null
					|| Block.byId[l4].isLeaves(world, i, j + j4, k))
				setTypeAndData(world, i, j + j4, k, blockWood, metaWood);
		}

		return true;
	}
}
