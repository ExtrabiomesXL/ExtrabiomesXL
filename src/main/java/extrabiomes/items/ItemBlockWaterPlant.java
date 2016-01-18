/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockWaterPlant;
import extrabiomes.helpers.LogHelper;

public class ItemBlockWaterPlant extends ItemBlock
{
	private Block block;
    public ItemBlockWaterPlant(Block block)
    {
        super(block);
        this.block = block;
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemstack, int par2)
    {
        return block.getRenderColor(itemstack.getItemDamage());
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int md)
    {
        return block.getIcon(0, md);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
    	// NB: BlockWaterPlant handles the safety net, so we can't check here
        return ((BlockWaterPlant) block).getLocalizedName(itemStack.getItemDamage());
    }
    
    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List listOfLines, boolean sneaking) {
    	((BlockWaterPlant)Block.getBlockFromItem(itemForTooltip.getItem())).addInformation(itemForTooltip.getItemDamage(), listOfLines);
    }
}
