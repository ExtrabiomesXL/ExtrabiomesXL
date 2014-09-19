/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class BlockRedRockSlab extends BlockSlab {
  public enum BlockType {
    REDCOBBLE(0), REDROCK(1), REDROCKBRICK(2);

    private final int metadata;

    BlockType(int metadata) {
      this.metadata = metadata;
    }

    public int metadata() {
      return metadata;
    }

    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }

  private static Block singleSlab = null;
  private IIcon[] textures = { null, null, null, null };

  public BlockRedRockSlab(boolean isDouble) {
    super(isDouble, Material.rock);
    if (!isDouble){
      singleSlab = this;
    }
    
    setHardness(2.0F);
    setResistance(10.0F);
    setStepSound(soundTypeStone);
    setLightOpacity(0);
    setCreativeTab(Extrabiomes.tabsEBXL);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister iconRegister) {
    textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockcobble");
    textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockbrick");
    textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockslabside");
    textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockslabtop");
  }

  @Override
  protected ItemStack createStackedBlock(int metadata) {
    return new ItemStack(singleSlab, 2, metadata & 7);
  }

  @Override
  public IIcon getIcon(int side, int metadata) {
    metadata &= 7;
    return metadata == BlockType.REDROCK.metadata() ? side < 2 ? textures[3] : textures[2] : metadata == BlockType.REDROCKBRICK.metadata() ? textures[1] : textures[0];
  }
  
  /* Was called getFullSlabName in pre 1.7 */
  @Override
  public String func_150002_b(int metadata) {
    String blockStepType;
    switch (metadata & 7) {
    case 1:
      blockStepType = BlockType.REDROCK.toString();
      break;
    case 2:
      blockStepType = BlockType.REDROCKBRICK.toString();
      break;
    default:
      blockStepType = BlockType.REDCOBBLE.toString();
    }

    return super.getUnlocalizedName() + "." + blockStepType;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item item, CreativeTabs tab, List itemList) {
    if (this == singleSlab)
      for (final BlockType type : BlockType.values())
        itemList.add(new ItemStack(item, 1, type.metadata()));
  }

  @Override
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    return ItemBlock.getItemFromBlock(singleSlab);
  }
  

  /**
   * Gets an item for the block being called on. Args: world, x, y, z
   */
  @SideOnly(Side.CLIENT)
  @Override
  public Item getItem(World world, int x, int y, int z) {
    return Item.getItemFromBlock(this);
  }
}
