/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class BlockNewWoodSlab extends BlockWoodSlab {
	public enum BlockType {
		SAKURA_BLOSSOM(0);

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
	
	private Icon[] textures  = {null, null, null, null, null, null, null, null};

	public BlockNewWoodSlab(int id, boolean isDouble) {
		super(id, isDouble);
		if (!isDouble) singleSlabID = id;
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundWoodFootstep);
		setBurnProperties(blockID, 5, 20);
		//setTextureFile("/extrabiomes/extrabiomes.png");
		setLightOpacity(0);
		setCreativeTab(Extrabiomes.tabsEBXL);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){
    	textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "plankssakura");
    }

	@Override
	protected ItemStack createStackedBlock(int damage) {
		return new ItemStack(singleSlabID, 2, damage & 7);
	}

	@Override
	public Icon getIcon(int side, int metadata)
	{
		return textures[0];
	}

	@Override
	public String getFullSlabName(int metadata) {
		String woodType;
		switch (metadata & 7) {
			default:
				woodType = BlockType.SAKURA_BLOSSOM.toString();
		}

		return super.getUnlocalizedName() + "." + woodType;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs tab, List itemList) {
		if (blockID == singleSlabID) {
			for (final BlockType type : BlockType.values()) {
				itemList.add(new ItemStack(blockID, 1, type.metadata()));
			}
		}
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return singleSlabID;
	}
	
	@Override
	@SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return singleSlabID;
    }
}
