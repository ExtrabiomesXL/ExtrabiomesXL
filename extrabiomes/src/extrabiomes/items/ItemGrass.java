package extrabiomes.items;

import net.minecraft.src.ItemStack;
import extrabiomes.utility.MultiItemBlock;

public class ItemGrass extends MultiItemBlock {

    public ItemGrass(int id) {
        super(id);
    }

    @Override
    public String getItemNameIS(ItemStack itemstack) {
        int metadata = itemstack.getItemDamage();
        if (metadata > 4) metadata = 4;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getItemNameIS(itemstack);
    }

}
