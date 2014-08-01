/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class BlockCatTail extends BlockFlower
{
    
    private IIcon texture;
    
    public BlockCatTail(int index, Material material)
    {
        super(0);
        disableStats();
        final float f = 0.375F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "cattail");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return texture;
    }
    
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return canPlaceBlockAt(world, x, y, z);
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        final Block block = world.getBlock(x, y - 1, z);
        
        if (!block.equals(Blocks.grass) && !block.equals(Blocks.dirt))
            return false;
        
        y--;
        
        for (int offset = -1; offset < 2; offset += 2)
            if (world.getBlock(x + offset, y, z).getMaterial() == Material.water
                    || world.getBlock(x, y, z + offset).getMaterial() == Material.water)
                return true;
        
        return false;
    }
    
    @Override
    public int getRenderType()
    {
        return 6;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        if (!canBlockStay(world, x, y, z))
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, Blocks.air);
        }
    }
}
