/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class BlockLeafPile extends Block
{
    
    static private boolean canThisPlantGrowOnThisBlock(Block block)
    {
        return block.equals(Blocks.grass) || block.equals(Blocks.dirt);
    }
    
    private IIcon texture;
    
    public BlockLeafPile(int index, Material material)
    {
        super(material);
        final float f = 0.5F;
        final float f1 = 0.015625F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leafpile");
    }
    
    @Override
    //Change this to more appropriate method
    public IIcon getIcon(int side, int metadata)
    {
        return texture;
    }
    
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z));
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z));
    }
    
    private void checkFlowerChange(World world, int x, int y, int z)
    {
        if (!canBlockStay(world, x, y, z))
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, Blocks.air);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess iBlockAccess, int x, int y, int z)
    {
        return iBlockAccess.getBiomeGenForCoords(x, z).getBiomeFoliageColor(x, y, z);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return ColorizerFoliage.getFoliageColorBasic();
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int metadata)
    {
        return getBlockColor();
    }
    
    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        super.onNeighborBlockChange(world, x, y, z, neighbor);
        checkFlowerChange(world, x, y, z);
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        checkFlowerChange(world, x, y, z);
    }
}
