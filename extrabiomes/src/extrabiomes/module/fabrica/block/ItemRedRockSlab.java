/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

import com.google.common.base.Optional;

public class ItemRedRockSlab extends ItemSlab {

	private static Optional<BlockHalfSlab>	singleSlab	= Optional
																.absent();
	private static Optional<BlockHalfSlab>	doubleSlab	= Optional
																.absent();

	static void setSlabs(BlockHalfSlab singleSlab,
			BlockHalfSlab doubleSlab)
	{
		ItemRedRockSlab.singleSlab = Optional.of(singleSlab);
		ItemRedRockSlab.doubleSlab = Optional.of(doubleSlab);
	}

	public ItemRedRockSlab(int id) {
		super(id, singleSlab.get(), doubleSlab.get(), id == doubleSlab
				.get().blockID);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return singleSlab.get().getFullSlabName(
				itemStack.getItemDamage());
	}

}
