package net.minecraft.src;

public class ItemFirSapling extends Item
{
    private int spawnID;

    public ItemFirSapling(int par1, Block par2Block)
    {
        super(par1);
        spawnID = mod_ExtraBiomesXL.firSapling.blockID;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7)
    {
        int i = par3World.getBlockId(par4, par5, par6);

        if (i == Block.snow.blockID)
        {
            par7 = 1;
        }
        else if (i != Block.vine.blockID && i != Block.tallGrass.blockID && i != Block.deadBush.blockID)
        {
            if (par7 == 0)
            {
                par5--;
            }

            if (par7 == 1)
            {
                par5++;
            }

            if (par7 == 2)
            {
                par6--;
            }

            if (par7 == 3)
            {
                par6++;
            }

            if (par7 == 4)
            {
                par4--;
            }

            if (par7 == 5)
            {
                par4++;
            }
        }

        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6))
        {
            return false;
        }

        if (par1ItemStack.stackSize == 0)
        {
            return false;
        }

        if (par3World.canBlockBePlacedAt(spawnID, par4, par5, par6, false, par7))
        {
            Block block = Block.blocksList[spawnID];

            if (par3World.setBlockWithNotify(par4, par5, par6, spawnID))
            {
                if (par3World.getBlockId(par4, par5, par6) == spawnID)
                {
                    Block.blocksList[spawnID].onBlockPlaced(par3World, par4, par5, par6, par7);
                    Block.blocksList[spawnID].onBlockPlacedBy(par3World, par4, par5, par6, par2EntityPlayer);
                }

                par3World.playSoundEffect((float)par4 + 0.5F, (float)par5 + 0.5F, (float)par6 + 0.5F, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                par1ItemStack.stackSize--;
            }
        }

        return true;
    }
}
