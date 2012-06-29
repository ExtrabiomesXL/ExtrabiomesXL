
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;

import org.bukkit.BlockChangeDelegate;

import extrabiomes.api.TerrainGenManager;

public class WorldGenAcacia extends WorldGenerator {
	public WorldGenAcacia(boolean flag) {
		super(flag);
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
		final int l = random.nextInt(4) + 6;
		boolean flag = true;

		if (j < 1 || j + l + 1 > 256) return false;

		for (int i1 = j; i1 <= j + 1 + l; i1++) {
			byte byte1 = 1;

			if (i1 == j) byte1 = 0;

			if (i1 >= j + 1 + l - 2) byte1 = 2;

			for (int k1 = i - byte1; k1 <= i + byte1 && flag; k1++)
				for (int j2 = k - byte1; j2 <= k + byte1 && flag; j2++)
					if (i1 >= 0 && i1 < 256) {
						final int i3 = world.getTypeId(k1, i1, j2);

						if (Block.byId[i3] != null
								&& !Block.byId[i3].isLeaves(world, k1,
										i1, j2)
								&& i3 != Block.GRASS.id
								&& i3 != Block.DIRT.id
								&& !Block.byId[i3].isWood(world, k1,
										i1, j2)) flag = false;
					} else
						flag = false;
		}

		if (!flag) return false;

		if (!TerrainGenManager.treesCanGrowOnIDs.contains(Integer
				.valueOf(world.getTypeId(i, j - 1, k)))
				|| j >= 256 - l - 1) return false;

		world.setRawTypeId(i, j - 1, k, Block.DIRT.id);
		final byte byte0 = 3;
		final int j1 = 0;

		for (int l1 = j - byte0 + l; l1 <= j + l; l1++) {
			final int k2 = l1 - (j + l);
			final int j3 = j1 + 1 - k2;

			for (int k3 = i - j3; k3 <= i + j3; k3++) {
				final int l3 = k3 - i;

				for (int i4 = k - j3; i4 <= k + j3; i4++) {
					final int j4 = i4 - k;
					final Block block = Block.byId[world.getTypeId(k3,
							l1, i4)];

					if ((Math.abs(l3) != j3 || Math.abs(j4) != j3 || random
							.nextInt(2) != 0 && k2 != 0)
							&& (block == null || block
									.canBeReplacedByLeaves(world, k3,
											l1, i4)))
						setTypeAndData(world, k3, l1, i4,
								TerrainGenManager.blockAcaciaLeaves.id,
								TerrainGenManager.metaAcaciaLeaves);
				}
			}
		}

		for (int i2 = 0; i2 < l; i2++) {
			final int l2 = world.getTypeId(i, j + i2, k);

			if (Block.byId[l2] == null
					|| Block.byId[l2].isLeaves(world, i, j + i2, k))
				setTypeAndData(world, i, j + i2, k,
						TerrainGenManager.blockAcaciaWood.id,
						TerrainGenManager.metaAcaciaWood);
		}

		return true;
	}
}
