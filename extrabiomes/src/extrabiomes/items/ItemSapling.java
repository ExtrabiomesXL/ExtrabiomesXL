package extrabiomes.items;

import net.minecraft.src.ItemStack;
import extrabiomes.utility.MultiItemBlock;

public class ItemSapling extends MultiItemBlock {

    public ItemSapling(int id) {
        super(id);
    }

    private static int unmarkedMetadata(int metadata) {
        return metadata & METADATA_BITMASK;
    }

    private static final int METADATA_BITMASK = 0x7;

    @Override
    public String getItemNameIS(ItemStack itemstack) {
        int metadata = unmarkedMetadata(itemstack.getItemDamage());
        if (metadata > 6) metadata = 0;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getItemNameIS(itemstack);
    }

}
