
package extrabiomes;

import net.minecraft.server.Block;
import net.minecraft.server.ItemBlock;
import net.minecraft.server.ItemStack;

public class MultiItemBlock extends ItemBlock {
	public MultiItemBlock(int i) {
		super(i);
		setMaxDurability(0);
		a(true);
	}

	@Override
	public String a(ItemStack itemstack) {
		return new StringBuilder().append("tile.")
				.append(Block.byId[a()].q()).append(".")
				.append(itemstack.getData()).toString();
	}

	/**
	 * Returns the metadata of the block which this Item (ItemBlock) can
	 * place
	 */
	@Override
	public int filterData(int i) {
		return i;
	}

	Block getBlock() {
		return Block.byId[a()];
	}
}
