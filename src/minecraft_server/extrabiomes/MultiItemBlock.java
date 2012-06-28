package extrabiomes;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class MultiItemBlock extends ItemBlock {

	public MultiItemBlock(int id) {
		super(id);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	Block getBlock() {
		return Block.blocksList[getBlockID()];
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return "tile." + Block.blocksList[getBlockID()].getBlockName() + "."
				+ itemstack.getItemDamage();
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
}
