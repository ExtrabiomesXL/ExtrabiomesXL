package extrabiomes.module.fabrica.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomWoodDoor extends BlockDoor {
    private static int blockId;
    @SideOnly(Side.CLIENT)
    private IIcon[] icon_upper;
    @SideOnly(Side.CLIENT)
    private IIcon[] icon_lower;

	protected BlockCustomWoodDoor(String doorType) {
		super(Material.wood);

		// Default Settings
		setBlockName("extrabiomes.door." + doorType);
    setBlockTextureName("door_" + doorType);
		setHardness(3.0F);
		setStepSound(soundTypeWood);
		disableStats();
		setCreativeTab(Extrabiomes.tabsEBXL);
	}
	
	@Override
	public String getTextureName() {
		return textureName;
	}

  @SideOnly(Side.CLIENT)
  @Override
  public void registerBlockIcons(IIconRegister iconRegister) {
    icon_upper = new IIcon[2];
    icon_lower = new IIcon[2];
    icon_upper[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + getTextureName() + "_upper");
    icon_lower[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + getTextureName() + "_lower");
    icon_upper[1] = new IconFlipped(icon_upper[0], true, false);
    icon_lower[1] = new IconFlipped(icon_lower[0], true, false);
  }

  /**
   * Gets the block's texture. Args: side, meta
   */
  @SideOnly(Side.CLIENT)
  @Override
  public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
    return this.icon_lower[0];
  }
  
  @SideOnly(Side.CLIENT)
  @Override
  public IIcon getIcon(IBlockAccess access, int x, int y, int z, int direction) {
    if (direction != 1 && direction != 0) {
      int i1 = this.func_150012_g(access, x, y, z);
      int j1 = i1 & 3;
      boolean flag = (i1 & 4) != 0;
      boolean flag1 = false;
      boolean flag2 = (i1 & 8) != 0;

      if (flag) {
        if (j1 == 0 && direction == 2) {
          flag1 = !flag1;
        } else if (j1 == 1 && direction == 5) {
          flag1 = !flag1;
        } else if (j1 == 2 && direction == 3) {
          flag1 = !flag1;
        } else if (j1 == 3 && direction == 4) {
          flag1 = !flag1;
        }
      } else {
        if (j1 == 0 && direction == 5) {
          flag1 = !flag1;
        } else if (j1 == 1 && direction == 3) {
          flag1 = !flag1;
        } else if (j1 == 2 && direction == 4) {
          flag1 = !flag1;
        } else if (j1 == 3 && direction == 2) {
          flag1 = !flag1;
        }

        if ((i1 & 16) != 0) {
          flag1 = !flag1;
        }
      }

      return flag2 ? this.icon_upper[flag1?1:0] : this.icon_lower[flag1?1:0];
    } else {
      return this.icon_lower[0];
    }
  }
    
  @Override
  public Item getItemDropped(int metadata, Random rand, int p_149650_3_) {
    return (metadata & 8) != 0 ? null : Item.getItemFromBlock(this);
  }

  /**
   * Gets an item for the block being called on. Args: world, x, y, z
   */
  @SideOnly(Side.CLIENT)
  @Override
  public Item getItem(World world, int x, int y, int z) {
    return Item.getItemFromBlock(this);
  }

  public static void setRenderId(int registerBlockHandler) {
    blockId = registerBlockHandler;
  }
  
  /**
   * The type of render function that is called for this block
   */
  @Override
  public int getRenderType() {
    return blockId;
  }
}
