/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.buildcraft;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLInterModComms;

public abstract class FacadeHelper
{
    
    public static void addBuildcraftFacade(Block block, int metadata)
    {
        FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", String.format("%s@%d", Block.blockRegistry.getNameForObject(block), metadata));
    }
    
    public static void addBuildcraftFacade(Block block)
    {
        addBuildcraftFacade(block, 0);
    }
    
}
