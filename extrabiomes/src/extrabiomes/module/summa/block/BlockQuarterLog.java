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
import net.minecraft.src.BlockPistonBase;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.api.UseLogTurnerEvent;

public class BlockQuarterLog extends BlockLog {
	public enum BarkOn {
		SW, SE, NW, NE
	}

	public enum BlockType {
		REDWOOD(0, "Redwood Log"),
		FIR(1, "Fir Log"),
		OAK(2, "Oak Log");

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

	private enum Orientation {
		UD, NS, EW
	}

	private static int		renderId	= 31;
	private static int		dropID		= 0;

	private static Block	logNW;
	private static Block	logNE;
	private static Block	logSW;
	private static Block	logSE;

	private static Orientation determineOrientation(World world, int x,
			int y, int z, EntityLiving entity)
	{
		final int direction = BlockPistonBase.determineOrientation(
				world, x, y, z, (EntityPlayer) entity);

		switch (direction) {
			case 0:
			case 1:
				return Orientation.UD;
			case 2:
			case 3:
				return Orientation.NS;
			default:
				return Orientation.EW;
		}
	}

	public static void setDropID(int dropID) {
		BlockQuarterLog.dropID = dropID;
	}

	static void setQuarterLogs(Block logNW, Block logNE, Block logSW,
			Block logSE)
	{
		BlockQuarterLog.logNE = logNE;
		BlockQuarterLog.logNW = logNW;
		BlockQuarterLog.logSE = logSE;
		BlockQuarterLog.logSW = logSW;
	}

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

	private int getNextBlockID() {
		if (blockID == logSW.blockID) return logNE.blockID;
		if (blockID == logNE.blockID) return logNW.blockID;
		if (blockID == logNW.blockID) return logSE.blockID;
		return logSW.blockID;
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
	public ItemStack getPickBlock(MovingObjectPosition target,
			World world, int x, int y, int z)
	{
		final ItemStack itemstack = super.getPickBlock(target, world,
				x, y, z);
		itemstack.itemID = dropID;
		return itemstack;
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
					offset = 1;
					break;
				case 1:
					offset = 48;
					break;
				case 2:
					offset = 0;
					break;
				case 3:
					offset = 49;
					break;
				case 4:
					offset = 32;
					break;
				default:
					offset = 33;
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
		return dropID == 0 ? blockID : dropID;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLiving entity)
	{
		super.onBlockPlacedBy(world, x, y, z, entity);

		final Orientation orientation = determineOrientation(world, x,
				y, z, entity);

		if (orientation == Orientation.UD) {
			final int northID = world.getBlockId(x, y, z - 1);
			final int northMeta = world.getBlockMetadata(x, y, z - 1);
			final int southID = world.getBlockId(x, y, z + 1);
			final int southMeta = world.getBlockMetadata(x, y, z + 1);
			final int westID = world.getBlockId(x - 1, y, z);
			final int westMeta = world.getBlockMetadata(x - 1, y, z);
			final int eastID = world.getBlockId(x + 1, y, z);
			final int eastMeta = world.getBlockMetadata(x + 1, y, z);

			final int thisMeta = world.getBlockMetadata(x, y, z);

			if (thisMeta == northMeta) {
				if (northID == logNW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSW.blockID, thisMeta);
					return;
				}
				if (northID == logNE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSE.blockID, thisMeta);
					return;
				}
			}
			if (thisMeta == eastMeta) {
				if (eastID == logNE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNW.blockID, thisMeta);
					return;
				}
				if (eastID == logSE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSW.blockID, thisMeta);
					return;
				}
			}
			if (thisMeta == southMeta) {
				if (southID == logSW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNW.blockID, thisMeta);
					return;
				}
				if (southID == logSE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNE.blockID, thisMeta);
					return;
				}
			}
			if (thisMeta == westMeta) {
				if (westID == logNW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNE.blockID, thisMeta);
					return;
				}
				if (westID == logSW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSE.blockID, thisMeta);
					return;
				}
			}
		}

		if (orientation == Orientation.NS) {
			final int upID = world.getBlockId(x, y + 1, z);
			final int upMeta = world.getBlockMetadata(x, y + 1, z);
			final int downID = world.getBlockId(x, y - 1, z);
			final int downMeta = world.getBlockMetadata(x, y - 1, z);
			final int westID = world.getBlockId(x - 1, y, z);
			final int westMeta = world.getBlockMetadata(x - 1, y, z);
			final int eastID = world.getBlockId(x + 1, y, z);
			final int eastMeta = world.getBlockMetadata(x + 1, y, z);

			final int thisMeta = world.getBlockMetadata(x, y, z);

			if (thisMeta == upMeta) {
				if (upID == logNW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSW.blockID, thisMeta);
					return;
				}
				if (upID == logNE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSE.blockID, thisMeta);
					return;
				}
			}
			if (thisMeta == eastMeta) {
				if (eastID == logNE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNW.blockID, thisMeta);
					return;
				}
				if (eastID == logSE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSW.blockID, thisMeta);
					return;
				}
			}
			if (thisMeta == downMeta) {
				if (downID == logSW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNW.blockID, thisMeta);
					return;
				}
				if (downID == logSE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNE.blockID, thisMeta);
					return;
				}
			}
			if (thisMeta == westMeta) {
				if (westID == logNW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNE.blockID, thisMeta);
					return;
				}
				if (westID == logSW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSE.blockID, thisMeta);
					return;
				}
			}
		}

		if (orientation == Orientation.EW) {
			final int northID = world.getBlockId(x, y, z - 1);
			final int northMeta = world.getBlockMetadata(x, y, z - 1);
			final int southID = world.getBlockId(x, y, z + 1);
			final int southMeta = world.getBlockMetadata(x, y, z + 1);
			final int upID = world.getBlockId(x, y + 1, z);
			final int upMeta = world.getBlockMetadata(x, y + 1, z);
			final int downID = world.getBlockId(x, y - 1, z);
			final int downMeta = world.getBlockMetadata(x, y - 1, z);

			final int thisMeta = world.getBlockMetadata(x, y, z);

			if (thisMeta == northMeta) {
				if (northID == logSW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSE.blockID, thisMeta);
					return;
				}
				if (northID == logNE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNW.blockID, thisMeta);
					return;
				}
			}
			if (thisMeta == upMeta) {
				if (upID == logNW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSE.blockID, thisMeta);
					return;
				}
				if (upID == logNE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSW.blockID, thisMeta);
					return;
				}
			}
			if (thisMeta == southMeta) {
				if (southID == logSE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logSW.blockID, thisMeta);
					return;
				}
				if (southID == logNW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNE.blockID, thisMeta);
					return;
				}
			}
			if (thisMeta == downMeta) {
				if (downID == logSW.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNE.blockID, thisMeta);
					return;
				}
				if (downID == logSE.blockID) {
					world.setBlockAndMetadataWithNotify(x, y, z,
							logNW.blockID, thisMeta);
					return;
				}
			}
		}
	}

	@ForgeSubscribe
	public void onUseLogTurnerEvent(UseLogTurnerEvent event) {
		int id = event.world.getBlockId(event.x, event.y, event.z);

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

				if (orientation == 0) id = getNextBlockID();
				event.world.setBlockAndMetadata(event.x, event.y,
						event.z, id, type | orientation);
			}
			event.setHandled();
		}
	}

}
