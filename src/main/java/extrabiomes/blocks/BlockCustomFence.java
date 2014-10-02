package extrabiomes.blocks;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.google.common.base.Optional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.module.fabrica.block.BlockCustomWall.BlockType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomFence extends BlockFence {
  
  public enum BlockType   {
    Acacia(2),
    Autumn(6),
    Baldcypress(7),
    Cypress(3),
    Fir(1),
    JapaneseMaple(4),
    RainbowEucalyptus(5),
    Redwood(0),
    Sakura(8);
    
    private final int metadata;
    public IIcon texture = null;
    
    BlockType(int metadata) {
      this.metadata = metadata;
    }
    
    public int metadata() {
      return metadata;
    }
  }
  
  private IIcon[] textures;
  private int renderId;
  
  public BlockCustomFence() {
    super("fence", Material.wood);
    this.setHardness(2.0F);
    this.setResistance(5.0F);
    this.setBlockName("extrabiomes.fence");
    this.disableStats();
    this.setCreativeTab(Extrabiomes.tabsEBXL);
  }
  
  @Override
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister iconRegister) {
    textures = new IIcon[BlockType.values().length];
    
    for (final BlockType blockType : BlockType.values()) {
      blockType.texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planks" + blockType.name().toLowerCase(Locale.ENGLISH));
    }
  }
  
  @Override
  public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
      return true;
  }
  
  @Override
  public IIcon getIcon(int side, int metadata) {
      return BlockType.values()[metadata].texture;
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item item, CreativeTabs tab, List itemList) {
    for (final BlockType blockType : BlockType.values()) {
      itemList.add(new ItemStack(this, 1, blockType.metadata()));
    }
  }

  public void setRenderId(int renderId) {
      this.renderId = renderId;
  }
  
  @Override
  public int getRenderType() {
      return renderId;
  }

  /**
   * Returns true if the specified block can be connected by a fence
   */
  @Override
  public boolean canConnectFenceTo(IBlockAccess blockAccess, int x, int y, int z) {
      Block block = blockAccess.getBlock(x, y, z);
      
      boolean flag = !(block instanceof BlockFenceGate);
      
      return block != this && flag ? (block.getMaterial().isOpaque() && block.renderAsNormalBlock() ? block.getMaterial() != Material.gourd : false) : true;
  }
  

  //public Item getItemDropped(int par1, Random par2Random, int par3)
  //{
  //    return ItemBlock.getItemFromBlock(this);
  //}
  @Override
  public int damageDropped(int metaData) {
    if(BlockType.values().length <= metaData) {
      return 0;
    }
    
    return metaData;
  }

  @SideOnly(Side.CLIENT)
  @Override
  public int getDamageValue(World world, int x, int y, int z) {
    return world.getBlockMetadata(x, y, z);
  }
}
