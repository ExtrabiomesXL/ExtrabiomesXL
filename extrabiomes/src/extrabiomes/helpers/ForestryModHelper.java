/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.src.ItemStack;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public abstract class ForestryModHelper {

    private static List<ItemStack> germlings = new ArrayList();
    private static List<ItemStack> leaves    = new ArrayList();
    private static List<ItemStack> saplings  = new ArrayList();

    public static Collection<ItemStack> getForestryBackPackItems() {
        final List<ItemStack> list = Lists.newArrayList(leaves);
        list.addAll(saplings);
        return ImmutableList.copyOf(list);
    }

    public static Collection<ItemStack> getLeaves() {
        return ImmutableList.copyOf(leaves);
    }

    public static boolean isGermling(ItemStack stack) {
        // To enforce uniqueness in the List (should be a Set, but
        // ItemStack.equals() is not defined. -.-
        for (final ItemStack is : germlings)
            if (ItemStack.areItemStacksEqual(is, stack)) return true;
        return false;
    }

    public static void registerGermling(ItemStack stack) {

        // To enforce uniqueness in the List (should be a Set, but
        // ItemStack.equals() is not defined. -.-
        for (final ItemStack is : germlings)
            if (ItemStack.areItemStacksEqual(is, stack)) return;

        germlings.add(stack);
    }

    public static void registerLeaves(ItemStack stack) {

        // To enforce uniqueness in the List (should be a Set, but
        // ItemStack.equals() is not defined. -.-
        for (final ItemStack is : leaves)
            if (ItemStack.areItemStacksEqual(is, stack)) return;

        leaves.add(stack);
    }

    public static void registerSapling(ItemStack stack) {

        // To enforce uniqueness in the List (should be a Set, but
        // ItemStack.equals() is not defined. -.-
        for (final ItemStack is : saplings)
            if (ItemStack.areItemStacksEqual(is, stack)) return;

        saplings.add(stack);
    }

    public static Collection<ItemStack> getSaplings() {
        return ImmutableList.copyOf(saplings);
    }

}
