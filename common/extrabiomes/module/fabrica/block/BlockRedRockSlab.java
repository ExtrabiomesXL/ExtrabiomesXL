/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStep;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

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
	private Icon[] textures = {null, null, null, null};

	public BlockRedRockSlab(int id, boolean isDouble) {
		super(id, isDouble);
		if (!isDouble) singleSlabID = blockID;
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundStoneFootstep);
		setLightOpacity(0);
		setCreativeTab(Extrabiomes.tabsEBXL);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
		textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockcobble");
		textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockbrick");
		textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockslabside");
		textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockslabtop");
	}

	@Override
	protected ItemStack createStackedBlock(int metadata) {
		return new ItemStack(singleSlabID, 2, metadata & 7);
	}

	@Override
	public Icon getIcon(int side, int metadata)
	{
		metadata &= 7;
		return metadata == BlockType.REDROCK.metadata() ? side < 2 ? textures[3]
				: textures[2]
				: metadata == BlockType.REDROCKBRICK.metadata() ? textures[1]
						: textures[0];
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

		return super.getUnlocalizedName() + "." + blockStepType;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
