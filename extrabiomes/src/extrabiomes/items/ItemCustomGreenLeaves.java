/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.items;

import net.minecraft.item.ItemStack;

public class ItemCustomGreenLeaves extends ItemCustomLeaves {

    public ItemCustomGreenLeaves(int id) {
        super(id);
    }

    @Override
    public String getItemNameIS(ItemStack itemstack) {
        int metadata = unmarkedMetadata(itemstack.getItemDamage());
        if (metadata > 2) metadata = 0;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getItemNameIS(itemstack);
    }

}
