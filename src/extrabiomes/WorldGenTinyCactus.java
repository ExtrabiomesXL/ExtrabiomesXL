package extrabiomes;

import java.util.Random;

import extrabiomes.api.TerrainGenBlock;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.Block;

public class WorldGenTinyCactus extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int i = 0; i < 10; i++) {
			int x1 = (x + rand.nextInt(8)) - rand.nextInt(8);
			int y1 = (y + rand.nextInt(4)) - rand.nextInt(4);
			int z1 = (z + rand.nextInt(8)) - rand.nextInt(8);

			if (!world.isAirBlock(x1, y1, z1)) {
				continue;
			}

			int i1 = 1 + rand.nextInt(rand.nextInt(3) + 1);

			for (int y2 = 0; y2 < i1; y2++) {
				if (world.getBlockId(x1, y1 + y2 - 1, z1) == Block.sand.blockID) {
					final MetaBlock mb = BlockControl.INSTANCE
							.getTerrainGenBlock(TerrainGenBlock.TINY_CACTUS);
					world.setBlockAndMetadata(x1, y1 + y2, z1, mb.blockId(),
							mb.metadata());
				}
			}
		}

		return true;
	}
}
