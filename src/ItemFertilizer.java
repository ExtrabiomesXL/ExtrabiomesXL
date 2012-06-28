package net.minecraft.src;

import java.util.Random;

public class ItemFertilizer extends Item {
	
	public ItemFertilizer(int par1)
    {
        super(par1);
        setMaxDamage(0);
    }
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6))
        {
            return false;
        }
        {
            int i = par3World.getBlockId(par4, par5, par6);

            if (i == Block.sapling.blockID)
            {
                if (!par3World.isRemote)
                {
                    ((BlockSapling)Block.sapling).growTree(par3World, par4, par5, par6, par3World.rand);
                    par1ItemStack.stackSize--;
                }

                return true;
            }

            if (i == mod_ExtraBiomesXL.autumnSapling.blockID)
            {
                if (!par3World.isRemote)
                {
                    ((BlockAutumnSapling)mod_ExtraBiomesXL.autumnSapling).growTree(par3World, par4, par5, par6, par3World.rand);
                    par1ItemStack.stackSize--;
                }

                return true;
            }
            
            if (i == mod_ExtraBiomesXL.firSapling.blockID)
            {
                if (!par3World.isRemote)
                {
                    ((BlockFirSapling)mod_ExtraBiomesXL.firSapling).growTree(par3World, par4, par5, par6, par3World.rand);
                    par1ItemStack.stackSize--;
                }

                return true;
            }

            if (i == Block.mushroomBrown.blockID || i == Block.mushroomRed.blockID)
            {
                if (!par3World.isRemote && ((BlockMushroom)Block.blocksList[i]).fertilizeMushroom(par3World, par4, par5, par6, par3World.rand))
                {
                    par1ItemStack.stackSize--;
                }

                return true;
            }

            if (i == Block.melonStem.blockID || i == Block.pumpkinStem.blockID)
            {
                if (!par3World.isRemote)
                {
                    ((BlockStem)Block.blocksList[i]).fertilizeStem(par3World, par4, par5, par6);
                    par1ItemStack.stackSize--;
                }

                return true;
            }

            if (i == Block.crops.blockID)
            {
                if (!par3World.isRemote)
                {
                    ((BlockCrops)Block.crops).fertilize(par3World, par4, par5, par6);
                    par1ItemStack.stackSize--;
                }

                return true;
            }

            if (i == Block.grass.blockID)
            {
                if (!par3World.isRemote)
                {
                    par1ItemStack.stackSize--;
                    label0:

                    for (int j = 0; j < 128; j++)
                    {
                        int k = par4;
                        int l = par5 + 1;
                        int i1 = par6;

                        for (int j1 = 0; j1 < j / 16; j1++)
                        {
                            k += itemRand.nextInt(3) - 1;
                            l += ((itemRand.nextInt(3) - 1) * itemRand.nextInt(3)) / 2;
                            i1 += itemRand.nextInt(3) - 1;

                            if (par3World.getBlockId(k, l - 1, i1) != Block.grass.blockID || par3World.isBlockNormalCube(k, l, i1))
                            {
                                continue label0;
                            }
                        }

                        if (par3World.getBlockId(k, l, i1) != 0)
                        {
                            continue;
                        }

                        if (itemRand.nextInt(10) != 0)
                        {
                            par3World.setBlockWithNotify(k, l, i1, mod_ExtraBiomesXL.thickGrass.blockID);
                            continue;
                        }
                        
                        if (itemRand.nextInt(3) != 0)
                        {
                            par3World.setBlockWithNotify(k, l, i1, mod_ExtraBiomesXL.hydrangea.blockID);
                        }
                        else
                        {
                            par3World.setBlockWithNotify(k, l, i1, mod_ExtraBiomesXL.orangeFlower.blockID);
                        }
                    }
                }

                return true;
            }
        }

        return false;
    }
}