package extrabiomes;

import java.util.Random;

import extrabiomes.api.TerrainGenBlock;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenRoot extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int i = 0; i < 10; i++) {
			final int x1 = (x + rand.nextInt(8)) - rand.nextInt(8);
			final int y1 = (y + rand.nextInt(4)) - rand.nextInt(4);
			final int z1 = (z + rand.nextInt(8)) - rand.nextInt(8);

			if (!world.isAirBlock(x1, y1, z1)) {
				continue;
			}

			final int i1 = 1 + rand.nextInt(rand.nextInt(3) + 1);

			for (int y2 = 0; y2 < i1; y2++) {
				final MetaBlock mb = BlockControl.INSTANCE
						.getTerrainGenBlock(TerrainGenBlock.ROOT);
				if (mb.block().canBlockStay(world, x1, y1 + y2, z1)) {
					world.setBlockAndMetadata(x1, y1 + y2, z1, mb.blockId(),
							mb.metadata());
				}
			}
		}

		return true;
	}
}
