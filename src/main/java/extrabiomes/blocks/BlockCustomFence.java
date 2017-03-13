package extrabiomes.blocks;

import java.util.List;
import java.util.Locale;

import extrabiomes.Extrabiomes;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCustomFence extends BlockFence {
  
  public enum BlockType implements IStringSerializable {
    Acacia(2),
    Autumn(6),
    Baldcypress(7),
    Cypress(3),
    Fir(1),
    JapaneseMaple(4),
    RainbowEucalyptus(5),
    Redwood(0),
    Sakura(8);
    
	private final String name; 
    private final int metadata;
    //public IIcon texture = null;
    
    BlockType(int metadata) {
    	this.name = name().toLowerCase(Locale.ENGLISH);
      this.metadata = metadata;
    }
    
    @Override
	public String getName() {
		return name;
	}
    
    public int getMetadata() {
      return metadata;
    }
    
    public static BlockType getDefault() {
    	return Redwood;
    }
    
    public static final BlockType[] VALUES = values();
  }
  
  public static final PropertyEnum<BlockType> FENCE_TYPE = PropertyEnum.<BlockType>create("type", BlockType.class, BlockType.VALUES);
  /*private IIcon[] textures;
  private int renderId;*/
  
  public BlockCustomFence() {
    super(/*"fence", */Material.WOOD, Material.WOOD.getMaterialMapColor());
    
    setDefaultState(getDefaultState().withProperty(FENCE_TYPE, BlockType.getDefault()));
    this.setHardness(2.0F);
    this.setResistance(5.0F);
    this.setUnlocalizedName("extrabiomes.fence");
    this.disableStats();
    this.setCreativeTab(Extrabiomes.tabsEBXL);
  }
  
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FENCE_TYPE).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (meta < 0 || meta >= BlockType.VALUES.length) {
			return getDefaultState(); //Invalid meta
		} else {
			return getDefaultState().withProperty(FENCE_TYPE, BlockType.VALUES[meta]);
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FENCE_TYPE, NORTH, EAST, WEST, SOUTH);
	}
  
  /*@Override
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister iconRegister) {
    textures = new IIcon[BlockType.values().length];
    
    for (final BlockType blockType : BlockType.values()) {
      blockType.texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planks" + blockType.name().toLowerCase(Locale.ENGLISH));
    }
  }
  
  @Override
  public IIcon getIcon(int side, int metadata) {
      return BlockType.values()[metadata].texture;
  }*/
  
  @Override
  public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
	  return true;
  }
  
  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> itemList) {
    for (final BlockType blockType : BlockType.VALUES) {
      itemList.add(new ItemStack(this, 1, blockType.getMetadata()));
    }
  }

  /*public void setRenderId(int renderId) {
      this.renderId = renderId;
  }
  
  @Override
  public int getRenderType() {
      return renderId;
  }*/

  /**
   * Returns true if the specified block can be connected by a fence
   */
  @Override
  public boolean canConnectTo(IBlockAccess world, BlockPos pos) {
      /*Block block = blockAccess.getBlock(x, y, z);
      
      boolean flag = !(block instanceof BlockFenceGate);
      
      return block != this && flag ? (block.getMaterial().isOpaque() && block.renderAsNormalBlock() ? block.getMaterial() != Material.GOURD : false) : true;*/
	  return super.canConnectTo(world, pos); //TODO: Check if anything different to normal fences has to be done
  }
  

  @Override
  public int damageDropped(IBlockState state) {
    return state.getValue(FENCE_TYPE).getMetadata();
  }
}
