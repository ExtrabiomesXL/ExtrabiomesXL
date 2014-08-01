/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import extrabiomes.Extrabiomes;

public class BlockWoodStairs extends BlockCustomStairs
{
    
    public BlockWoodStairs(Block modelBlock, int modelMetadata)
    {
        super(modelBlock, modelMetadata);
        //Blocks.fire.setFireInfo(this, 5, 20);
        setCreativeTab(Extrabiomes.tabsEBXL);
    }
    
}
