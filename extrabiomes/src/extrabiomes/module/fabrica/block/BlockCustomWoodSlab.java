
package extrabiomes.module.fabrica.block;

import java.util.List;
import java.util.Random;

import net.minecraft.src.BlockWoodSlab;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockCustomWoodSlab extends BlockWoodSlab {
	public enum BlockType {
		REDWOOD(0, "Redwood Slab"),
		FIR(1, "Fir Wood Slab"),
		ACACIA(2, "Acacia Wood Slab");

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

	public BlockCustomWoodSlab(int id, boolean isDouble) {
		super(id, isDouble);
		if (!isDouble) singleSlabID = id;
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundWoodFootstep);
		setBurnProperties(blockID, 5, 20);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setLightOpacity(0);
	}

	@Override
	protected ItemStack createStackedBlock(int damage) {
		return new ItemStack(singleSlabID, 2, damage & 7);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return 128 + (metadata & 7);
	}

	@Override
	public String getFullSlabName(int metadata) {
		String woodType;
		switch (metadata) {
			case 1:
				woodType = BlockType.FIR.toString();
				break;
			case 2:
				woodType = BlockType.ACACIA.toString();
				break;
			default:
				woodType = BlockType.REDWOOD.toString();
		}

		return super.getBlockName() + "." + woodType;
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
