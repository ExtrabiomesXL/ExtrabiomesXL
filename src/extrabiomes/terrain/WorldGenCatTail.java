package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.ExtrabiomesBlock;

public class WorldGenCatTail extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (ExtrabiomesBlock.catTail != null)
			for (int l = 0; l < 20; l++) {
				final int x1 = (x + rand.nextInt(4)) - rand.nextInt(4);
				final int y1 = y;
				final int z1 = (z + rand.nextInt(4)) - rand.nextInt(4);
				if (!world.isAirBlock(x1, y1, z1)
						|| world.getBlockMaterial(x1 - 1, y1 - 1, z1) != Material.water
						&& world.getBlockMaterial(x1 + 1, y1 - 1, z1) != Material.water
						&& world.getBlockMaterial(x1, y1 - 1, z1 - 1) != Material.water
						&& world.getBlockMaterial(x1, y1 - 1, z1 + 1) != Material.water) {
					continue;
				}

				final int i1 = 1 + rand.nextInt(rand.nextInt(1) + 1);

				for (int i = 0; i < i1; i++) {
					if (ExtrabiomesBlock.catTail.canBlockStay(world, x1,
							y1 + i, z1)) {
						world.setBlock(x1, y1 + i, z1,
								ExtrabiomesBlock.catTail.blockID);
					}
				}
			}

		return true;
	}

}
