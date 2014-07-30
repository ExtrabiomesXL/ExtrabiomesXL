/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.utility.IDRestrictionAnnotation;

@IDRestrictionAnnotation(maxIDRValue = 255)
public class GenericTerrainBlock extends Block
{
    
    public GenericTerrainBlock(int index, Material material)
    {
        super(material);
    }
    
    public IIcon   texture;
    
    public String texturePath;
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + texturePath);
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return texture;
    }
}
