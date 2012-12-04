/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.buildcraft;

import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.event.FMLInterModComms;

public abstract class FacadeHelper {

    public static void addBuildcraftFacade(int blockID) {
        addBuildcraftFacade(blockID, 0);
    }

    public static void addBuildcraftFacade(int blockID, int metadata) {
        FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade",
                String.format("%d@%d", blockID, metadata));
    }

    public static void addBuildcraftFacade(ItemStack stack) {
        addBuildcraftFacade(stack.itemID, stack.getItemDamage());
    }

}
