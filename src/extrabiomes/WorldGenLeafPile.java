package extrabiomes;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.TerrainGenBlock;

public class WorldGenLeafPile extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		BlockControl bm = BlockControl.INSTANCE;
		for (int i = 0; ((i = world.getBlockId(x, y, z)) == 0 || bm.isLeaves(i))
				&& y > 0; y--) {
		}

		for (int j = 0; j < 128; j++) {
			final int x1 = (x + rand.nextInt(8)) - rand.nextInt(8);
			final int y1 = (y + rand.nextInt(4)) - rand.nextInt(4);
			final int z1 = (z + rand.nextInt(8)) - rand.nextInt(8);

			final MetaBlock mb = bm.getTerrainGenBlock(TerrainGenBlock.LEAF_PILE);
			if (world.isAirBlock(x1, y1, z1)
					&& mb.block().canBlockStay(world, x1, y1, z1)) {
				world.setBlockAndMetadata(x1, y1, z1, mb.blockId(),
						mb.metadata());
			}
		}

		return true;
	}

}
