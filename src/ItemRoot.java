package net.minecraft.src;

public class ItemRoot extends Item
{
    private int spawnID;

    public ItemRoot(int i, Block block)
    {
        super(i);
        spawnID = mod_ExtraBiomesXL.root.blockID;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        if (l == 0)
        {
            return false;
        }
        if (!world.getBlockMaterial(i, j, k).isSolid())
        {
            return false;
        }
        if (l == 1)
        {
            j++;
        }
        if (l == 2)
        {
            k--;
        }
        if (l == 3)
        {
            k++;
        }
        if (l == 4)
        {
            i--;
        }
        if (l == 5)
        {
            i++;
        }
        
        if (!entityplayer.canPlayerEdit(i, j, k))
        {
            return false;
        }
        if (itemstack.stackSize == 0)
        {
            return false;
        }
        if (world.canBlockBePlacedAt(spawnID, i, j, k, false, l))
        {
            Block block = Block.blocksList[spawnID];
            if (world.setBlockWithNotify(i, j, k, spawnID))
            {
                if (world.getBlockId(i, j, k) == spawnID)
                {
                    Block.blocksList[spawnID].onBlockPlaced(world, i, j, k, l);
                    Block.blocksList[spawnID].onBlockPlacedBy(world, i, j, k, entityplayer);
                }
                world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                itemstack.stackSize--;
            }
        }
        return true;
    }
}