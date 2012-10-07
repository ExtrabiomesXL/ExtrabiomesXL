/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.List;
import java.util.Random;

import net.minecraft.src.BlockStep;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockRedRockSlab extends BlockStep {
	public enum BlockType {
		RED_COBBLE(0, "Red Cobblestone Slab"),
		RED_ROCK(1, "Red Rock Slab"),
		RED_ROCK_BRICK(2, "Red Rock Brick Slab");

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

	private static int	singleSlabID	= 0;

	public BlockRedRockSlab(int id, boolean isDouble) {
		super(id, isDouble);
		if (!isDouble) singleSlabID = blockID;
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setLightOpacity(0);
	}

	@Override
	protected ItemStack createStackedBlock(int metadata) {
		return new ItemStack(singleSlabID, 2, metadata & 7);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		metadata &= 7;
		return metadata == BlockType.RED_ROCK.metadata() ? side < 2 ? 14
				: 13
				: metadata == BlockType.RED_ROCK_BRICK.metadata() ? 11
						: 12;
	}

	@Override
	public String getFullSlabName(int metadata) {
		String blockStepType;
		switch (metadata & 7) {
			case 1:
				blockStepType = BlockType.RED_ROCK.toString();
				break;
			case 2:
				blockStepType = BlockType.RED_ROCK_BRICK.toString();
				break;
			default:
				blockStepType = BlockType.RED_COBBLE.toString();
		}

		return super.getBlockName() + "." + blockStepType;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs tab,
			List itemList)
	{
		if (blockID == singleSlabID)
			for (final BlockType type : BlockType.values())
				itemList.add(new ItemStack(blockID, 1, type.metadata()));
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return singleSlabID;
	}
}
