
package extrabiomes.terrain;

import java.util.Random;

import org.bukkit.BlockChangeDelegate;

import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import extrabiomes.api.TerrainGenManager;

public class WorldGenRedwood extends WorldGenerator {
	final int	blockLeaf;
	final int	metaLeaf;
	final int	blockWood;
	final int	metaWood;

	public WorldGenRedwood(boolean flag) {
		super(flag);
		blockLeaf = TerrainGenManager.blockRedwoodLeaves.id;
		metaLeaf = TerrainGenManager.metaRedwoodLeaves;
		blockWood = TerrainGenManager.blockRedwoodWood.id;
		metaWood = TerrainGenManager.metaRedwoodWood;
	}

	@Override
	public boolean a(World arg0, Random arg1, int arg2, int arg3,
			int arg4)
	{
		return generate((BlockChangeDelegate) arg0, arg1, arg2, arg3,
				arg4);
	}

	public boolean generate(BlockChangeDelegate arg0, Random random, int i, int j, int k) {
		final int l = random.nextInt(8) + 24;
		final int i1 = 1 + random.nextInt(12);
		final int j1 = l - i1;
		final int k1 = 2 + random.nextInt(6);

		if (j < 1 || j + l + 1 > 256) return false;

		final int l1 = arg0.getTypeId(i, j - 1, k);

		if (!TerrainGenManager.treesCanGrowOnIDs.contains(Integer
				.valueOf(l1)) || j >= 256 - l - 1) return false;

		for (int i2 = j; i2 <= j + 1 + l; i2++) {
			int k2 = 1;

			if (i2 - j < i1)
				k2 = 0;
			else
				k2 = k1;

			for (int i3 = i - k2; i3 <= i + k2; i3++)
				for (int j3 = k - k2; j3 <= k + k2; j3++)
					if (i2 >= 0 && i2 < 256) {
						final int i4 = arg0.getTypeId(i3, i2, j3);

						if (Block.byId[i4] != null
								&& Block.byId[i4].isLeaves(arg0, i3,
										i2, j3)) return false;
					} else
						return false;
		}

		arg0.setRawTypeId(i, j - 1, k, Block.DIRT.id);
		arg0.setRawTypeId(i - 1, j - 1, k, Block.DIRT.id);
		arg0.setRawTypeId(i, j - 1, k - 1, Block.DIRT.id);
		arg0.setRawTypeId(i - 1, j - 1, k - 1, Block.DIRT.id);
		int j2 = random.nextInt(2);
		int l2 = 1;
		boolean flag = false;

		for (int k3 = 0; k3 <= j1; k3++) {
			final int j4 = j + l - k3;

			for (int l4 = i - j2; l4 <= i + j2; l4++) {
				final int j5 = l4 - i;

				for (int k5 = k - j2; k5 <= k + j2; k5++) {
					final int l5 = k5 - k;
					final Block block = Block.byId[arg0.getTypeId(l4,
							j4, k5)];

					if ((Math.abs(j5) != j2 || Math.abs(l5) != j2 || j2 <= 0)
							&& (block == null || block
									.canBeReplacedByLeaves(arg0, l4,
											j4, k5)))
					{
						setTypeAndData(arg0, l4, j4, k5, blockLeaf,
								metaLeaf);
						setTypeAndData(arg0, l4 - 1, j4, k5,
								blockLeaf, metaLeaf);
						setTypeAndData(arg0, l4, j4, k5 - 1,
								blockLeaf, metaLeaf);
						setTypeAndData(arg0, l4 - 1, j4, k5 - 1,
								blockLeaf, metaLeaf);
					}
				}
			}

			if (j2 >= l2) {
				j2 = flag ? 1 : 0;
				flag = true;

				if (++l2 > k1) l2 = k1;
			} else
				j2++;
		}

		final int l3 = random.nextInt(3);

		for (int k4 = 0; k4 < l - l3; k4++) {
			final int i5 = arg0.getTypeId(i, j + k4, k);

			if (Block.byId[i5] == null
					|| Block.byId[i5].isLeaves(arg0, i, j + k4, k))
			{
				setTypeAndData(arg0, i, j + k4, k, blockWood, metaWood);
				setTypeAndData(arg0, i, j + k4, k, blockWood, metaWood);
				setTypeAndData(arg0, i - 1, j + k4, k, blockWood,
						metaWood);
				setTypeAndData(arg0, i, j + k4, k - 1, blockWood,
						metaWood);
				setTypeAndData(arg0, i - 1, j + k4, k - 1, blockWood,
						metaWood);
			}
		}

		return true;
	}
}
