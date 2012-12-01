/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.core.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import net.minecraft.src.ItemStack;

public abstract class ForestryModHelper {

    private static List<ItemStack> leaves = new ArrayList();

    public static void registerLeaves(ItemStack stack) {

        // To enforce uniqueness in the List (should be a Set, but
        // ItemStack.equals() is not defined. -.-
        for (final ItemStack is : leaves)
            if (ItemStack.areItemStacksEqual(is, stack)) return;

        leaves.add(stack);
    }
    
    public static Collection<ItemStack> getLeaves() {
        return ImmutableList.copyOf(leaves);
    }
    
    public static Collection<ItemStack> getForestryBackPackItems() {
        List<ItemStack> list = Lists.newArrayList(leaves);
        return ImmutableList.copyOf(list);
    }

}
