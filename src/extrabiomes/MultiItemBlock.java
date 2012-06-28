package extrabiomes;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class MultiItemBlock extends ItemBlock {

	public MultiItemBlock(int id) {
		super(id);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);

		if (!(getBlock() instanceof MultiBlock))
			throw new IllegalArgumentException(
					"MultiItemBlock must be paired with a MultiBlock.");
	}

	Block getBlock() {
		return Block.blocksList[getBlockID()];
	}

	@Override
	public int getColorFromDamage(int md, int par2) {
		return getBlock().getRenderColor(md);
	}

	@Override
	public int getIconFromDamage(int md) {
		return getBlock().getBlockTextureFromSideAndMetadata(0, md);
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return ((MultiBlock) getBlock()).getNameFromMetadata(itemstack
				.getItemDamage());
	}

	@Override
	public int getMetadata(int md) {
		return md;
	}
}
