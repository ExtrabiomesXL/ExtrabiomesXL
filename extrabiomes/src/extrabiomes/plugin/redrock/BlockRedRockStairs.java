/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.redrock;

import net.minecraft.src.Block;
import net.minecraft.src.BlockStairs;

public class BlockRedRockStairs extends BlockStairs {
	public BlockRedRockStairs(int blockID, Block modelBlock,
			int modelMetadata)
	{
		super(blockID, modelBlock, modelMetadata);
		setRequiresSelfNotify();
		setTextureFile("/extrabiomes/extrabiomes.png");
	}
}
