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
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.api.UseLogTurnerEvent;

public class BlockCustomLog extends BlockLog {
	public enum BlockType {
		FIR(0, "Fir Log"),
		ACACIA(1, "Acacia Log");

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

	public BlockCustomLog(int id) {
		super(id);
		blockIndexInTexture = 97;
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
		if (orientation == 0 && (side == 1 || side == 0)
				|| orientation == 4 && (side == 5 || side == 4)
				|| orientation == 8 && (side == 2 || side == 3))
			return blockIndexInTexture + 16 + type;
		return blockIndexInTexture + type;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int blockID,
			CreativeTabs par2CreativeTabs, List list)
	{
		for (final BlockType type : BlockType.values())
			list.add(new ItemStack(blockID, 1, type.metadata()));
	}

	@Override
	public int idDropped(int metadata, Random rand, int unused) {
		return blockID;
	}

	@ForgeSubscribe
	public void onUseLogTurnerEvent(UseLogTurnerEvent event) {
		final int id = event.world
				.getBlockId(event.x, event.y, event.z);

		if (id == blockID) {
			final Block wood = Block.wood;
			event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F,
					event.z + 0.5F, wood.stepSound.getStepSound(),
					(wood.stepSound.getVolume() + 1.0F) / 2.0F,
					wood.stepSound.getPitch() * 1.55F);

			if (!event.world.isRemote) {
				final int metadata = event.world.getBlockMetadata(
						event.x, event.y, event.z);
				int orientation = metadata & 12;
				final int type = metadata & 3;

				orientation = orientation == 0 ? 4
						: orientation == 4 ? 8 : 0;
				event.world.setBlockAndMetadata(event.x, event.y,
						event.z, blockID, type | orientation);
			}
			event.setHandled();
		}
	}
}
