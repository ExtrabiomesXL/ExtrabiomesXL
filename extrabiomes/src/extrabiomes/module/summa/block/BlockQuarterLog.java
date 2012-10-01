/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.block;

import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockLog;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockQuarterLog extends BlockLog {
	enum BarkOn {
		SW, SE, NW, NE
	}

	enum BlockType {
		REDWOOD(0, "Redwood"),
		FIR(1, "Fir"),
		OAK(2, "Oak");

		private final int		value;
		private final String	itemName;

		BlockType(int value, String itemName) {
			this.value = value;
			this.itemName = itemName;
		}

		public String itemName() {
			return itemName;
		}

		public int metadata() {
			return value;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder(name()
					.toLowerCase());
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		}
	}

	private static int	renderId	= 31;

	public static void setRenderId(int renderId) {
		BlockQuarterLog.renderId = renderId;
	}

	private final BarkOn	barkOnSides;

	protected BlockQuarterLog(int id, BarkOn barkOnSides) {
		super(id);
		this.barkOnSides = barkOnSides;
		blockIndexInTexture = 144;
		setTextureFile("/extrabiomes/extrabiomes.png");
		setStepSound(soundWoodFootstep);
		setRequiresSelfNotify();
		setHardness(2.0F);
		setBurnProperties(blockID, 5, 5);
		setResistance(Block.wood.getExplosionResistance(null) * 5.0F);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		final int orientation = metadata & 12;
		final int type = metadata & 3;

		int offset = 0;
		switch (barkOnSides) {
			case SE:
				offset = getSETextureOffset(side, orientation);
				break;
			case SW:
				offset = getSWTextureOffset(side, orientation);
				break;
			case NE:
				offset = getNETextureOffset(side, orientation);
				break;
			case NW:
				offset = getNWTextureOffset(side, orientation);
				break;
		}

		return blockIndexInTexture + offset + type * 2;
	}

	private int getNETextureOffset(int side, final int orientation) {
		int offset = 0;
		if (orientation == 0)
			switch (side) {
				case 0:
				case 1:
					offset = 17;
					break;
				case 2:
					offset = 0;
					break;
				case 3:
					offset = 49;
					break;
				case 4:
					offset = 48;
					break;
				default:
					offset = 1;
			}
		else if (orientation == 4)
			switch (side) {
				case 0:
					offset = 49;
					break;
				case 1:
					offset = 0;
					break;
				case 2:
					offset = 1;
					break;
				case 3:
					offset = 48;
					break;
				case 4:
					offset = 16;
					break;
				default:
					offset = 17;
			}
		else if (orientation == 8) switch (side) {
			case 0:
				offset = 49;
				break;
			case 2:
				offset = 16;
				break;
			case 3:
				offset = 17;
				break;
			case 4:
				offset = 49;
				break;
			default:
				offset = 1;
		}
		return offset;
	}

	private int getNWTextureOffset(int side, final int orientation) {
		int offset = 0;
		if (orientation == 0)
			switch (side) {
				case 0:
				case 1:
					offset = 16;
					break;
				case 2:
					offset = 1;
					break;
				case 3:
					offset = 48;
					break;
				case 4:
					offset = 0;
					break;
				default:
					offset = 49;
			}
		else if (orientation == 4)
			switch (side) {
				case 0:
					offset = 48;
					break;
				case 1:
					offset = 1;
					break;
				case 2:
					offset = 49;
					break;
				case 3:
					offset = 0;
					break;
				case 4:
					offset = 17;
					break;
				default:
					offset = 16;
			}
		else if (orientation == 8) switch (side) {
			case 0:
				offset = 48;
				break;
			case 1:
				offset = 0;
				break;
			case 2:
				offset = 17;
				break;
			case 3:
				offset = 16;
				break;
			case 4:
				offset = 1;
				break;
			default:
				offset = 49;
		}
		return offset;
	}

	@Override
	public int getRenderType() {
		return renderId;
	}

	private int getSETextureOffset(int side, final int orientation) {
		int offset = 0;
		if (orientation == 0)
			switch (side) {
				case 0:
				case 1:
					offset = 33;
					break;
				case 2:
					offset = 48;
					break;
				case 3:
					offset = 1;
					break;
				case 4:
					offset = 49;
					break;
				default:
					offset = 0;
			}
		else if (orientation == 4)
			switch (side) {
				case 0:
					offset = 0;
					break;
				case 1:
					offset = 49;
					break;
				case 2:
					offset = 48;
					break;
				case 3:
					offset = 1;
					break;
				case 4:
					offset = 33;
					break;
				default:
					offset = 32;
			}
		else if (orientation == 8) switch (side) {
			case 0:
				offset = 1;
				break;
			case 1:
				offset = 49;
				break;
			case 2:
				offset = 32;
				break;
			case 3:
				offset = 33;
				break;
			case 4:
				offset = 48;
				break;
			default:
				offset = 0;
		}
		return offset;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int blockID,
			CreativeTabs par2CreativeTabs, List list)
	{
		for (final BlockType type : BlockType.values())
			list.add(new ItemStack(blockID, 1, type.metadata()));
	}

	private int getSWTextureOffset(int side, final int orientation) {
		int offset = 0;
		if (orientation == 0)
			switch (side) {
				case 0:
				case 1:
					offset = 32;
					break;
				case 2:
					offset = 49;
					break;
				case 3:
					offset = 0;
					break;
				case 4:
					offset = 1;
					break;
				default:
					offset = 48;
			}
		else if (orientation == 4)
			switch (side) {
				case 0:
					offset = 0;
					break;
				case 1:
					offset = 49;
					break;
				case 2:
					offset = 48;
					break;
				case 3:
					offset = 1;
					break;
				case 4:
					offset = 33;
					break;
				default:
					offset = 32;
			}
		else if (orientation == 8) switch (side) {
			case 0:
				offset = 0;
				break;
			case 1:
				offset = 48;
				break;
			case 2:
				offset = 33;
				break;
			case 3:
				offset = 32;
				break;
			case 4:
				offset = 0;
				break;
			default:
				offset = 48;
		}
		return offset;
	}

	@Override
	public int idDropped(int metadata, Random rand, int unused) {
		return blockID;
	}
}
