/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

class BlockQuicksand extends Block
{
    
    BlockQuicksand()
    {
        super(Material.sand);
        setHardness(4.0F);
        setStepSound(Block.soundTypeSand);
        setCreativeTab(Extrabiomes.tabsEBXL);
    }
    
    private IIcon texture;
    
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
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,
            int x, int y, int z)
    {
        return null;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return true;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y,
            int z, Entity entity)
    {
        entity.setInWeb();
    }
}
