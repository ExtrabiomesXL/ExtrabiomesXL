/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import extrabiomes.Extrabiomes;
import extrabiomes.utility.IDRestrictionAnnotation;

@IDRestrictionAnnotation(maxIDRValue = 255)
public class GenericTerrainBlock extends Block {

    public GenericTerrainBlock(int id, int index, Material material) {
        super(id, index, material);
    }
}
