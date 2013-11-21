/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.google.common.collect.ImmutableList;

public abstract class ForestryModHelper
{
    
    private static List<ItemStack> backpackDigger   = new ArrayList<ItemStack>();
    private static List<ItemStack> backpackForester = new ArrayList<ItemStack>();
    private static List<ItemStack> basicFlowers     = new ArrayList<ItemStack>();
    private static List<ItemStack> germlings        = new ArrayList<ItemStack>();
    private static List<ItemStack> leaves           = new ArrayList<ItemStack>();
    private static List<ItemStack> saplings         = new ArrayList<ItemStack>();
    
    public static void addToForesterBackpack(ItemStack stack)
    {
        backpackForester.add(stack);
    }
    
    public static void addToDiggerBackpack(ItemStack stack)
    {
        backpackDigger.add(stack);
    }
    
    public static Collection<ItemStack> getBasicFlowers()
    {
        return ImmutableList.copyOf(basicFlowers);
    }
    
    public static Collection<ItemStack> getDiggerBackPackItems()
    {
        return ImmutableList.copyOf(backpackDigger);
    }
    
    public static Collection<ItemStack> getForesterBackPackItems()
    {
        return ImmutableList.copyOf(backpackForester);
    }
    
    public static Collection<ItemStack> getLeaves()
    {
        return ImmutableList.copyOf(leaves);
    }
    
    public static Collection<ItemStack> getSaplings()
    {
        return ImmutableList.copyOf(saplings);
    }
    
    public static boolean isGermling(ItemStack stack)
    {
        // To enforce uniqueness in the List (should be a Set, but
        // ItemStack.equals() is not defined. -.-
        for (final ItemStack is : germlings)
            if (ItemStack.areItemStacksEqual(is, stack))
                return true;
        return false;
    }
    
    public static void registerBasicFlower(ItemStack stack)
    {
        
        // To enforce uniqueness in the List (should be a Set, but
        // ItemStack.equals() is not defined. -.-
        for (final ItemStack is : basicFlowers)
            if (ItemStack.areItemStacksEqual(is, stack))
                return;
        
        basicFlowers.add(stack);
    }
    
    public static void registerGermling(ItemStack stack)
    {
        
        // To enforce uniqueness in the List (should be a Set, but
        // ItemStack.equals() is not defined. -.-
        for (final ItemStack is : germlings)
            if (ItemStack.areItemStacksEqual(is, stack))
                return;
        
        germlings.add(stack);
    }
    
    public static void registerLeaves(ItemStack stack)
    {
        
        // To enforce uniqueness in the List (should be a Set, but
        // ItemStack.equals() is not defined. -.-
        for (final ItemStack is : leaves)
            if (ItemStack.areItemStacksEqual(is, stack))
                return;
        
        leaves.add(stack);
    }
    
    public static void registerSapling(ItemStack stack)
    {
        
        // To enforce uniqueness in the List (should be a Set, but
        // ItemStack.equals() is not defined. -.-
        for (final ItemStack is : saplings)
            if (ItemStack.areItemStacksEqual(is, stack))
                return;
        
        saplings.add(stack);
    }
    
}
