
package extrabiomes.terrain;

import java.util.Random;

import org.bukkit.BlockChangeDelegate;

import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import extrabiomes.api.TerrainGenManager;

public class WorldGenFirTree2 extends WorldGenerator {
	private static void setBlockandMetadataIfChunkExists(World world,
			int i, int j, int k, int l, int i1)
	{
		if (world.q().isChunkLoaded(i >> 4, k >> 4))
			world.setRawTypeIdAndData(i, j, k, l, i1);
	}

	final int	blockLeaf;
	final int	metaLeaf;
	final int	blockWood;

	final int	metaWood;

	public WorldGenFirTree2(boolean flag) {
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
		final int l = random.nextInt(16) + 32;
		final int i1 = 1 + random.nextInt(12);
		final int j1 = l - i1;
		final int k1 = 2 + random.nextInt(9);

		if (j < 1 || j + l + 1 > 256) return false;

		for (int l1 = j; l1 <= j + 1 + l; l1++) {
			if (l1 < 0 && l1 >= 256) return false;

			int j2 = 1;

			if (l1 - j < i1)
				j2 = 0;
			else
				j2 = k1;

			for (int l2 = i - j2; l2 <= i + j2; l2++)
				for (int j3 = k - j2; j3 <= k + j2; j3++) {
					if (!((World) world).q().isChunkLoaded(l2 >> 4, j3 >> 4))
						return false;

					final int k3 = world.getTypeId(l2, l1, j3);

					if (Block.byId[k3] != null
							&& !Block.byId[k3].isLeaves((World)null, l2, l1,
									j3)) return false;
				}
		}

		final int i2 = world.getTypeId(i, j - 1, k);

		if (!TerrainGenManager.treesCanGrowOnIDs.contains(Integer
				.valueOf(i2)) || j >= 256 - l - 1) return false;

		world.setRawTypeId(i, j - 1, k, Block.DIRT.id);
		world.setRawTypeId(i - 1, j - 1, k, Block.DIRT.id);
		world.setRawTypeId(i, j - 1, k - 1, Block.DIRT.id);
		world.setRawTypeId(i - 1, j - 1, k - 1, Block.DIRT.id);
		int k2 = random.nextInt(2);
		int i3 = 1;
		boolean flag = false;

		for (int l3 = 0; l3 <= j1; l3++) {
			final int j4 = j + l - l3;

			for (int l4 = i - k2; l4 <= i + k2; l4++) {
				final int j5 = l4 - i;

				for (int k5 = k - k2; k5 <= k + k2; k5++) {
					final int l5 = k5 - k;
					final Block block = Block.byId[world.getTypeId(l4,
							j4, k5)];

					if ((Math.abs(j5) != k2 || Math.abs(l5) != k2 || k2 <= 0)
							&& (block == null || block
									.canBeReplacedByLeaves(world, l4,
											j4, k5)))
					{
						setBlockandMetadataIfChunkExists((World) world, l4, j4,
								k5, blockLeaf, metaLeaf);
						setBlockandMetadataIfChunkExists((World) world, l4 - 1,
								j4, k5, blockLeaf, metaLeaf);
						setBlockandMetadataIfChunkExists((World) world, l4, j4,
								k5 - 1, blockLeaf, metaLeaf);
						setBlockandMetadataIfChunkExists((World) world, l4 - 1,
								j4, k5 - 1, blockLeaf, metaLeaf);
					}
				}
			}

			if (k2 >= i3) {
				k2 = flag ? 1 : 0;
				flag = true;

				if (++i3 > k1) i3 = k1;
			} else
				k2++;
		}

		final int i4 = random.nextInt(3);

		for (int k4 = 0; k4 < l - i4; k4++) {
			final int i5 = world.getTypeId(i, j + k4, k);

			if (Block.byId[i5] == null
					|| Block.byId[i5].isLeaves(world, i, j + k4, k))
			{
				setTypeAndData(world, i, j + k4, k, blockWood, metaWood);
				setTypeAndData(world, i - 1, j + k4, k, blockWood,
						metaWood);
				setTypeAndData(world, i, j + k4, k - 1, blockWood,
						metaWood);
				setTypeAndData(world, i - 1, j + k4, k - 1, blockWood,
						metaWood);
			}
		}

		return true;
	}
}
