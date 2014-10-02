package extrabiomes.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockCustomFenceGate extends BlockFenceGate {
  private String textureName;
  private IIcon texture;
  
  public BlockCustomFenceGate(String name)   {
    super();
    
    this.setHardness(2.0F);
    this.setResistance(5.0F);
    this.setBlockName("extrabiomes.fencegate." + name);
    this.textureName = "planks" + name;
    setCreativeTab(Extrabiomes.tabsEBXL);
  }
  
  @Override
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister iconRegister) {
      texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + textureName);
  }

  /**
   * Gets the block's texture. Args: side, meta
   */
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
      return texture;
  }

}
