package extrabiomes.module.fabrica.block;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class ItemPaste extends Item
{
    
    public ItemPaste(int par1)
    {
        super(par1);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIIcons(IIconRegister IIconRegister)
    {
        itemIIcon = IIconRegister.registerIIcon(Extrabiomes.TEXTURE_PATH + "cactuspaste");
    }

    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List listOfLines, boolean sneaking) {
    	listOfLines.add("This green paste made from");
    	listOfLines.add("4 \u00A7oTiny Cacti\u00A7r\u00A77 can be smelted");
    	listOfLines.add("into \u00A72\u00A7oCactus Green\u00A7r\u00A77.");
    }
}
