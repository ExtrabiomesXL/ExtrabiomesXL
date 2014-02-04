package extrabiomes.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockCustomVine extends BlockVine {

	public enum BlockType {
		GLORIOSA, SPANISH_MOSS;
	}

	public final BlockType	type;

	public BlockCustomVine(int blockID, BlockType type) {
		super(blockID);
		this.type = type;
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
    	final String iconPath = Extrabiomes.TEXTURE_PATH + "vine_" + type.toString().toLowerCase();
		this.blockIcon = iconRegister.registerIcon(iconPath);
    }

}
