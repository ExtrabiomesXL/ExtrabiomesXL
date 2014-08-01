/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import extrabiomes.Extrabiomes;

public class BlockCustomStairs extends BlockStairs
{
    
    public BlockCustomStairs(Block modelBlock, int modelMetadata)
    {
        super(modelBlock, modelMetadata);
        setLightOpacity(0);
        setCreativeTab(Extrabiomes.tabsEBXL);
    }
    
}
