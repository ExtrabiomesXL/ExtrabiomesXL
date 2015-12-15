package extrabiomes.items;

import java.util.Locale;

import extrabiomes.blocks.BlockMoreLeaves;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemCustomMoreLeaves extends ItemCustomLeaves
{
    
    public ItemCustomMoreLeaves(Block block)
    {
        super(block);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        int metadata = unmarkedMetadata(itemstack.getItemDamage());
        BlockMoreLeaves.BlockType[] validTypes = BlockMoreLeaves.BlockType.values();
        if (metadata >= validTypes.length )
            metadata = validTypes.length-1;
        itemstack = itemstack.copy();
        itemstack.setItemDamage(metadata);
        return super.getUnlocalizedName() + "." + validTypes[metadata].toString().toLowerCase(Locale.ENGLISH);
    }
    
}
