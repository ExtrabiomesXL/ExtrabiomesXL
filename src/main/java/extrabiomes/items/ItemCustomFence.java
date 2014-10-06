package extrabiomes.items;

import java.util.List;
import java.util.Locale;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import extrabiomes.blocks.BlockCustomFence;
import extrabiomes.utility.MultiItemBlock;

public class ItemCustomFence extends MultiItemBlock {
  public ItemCustomFence(Block block) {
      super(block);
  }
  
  private static int unmarkedMetadata(int metadata) {
      return metadata < BlockCustomFence.BlockType.values().length ? metadata : 0;
  }
  
  @Override
  public String getUnlocalizedName(ItemStack itemstack) {
      int metadata = unmarkedMetadata(itemstack.getItemDamage());
      itemstack = itemstack.copy();
      itemstack.setItemDamage(metadata);
      return super.getUnlocalizedName() + "." + BlockCustomFence.BlockType.values()[metadata].toString().toLowerCase(Locale.ENGLISH);
      //return super.getUnlocalizedName(itemstack);
  }
  
  @SideOnly(Side.CLIENT)
  @Override
  public IIcon getIconFromDamage(int metadata) {
      return this.field_150939_a.getIcon(0, metadata);
  }
}
