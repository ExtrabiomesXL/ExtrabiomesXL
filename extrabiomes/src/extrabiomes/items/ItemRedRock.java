package extrabiomes.items;

import net.minecraft.item.ItemStack;
import extrabiomes.utility.MultiItemBlock;

public class ItemRedRock extends MultiItemBlock {

    public ItemRedRock(int id) {
        super(id);
    }

    @Override
    public String getItemNameIS(ItemStack itemstack) {
        int metadata = itemstack.getItemDamage();
        if (metadata > 2) metadata = 2;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getItemNameIS(itemstack);
    }

}
