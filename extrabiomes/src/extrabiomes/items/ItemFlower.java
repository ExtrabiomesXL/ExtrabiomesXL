package extrabiomes.items;

import net.minecraft.item.ItemStack;
import extrabiomes.utility.MultiItemBlock;

public class ItemFlower extends MultiItemBlock {

    public ItemFlower(int id) {
        super(id);
    }

    @Override
    public String getItemNameIS(ItemStack itemstack) {
        int metadata = itemstack.getItemDamage();
        if (metadata > 7) metadata = 7;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getItemNameIS(itemstack);
    }

}
