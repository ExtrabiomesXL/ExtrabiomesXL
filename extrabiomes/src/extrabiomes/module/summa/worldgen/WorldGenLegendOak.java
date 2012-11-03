/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.module.summa.TreeSoilRegistry;

public class WorldGenLegendOak extends WorldGenerator {

	enum Acuteness {
		LOOSE, TIGHTER, TIGHT
	}

	enum BendDirection {
		LEFT, STRAIGHT, RIGHT
	}

	private static Block	trunkBlockNE	= Block.wood;
	private static Block	trunkBlockNW	= Block.wood;
	private static Block	trunkBlockSE	= Block.wood;
	private static Block	trunkBlockSW	= Block.wood;
	private static int		trunkMetadata	= 0;
	private static Block	branchBlock		= Block.wood;
	private static int		branchMetadata	= 0;
	private static Block	leavesBlock		= Block.leaves;
	private static int		leavesMetadata	= 0;

	public static void setLeavesBlock(Block block, int metadata) {
		leavesBlock = block;
		leavesMetadata = metadata;
	}

	public static void setTrunkBlock(Block blockNW, Block blockNE,
			Block blockSW, Block blockSE, int metadata)
	{
		trunkBlockNW = blockNW;
		trunkBlockNE = blockNE;
		trunkBlockSW = blockSW;
		trunkBlockSE = blockSE;
		trunkMetadata = metadata;
	}

	public WorldGenLegendOak(boolean doNotify) {
		super(doNotify);
	}

	@Override
	public boolean generate(World world, Random random, int x, int y,
			int z)
	{

		if (!TreeSoilRegistry
				.isValidSoil(world.getBlockId(x, y - 1, z)))
			return false;

		final int height = random.nextInt(4) + 3;
		final int size = 15 + random.nextInt(25);

		growTree(world, random, x, y, z, height, 0, size);

		return true;
	}

	private void growLeafNode(World world, int x, int y, int z) {
		for (int xOffset = -3; xOffset <= 3; xOffset++)
			for (int zOffset = -3; zOffset <= 3; zOffset++) {
				if ((Math.abs(xOffset) != 3 || Math.abs(zOffset) != 3)
						&& (Math.abs(xOffset) != 3 || Math.abs(zOffset) != 2)
						&& (Math.abs(xOffset) != 2 || Math.abs(zOffset) != 3)
						&& (xOffset != 0 || zOffset != 0))
					if (world.getBlockId(x + xOffset, y, z + zOffset) == 0)
						setBlockAndMetadata(world, x + xOffset, y, z
								+ zOffset, leavesBlock.blockID,
								leavesMetadata);
				if (Math.abs(xOffset) >= 3 || Math.abs(zOffset) >= 3
						|| Math.abs(xOffset) == 2
						&& Math.abs(zOffset) == 2) continue;
				if (world.getBlockId(x + xOffset, y - 1, z + zOffset) == 0)
					setBlockAndMetadata(world, x + xOffset, y - 1, z
							+ zOffset, leavesBlock.blockID,
							leavesMetadata);
				if (world.getBlockId(x + xOffset, y + 1, z + zOffset) != 0)
					continue;
				setBlockAndMetadata(world, x + xOffset, y + 1, z
						+ zOffset, leavesBlock.blockID, leavesMetadata);
			}
	}

	protected void growLeaves(World world, Random random, int x, int y,
			int z, int height, int leaflessHeight, int leafWidth)
	{
		for (final BendDirection xDirection : BendDirection.values())
			for (final BendDirection zDirection : BendDirection
					.values())
			{
				if (xDirection == BendDirection.STRAIGHT
						&& zDirection == BendDirection.STRAIGHT)
					continue;
				primary(world, random, leafWidth, xDirection,
						zDirection, x, y + height, z);
				inside(world, random, leafWidth, xDirection,
						zDirection, x, y + height, z);
				insideSmall(world, random, leafWidth, xDirection,
						zDirection, x, y + height, z);
			}
	}

	protected void growTree(World world, Random random, int x, int y,
			int z, int height, int leaflessHeight, int leafWidth)
	{
		world.setBlock(x, y - 1, z, Block.dirt.blockID);
		world.setBlock(x - 1, y - 1, z, Block.dirt.blockID);
		world.setBlock(x, y - 1, z - 1, Block.dirt.blockID);
		world.setBlock(x - 1, y - 1, z - 1, Block.dirt.blockID);

		growTrunk(world, random, x, y, z, height);

		growLeaves(world, random, x, y, z, height, leaflessHeight,
				leafWidth);

	}

	protected void growTrunk(World world, Random random, int x, int y,
			int z, int height)
	{

		for (int yOffset = 0; yOffset < height + 1; yOffset++) {
			setBlockAndMetadata(world, x, y + yOffset, z,
					trunkBlockSE.blockID, trunkMetadata);
			setBlockAndMetadata(world, x - 1, y + yOffset, z,
					trunkBlockSW.blockID, trunkMetadata);
			setBlockAndMetadata(world, x, y + yOffset, z - 1,
					trunkBlockNE.blockID, trunkMetadata);
			setBlockAndMetadata(world, x - 1, y + yOffset, z - 1,
					trunkBlockNW.blockID, trunkMetadata);
		}

	}

