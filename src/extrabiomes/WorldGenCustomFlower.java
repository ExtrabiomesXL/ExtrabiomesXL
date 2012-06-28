package extrabiomes;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenCustomFlower extends WorldGenerator {
	private MetaBlock mbToGen;

	public WorldGenCustomFlower(MetaBlock mbToGen) {
		this.mbToGen = mbToGen;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int id;

		while (((id = world.getBlockId(x, y, z)) == 0 || BlockControl.INSTANCE
				.isLeaves(id)) && y > 0)
			y--;

		for (int i = 0; i < 128; ++i) {
			final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			final int y1 = y + rand.nextInt(4) - rand.nextInt(4);
			final int z1 = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.isAirBlock(x1, y1, z1)
					&& mbToGen.block().canBlockStay(world, x1, y1, z1))
				world.setBlockAndMetadata(x1, y1, z1, mbToGen.blockId(),
						mbToGen.metadata());
		}

		return true;
	}
}
