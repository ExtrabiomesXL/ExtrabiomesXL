/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.blocks.BlockWaterPlant;
import extrabiomes.helpers.LogHelper;

public class ItemBlockWaterPlant extends ItemBlock
{
    
    public ItemBlockWaterPlant(int id)
    {
        super(id);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    @SideOnly(Side.CLIENT)
    private Block getBlock()
    {
        return Block.blocksList[getBlockID()];
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemstack, int par2)
    {
        return getBlock().getRenderColor(itemstack.getItemDamage());
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int md)
    {
        return getBlock().getIcon(0, md);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return ((BlockWaterPlant) getBlock()).getLocalizedName(itemStack.getItemDamage());
    }
    
    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
}
