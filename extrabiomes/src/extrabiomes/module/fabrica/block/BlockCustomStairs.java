/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import extrabiomes.Extrabiomes;
import net.minecraft.src.Block;
import net.minecraft.src.BlockStairs;

public class BlockCustomStairs extends BlockStairs {

	public BlockCustomStairs(int blockID, Block modelBlock,
			int modelMetadata)
	{
		super(blockID, modelBlock, modelMetadata);
		setRequiresSelfNotify();
		setTextureFile("/extrabiomes/extrabiomes.png");
		setLightOpacity(0);
		setCreativeTab(Extrabiomes.extrabiomesTab);
	}

}
