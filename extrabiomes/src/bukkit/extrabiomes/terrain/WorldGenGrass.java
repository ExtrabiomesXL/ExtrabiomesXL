
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.TerrainGenManager;

public class WorldGenGrass extends WorldGenerator {
	final int	metaToPlace;

	public WorldGenGrass(int i) {
		metaToPlace = i;
	}

	public boolean a(World world, Random random, int i, int j, int k) {
		if (ExtrabiomesBlock.grass != null) {
			int l;

			for (; Block.byId[l = world.getTypeId(i, j, k)] == null
					|| Block.byId[l].isLeaves(world, i, j, k) && j > 0; j--)
			{}

			for (int i1 = 0; i1 < 4; i1++) {
				final int j1 = i + random.nextInt(8)
						- random.nextInt(8);
				final int k1 = j + random.nextInt(4)
						- random.nextInt(4);
				final int l1 = k + random.nextInt(8)
						- random.nextInt(8);
				final int i2 = world.getTypeId(j1, k1 - 1, l1);
				boolean flag;

				if (metaToPlace == 2 || metaToPlace == 3
						|| metaToPlace == 4)
					flag = i2 == Block.SAND.id
							|| i2 == TerrainGenManager.blockWasteland.id
							&& world.m(j1, k1, l1) >= 8
							&& world.isChunkLoaded(j1, k1, l1);
				else if (metaToPlace == 0 || metaToPlace == 1)
					flag = i2 == TerrainGenManager.blockMountainRidge.id
							&& world.m(j1, k1, l1) >= 8
							&& world.isChunkLoaded(j1, k1, l1);
				else
					flag = false;

				if (flag && world.isEmpty(j1, k1, l1))
					world.setRawTypeIdAndData(j1, k1, l1,
							ExtrabiomesBlock.grass.id, metaToPlace);
			}
		}

		return true;
	}
}
