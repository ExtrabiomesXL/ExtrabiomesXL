package extrabiomes;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Direction;
import net.minecraft.src.Facing;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenDirtBed extends WorldGenerator {
	public WorldGenDirtBed() {
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3,
			int par4, int par5) {
		int i = par3;
		int j = par5;
		label0:

		for (; par4 < 62; par4++) {
			if (!par1World.isAirBlock(par3, par4, par5)) {
				int k = 2;

				do {
					if (k > 5) {
						continue label0;
					}

					if (Block.dirt.canPlaceBlockOnSide(par1World, par3, par4,
							par5, k)) {
						par1World
								.setBlockAndMetadata(
										par3,
										par4,
										par5,
										Block.dirt.blockID,
										1 << Direction.vineGrowth[Facing.faceToSide[k]]);
						continue label0;
					}

					k++;
				} while (true);
			}

			par3 = (i + 16);
			par5 = (j + 16);
		}

		return true;
	}
}