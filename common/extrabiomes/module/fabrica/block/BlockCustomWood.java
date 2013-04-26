/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.List;

import net.minecraft.block.BlockWood;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class BlockCustomWood extends BlockWood {
	public enum BlockType {
		REDWOOD(0), FIR(1), ACACIA(2);

		private final int	metadata;

		BlockType(int metadata) {
			this.metadata = metadata;
		}

		public int metadata() {
			return metadata;
		}
	}
	
	private Icon[] textures  = {null, null, null, null, null, null, null, null};

	public BlockCustomWood(int id) {
		super(id);
		//blockIndexInTexture = 128;
		//setTextureFile("/extrabiomes/extrabiomes.png");
		setStepSound(soundWoodFootstep);
		//setRequiresSelfNotify();
		setHardness(2.0F);
		setResistance(5.0F);
		setBurnProperties(blockID, 5, 20);
		setCreativeTab(Extrabiomes.tabsEBXL);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){
    	textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksredwood");
    	textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksfir");
    	textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksacacia");
    }

	@Override
	public Icon getIcon(int side, int metadata)
	{
		return textures[metadata];
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int blockID,
			CreativeTabs par2CreativeTabs, List list)
	{
		for (final BlockType type : BlockType.values())
			list.add(new ItemStack(blockID, 1, type.metadata()));
	}
}
