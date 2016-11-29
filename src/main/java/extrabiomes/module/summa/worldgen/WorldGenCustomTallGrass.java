/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomTallGrass extends WorldGenerator {
	private final IBlockState tallGrassState;

	public WorldGenCustomTallGrass(IBlockState state) {
		this.tallGrassState = state;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (IBlockState state = worldIn.getBlockState(position); (state.getBlock().isAir(state, worldIn, position)
				|| state.getBlock().isLeaves(state, worldIn, position))
				&& position.getY() > 0; state = worldIn.getBlockState(position)) {
			position = position.down();
		}

		for (int i = 0; i < 128; ++i) {
			BlockPos pos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if (worldIn.isAirBlock(pos) && Blocks.TALLGRASS.canBlockStay(worldIn, pos, tallGrassState)) {
				worldIn.setBlockState(pos, this.tallGrassState, 2);
			}
		}

		return true;
	}
}