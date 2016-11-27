/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.items;

import extrabiomes.blocks.BlockEBXLLeaves;
import extrabiomes.utility.MultiItemBlock;
import net.minecraft.item.ItemStack;

public class ItemEBXLLeaves extends MultiItemBlock {
	protected static final int METADATA_USERPLACEDBIT = 0x4;
	protected static final int METADATA_BITMASK = 0x3;
	
	public ItemEBXLLeaves(BlockEBXLLeaves<?> block) {
		super(block);

	}

	@Override
	public int getMetadata(int damage) {
		return damage | METADATA_USERPLACEDBIT;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + '.' + ((BlockEBXLLeaves<?>) block).type.getForMeta(stack.getItemDamage() & METADATA_BITMASK).getName();
	}
}