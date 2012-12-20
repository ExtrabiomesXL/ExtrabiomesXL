/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import extrabiomes.utility.IDRestrictionAnnotation;

@IDRestrictionAnnotation(maxIDRValue = 255)
public class GenericTerrainBlock extends Block {

    public GenericTerrainBlock(int id, int index, Material material) {
        super(id, index, material);
    }
}
