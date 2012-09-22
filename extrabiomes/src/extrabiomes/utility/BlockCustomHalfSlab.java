/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.utility;

import net.minecraft.src.BlockHalfSlab;
import net.minecraft.src.Facing;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.plugin.redrock.RedRock;

public abstract class BlockCustomHalfSlab extends BlockHalfSlab {

	@SideOnly(Side.CLIENT)
	private static boolean isBlockSingleSlab(int blockID) {
		return blockID == RedRock.getHalfSlab().blockID;
	}

	final boolean	isDoubleSlab;

	public BlockCustomHalfSlab(int blockID, boolean isDoubleSlab,
			Material material)
	{
		super(blockID, isDoubleSlab, material);
		this.isDoubleSlab = isDoubleSlab;
	}

	@SideOnly(Side.CLIENT)
	private boolean blockShouldSideBeRendered(IBlockAccess blockAccess,
			int x, int y, int z, int side)
	{
		return side == 0 && minY > 0.0D ? true : side == 1
				&& maxY < 1.0D ? true : side == 2 && minZ > 0.0D ? true
				: side == 3 && maxZ < 1.0D ? true : side == 4
						&& minX > 0.0D ? true : side == 5
						&& maxX < 1.0D ? true : !blockAccess
						.isBlockOpaqueCube(x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess,
			int x, int y, int z, int side)
	{
		if (isDoubleSlab)
			return super.shouldSideBeRendered(blockAccess, x, y, z,
					side);
		else if (side != 1
				&& side != 0
				&& !blockShouldSideBeRendered(blockAccess, x, y, z,
						side))
			return false;
		else {
			final int x1 = x
					+ Facing.offsetsXForSide[Facing.faceToSide[side]];
			final int x2 = y
					+ Facing.offsetsYForSide[Facing.faceToSide[side]];
			final int x3 = z
					+ Facing.offsetsZForSide[Facing.faceToSide[side]];
			final boolean isUpsideDown = (blockAccess.getBlockMetadata(
					x1, x2, x3) & 8) != 0;
			return isUpsideDown ? side == 0 ? true
					: side == 1
							&& blockShouldSideBeRendered(blockAccess,
									x, y, z, side) ? true
							: !isBlockSingleSlab(blockAccess
									.getBlockId(x, y, z))
									|| (blockAccess.getBlockMetadata(x,
											y, z) & 8) == 0
					: side == 1 ? true : side == 0
							&& blockShouldSideBeRendered(blockAccess,
									x, y, z, side) ? true
							: !isBlockSingleSlab(blockAccess
									.getBlockId(x, y, z))
									|| (blockAccess.getBlockMetadata(x,
											y, z) & 8) != 0;
		}
	}

}
