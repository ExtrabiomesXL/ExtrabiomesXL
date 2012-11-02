/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.utility;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class MultiItemBlock extends ItemBlock {

	public MultiItemBlock(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	private Block getBlock() {
		return Block.blocksList[getBlockID()];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack itemstack, int par2) {
		return getBlock().getRenderColor(itemstack.getItemDamage());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getIconFromDamage(int md) {
		return getBlock().getBlockTextureFromSideAndMetadata(0, md);
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return super.getItemNameIS(itemstack) + "."
				+ itemstack.getItemDamage();
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
}
