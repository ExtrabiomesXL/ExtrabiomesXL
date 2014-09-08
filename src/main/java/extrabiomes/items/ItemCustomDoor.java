package extrabiomes.items;

import extrabiomes.Extrabiomes;
import extrabiomes.module.fabrica.block.BlockCustomWoodDoor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemCustomDoor extends Item {
	private Block block;

	public ItemCustomDoor(Block block) {
		this.block = block;
		((BlockCustomWoodDoor) block).setItem(this);
		this.setCreativeTab(Extrabiomes.tabsEBXL);
	}
	
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + ((BlockCustomWoodDoor) block).getTextureName());
    }
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (p_77648_7_ != 1) {
            return false;
        } else {
            ++y;

            if (player.canPlayerEdit(x, y, z, p_77648_7_, itemStack) && player.canPlayerEdit(x, y + 1, z, p_77648_7_, itemStack)) {
                if (!block.canPlaceBlockAt(world, x, y, z)) {
                    return false;
                } else {
                    int direction = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                    placeDoorBlock(world, x, y, z, direction, block);
                    --itemStack.stackSize;
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public static void placeDoorBlock(World world, int x, int y, int z, int direction, Block block) {
        byte off_x = 0;
        byte off_y = 0;

        if (direction == 0) {
            off_y = 1;
        }

        if (direction == 1) {
            off_x = -1;
        }

        if (direction == 2) {
            off_y = -1;
        }

        if (direction == 3) {
            off_x = 1;
        }

        int i1 = (world.getBlock(x - off_x, y, z - off_y).isNormalCube() ? 1 : 0) + (world.getBlock(x - off_x, y + 1, z - off_y).isNormalCube() ? 1 : 0);
        int j1 = (world.getBlock(x + off_x, y, z + off_y).isNormalCube() ? 1 : 0) + (world.getBlock(x + off_x, y + 1, z + off_y).isNormalCube() ? 1 : 0);
        boolean flag = world.getBlock(x - off_x, y, z - off_y) == block || world.getBlock(x - off_x, y + 1, z - off_y) == block;
        boolean flag1 = world.getBlock(x + off_x, y, z + off_y) == block || world.getBlock(x + off_x, y + 1, z + off_y) == block;
        boolean flag2 = false;

        if (flag && !flag1)
        {
            flag2 = true;
        }
        else if (j1 > i1)
        {
            flag2 = true;
        }

        world.setBlock(x, y, z, block, direction, 2);
        world.setBlock(x, y + 1, z, block, 8 | (flag2 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, z, block);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, block);
    }
    
    /**
     * Returns the unlocalized name of this item.
     */
    @Override
    public String getUnlocalizedName() {
        return ((BlockCustomWoodDoor) block).getUnlocalizedName();
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return ((BlockCustomWoodDoor) block).getUnlocalizedName();
    }
}