	private void inside(World world, Random random, int size,
			BendDirection xDirection, BendDirection zDirection, int x,
			int y, int z)
	{
		int length = 0;
		while (length < 2 * size / 3) {
			setBlockAndMetadata(world, x, y, z, branchBlock.blockID,
					branchMetadata);
			if (random.nextInt(3) == 0 || length == 2 * size / 3 - 1)
				growLeafNode(world, x, y, z);
			switch (xDirection) {
				case STRAIGHT:
					x += random.nextInt(3) - 1;
					break;
				case RIGHT:
					x += random.nextInt(2);
					break;
				case LEFT:
					x -= random.nextInt(2);
			}
			switch (zDirection) {
				case STRAIGHT:
					z += random.nextInt(3) - 1;
					break;
				case RIGHT:
					z += random.nextInt(2);
					break;
				case LEFT:
					z -= random.nextInt(2);
			}
			if (random.nextInt(6) == 0 && length > size / 3)
				secondary(world, random, size / 3 - length / 3,
						xDirection, zDirection, x, y, z);
			y++;
			length++;
		}
	}

	private void insideSmall(World world, Random random, int size,
			BendDirection xDirection, BendDirection zDirection, int x,
			int y, int z)
	{
		int length = 0;
		while (length < size / 3) {
			setBlockAndMetadata(world, x, y, z, branchBlock.blockID,
					branchMetadata);
			if (random.nextInt(3) == 0 || length == size / 3 - 1)
				growLeafNode(world, x, y, z);
			switch (xDirection) {
				case STRAIGHT:
					x += random.nextInt(3) - 1;
					break;
				case RIGHT:
					x += random.nextInt(2);
					break;
				case LEFT:
					x -= random.nextInt(2);
			}
			switch (zDirection) {
				case STRAIGHT:
					z += random.nextInt(3) - 1;
					break;
				case RIGHT:
					z += random.nextInt(2);
					break;
				case LEFT:
					z -= random.nextInt(2);
			}
			if (random.nextInt(6) == 0 && length > size / 6)
				secondary(world, random, size / 6 - length / 6,
						xDirection, zDirection, x, y, z);
			y++;
			length++;
		}
	}

	private void primary(World world, Random random, int size,
			BendDirection xDirection, BendDirection zDirection, int x,
			int y, int z)
	{
		Acuteness acuteness = Acuteness.LOOSE;
		int length = 0;
		if (xDirection == BendDirection.RIGHT) x += 2;
		if (xDirection == BendDirection.LEFT) x -= 2;
		if (zDirection == BendDirection.RIGHT) z += 2;
		if (zDirection == BendDirection.LEFT) z -= 2;
		while (length < size) {
			switch (acuteness) {
				case LOOSE:
					if (random.nextInt(4) == 0) y++;
					break;
				case TIGHTER:
					if (random.nextInt(2) == 0) y++;
					break;
				case TIGHT:
					y++;
			}
			setBlockAndMetadata(world, x, y, z, branchBlock.blockID,
					branchMetadata);
			if (random.nextInt(3) == 0 || length == size - 1)
				growLeafNode(world, x, y, z);
			switch (xDirection) {
				case STRAIGHT:
					x += random.nextInt(3) - 1;
					break;
				case RIGHT:
					x += random.nextInt(2);
					break;
				case LEFT:
					x -= random.nextInt(2);
			}
			switch (zDirection) {
				case STRAIGHT:
					z += random.nextInt(3) - 1;
					break;
				case RIGHT:
					z += random.nextInt(2);
					break;
				case LEFT:
					z -= random.nextInt(2);
			}
			if (length == size / 3) acuteness = Acuteness.TIGHTER;
			if (length == 2 * size / 3) acuteness = Acuteness.TIGHT;
			if (random.nextInt(4) == 0)
				secondary(world, random, size / 2 - length / 2,
						xDirection, zDirection, x, y, z);
			length++;
		}
	}

	private void secondary(World world, Random random, int size,
			BendDirection xDirection, BendDirection zDirection, int x,
			int y, int z)
	{
		int length = 0;
		for (int branch = 0; branch < 2; branch++) {
			int x1 = x;
			int y1 = y;
			int z1 = z;
			while (length < size) {
				if (random.nextInt(2) == 0) y1++;
				setBlockAndMetadata(world, x1, y1, z1,
						branchBlock.blockID, branchMetadata);
				if (random.nextInt(4) == 0 || length == size - 1)
					growLeafNode(world, x1, y1, z1);
				if (zDirection == BendDirection.STRAIGHT) {
					if (xDirection == BendDirection.RIGHT)
						x1 += random.nextInt(2);
					else
						x1 -= random.nextInt(2);
					if (branch == 0)
						z1 += random.nextInt(2);
					else
						z1 -= random.nextInt(2);
				}
				if (xDirection == BendDirection.STRAIGHT) {
					if (zDirection == BendDirection.RIGHT)
						z1 += random.nextInt(2);
					else
						z1 -= random.nextInt(2);
					if (branch == 0)
						x1 += random.nextInt(2);
					else
						x1 -= random.nextInt(2);
				}
				if (xDirection == BendDirection.RIGHT) {
					if (zDirection == BendDirection.RIGHT)
						if (branch == 0)
							z1 += random.nextInt(2);
						else
							x1 += random.nextInt(2);
					if (zDirection == BendDirection.LEFT)
						if (branch == 0)
							z1 -= random.nextInt(2);
						else
							x1 += random.nextInt(2);
				}
				if (xDirection == BendDirection.LEFT) {
					if (zDirection == BendDirection.RIGHT)
						if (branch == 0)
							z1 += random.nextInt(2);
						else
							x1 -= random.nextInt(2);
					if (zDirection == BendDirection.LEFT)
						if (branch == 0)
							z1 -= random.nextInt(2);
						else
							x1 -= random.nextInt(2);
				}
				length++;
			}
		}
	}
}