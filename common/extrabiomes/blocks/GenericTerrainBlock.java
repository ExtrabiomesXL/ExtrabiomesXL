/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import extrabiomes.Extrabiomes;
import extrabiomes.utility.IDRestrictionAnnotation;

@IDRestrictionAnnotation(maxIDRValue = 255)
public class GenericTerrainBlock extends Block {

    public GenericTerrainBlock(int id, int index, Material material) {
        super(id, material);
    }
    
    public Icon texture;
    
    public String texturePath;
    
    @Override
    public void registerIcons(IconRegister iconRegister){
    	texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + texturePath);
    }
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return texture;
	}
}
