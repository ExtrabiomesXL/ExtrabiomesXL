package extrabiomes.module.fabrica.block;

import java.util.List;
import java.util.Locale;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.helpers.ToolTipStringFormatter;

public class ItemPaste extends Item
{
    
    public ItemPaste()
    {
        super();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "cactuspaste");
    }

    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List listOfLines, boolean sneaking) {
      String line = LanguageRegistry.instance().getStringLocalization(this.getUnlocalizedName() + ".description");
      
      if(!line.equals(this.getUnlocalizedName() + ".description")) {
        if(listOfLines.size() > 0 && ((String)listOfLines.get(0)).length() > 20) {
          ToolTipStringFormatter.Format(line, listOfLines, ((String)listOfLines.get(0)).length() + 5);
        } else {
          ToolTipStringFormatter.Format(line, listOfLines);
        }
      }
    }
}
