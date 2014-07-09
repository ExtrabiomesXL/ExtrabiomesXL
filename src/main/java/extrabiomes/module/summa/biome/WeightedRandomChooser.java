/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.util.Collection;
import java.util.Random;

import net.minecraft.util.WeightedRandom.Item;

import com.google.common.base.Optional;

public enum WeightedRandomChooser
{
    INSTANCE;
    
    public static <T extends Item> Optional<T> getRandomItem(Random rand, Collection<T> collection)
    {
        return getRandomItem(rand, collection, getTotalWeight(collection));
    }
    
    static <T extends Item> Optional<T> getRandomItem(Random rand, Collection<T> collection, int limit)
    {
        if (limit > 0)
        {
            int choice = rand.nextInt(limit);
            
            for (final T item : collection)
            {
                choice -= item.itemWeight;
                if (choice < 0)
                    return Optional.of(item);
            }
        }
        
        return Optional.absent();
    }
    
    public static int getTotalWeight(Collection<? extends Item> collection)
    {
        int totalWeight = 0;
        
        for (final Item item : collection)
        {
            totalWeight += item.itemWeight;
        }
        
        return totalWeight;
    }
}
