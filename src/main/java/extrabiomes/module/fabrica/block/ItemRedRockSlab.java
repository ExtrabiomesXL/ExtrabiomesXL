/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

import com.google.common.base.Optional;

public class ItemRedRockSlab extends ItemSlab
{
    
    private static Optional<BlockSlab> singleSlab = Optional.absent();
    private static Optional<BlockSlab> doubleSlab = Optional.absent();
    
    static void setSlabs(BlockSlab singleSlab, BlockSlab doubleSlab)
    {
        ItemRedRockSlab.singleSlab = Optional.of(singleSlab);
        ItemRedRockSlab.doubleSlab = Optional.of(doubleSlab);
    }
    
    public ItemRedRockSlab(Block block)
    {
        super(block, singleSlab.get(), doubleSlab.get(), block.equals(doubleSlab.get()));
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return singleSlab.get().func_150002_b(itemStack.getItemDamage());
    }
    
}
