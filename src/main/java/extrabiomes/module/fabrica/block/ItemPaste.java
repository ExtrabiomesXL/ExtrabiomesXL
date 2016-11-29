package extrabiomes.module.fabrica.block;

import java.util.List;

import extrabiomes.Extrabiomes;
import extrabiomes.helpers.ToolTipStringFormatter;
import extrabiomes.lib.ITextureRegisterer;
import extrabiomes.utility.ModelUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPaste extends Item implements ITextureRegisterer
{
    
    public ItemPaste()
    {
        super();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerTexture()
    {
    	ModelUtil.registerTexture(this, "cactuspaste");
    }

    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List<String> listOfLines, boolean sneaking) {
      String line = Extrabiomes.proxy.translate(this.getUnlocalizedName() + ".description");
      
      if(!line.equals(this.getUnlocalizedName() + ".description")) {
        if(listOfLines.size() > 0 && listOfLines.get(0).length() > 20) {
          ToolTipStringFormatter.Format(line, listOfLines, listOfLines.get(0).length() + 5);
        } else {
          ToolTipStringFormatter.Format(line, listOfLines);
        }
      }
    }
}
