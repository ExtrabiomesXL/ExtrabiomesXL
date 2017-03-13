/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.block;

import extrabiomes.Extrabiomes;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

class BlockQuicksand extends Block
{
    
    BlockQuicksand()
    {
        super(Material.SAND);
        setHardness(4.0F);
        setSoundType(SoundType.SAND);
        setCreativeTab(Extrabiomes.tabsEBXL);
    }
    
    /*private IIcon texture;
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "quicksand");
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return texture;
    }*/
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
    {
    	return null;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return true;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        entity.setInWeb();
    }
}
