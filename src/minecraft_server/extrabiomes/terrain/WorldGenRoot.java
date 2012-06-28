package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.blocks.BlockCustomFlower;

public class WorldGenRoot extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (ExtrabiomesBlock.flower != null) {
			for (int i = 0; i < 10; i++) {
				final int x1 = (x + rand.nextInt(8)) - rand.nextInt(8);
				final int y1 = (y + rand.nextInt(4)) - rand.nextInt(4);
				final int z1 = (z + rand.nextInt(8)) - rand.nextInt(8);

				if (!world.isAirBlock(x1, y1, z1)) {
					continue;
				}

				final int i1 = 1 + rand.nextInt(rand.nextInt(3) + 1);

				for (int y2 = 0; y2 < i1; y2++) {
					if (ExtrabiomesBlock.flower.canBlockStay(world, x1,
							y1 + y2, z1)) {
						world.setBlockAndMetadata(x1, y1 + y2, z1,
								ExtrabiomesBlock.flower.blockID,
								BlockCustomFlower.metaRoot);
					}
				}
			}
		}

		return true;
	}
}
