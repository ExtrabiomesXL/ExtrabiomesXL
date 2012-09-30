/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.block;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import extrabiomes.utility.IDRestrictionAnnotation;

@IDRestrictionAnnotation(maxIDRValue = 255)
class BlockCrackedSand extends Block {

	BlockCrackedSand(int id)
	{
		super(id, 0, Material.rock);
		setHardness(1.2F);
		setStepSound(Block.soundStoneFootstep);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(CreativeTabs.tabBlock);
	}
}
