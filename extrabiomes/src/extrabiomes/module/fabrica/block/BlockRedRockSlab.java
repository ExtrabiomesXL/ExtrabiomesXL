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
		REDCOBBLE(0), REDROCK(1), REDROCKBRICK(2);

		private final int	metadata;

		BlockType(int metadata) {
			this.metadata = metadata;
		}

		public int metadata() {
			return metadata;
		}

		@Override
		public String toString() {
			return name().toLowerCase();
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
		return metadata == BlockType.REDROCK.metadata() ? side < 2 ? 14
				: 13
				: metadata == BlockType.REDROCKBRICK.metadata() ? 11
						: 12;
	}

	@Override
	public String getFullSlabName(int metadata) {
		String blockStepType;
		switch (metadata & 7) {
			case 1:
				blockStepType = BlockType.REDROCK.toString();
				break;
			case 2:
				blockStepType = BlockType.REDROCKBRICK.toString();
				break;
			default:
				blockStepType = BlockType.REDCOBBLE.toString();
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
