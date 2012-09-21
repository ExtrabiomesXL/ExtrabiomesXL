/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.utility;

import java.util.Collection;
import java.util.Random;

import net.minecraft.src.WeightedRandomItem;

import com.google.common.base.Optional;

public class WeightedRandomChooser {
	/**
	 * Returns a random choice from the input items.
	 */
	public static <T extends WeightedRandomItem> Optional<T> getRandomItem(
			Random rand, Collection<T> collection)
	{
		return getRandomItem(rand, collection,
				getTotalWeight(collection));
	}

	/**
	 * Returns a random choice from the input items, with a total weight
	 * value.
	 */
	public static <T extends WeightedRandomItem> Optional<T> getRandomItem(
			Random rand, Collection<T> collection, int limit)
	{
		if (limit > 0) {

			int choice = rand.nextInt(limit);

			for (final T item : collection) {
				choice -= item.itemWeight;
				if (choice < 0) return Optional.of(item);
			}
		}
		return Optional.absent();
	}

	/**
	 * Returns the total weight of all items in a collection.
	 */
	public static int getTotalWeight(
			Collection<? extends WeightedRandomItem> collection)
	{
		int totalWeight = 0;

		for (final WeightedRandomItem item : collection)
			totalWeight += item.itemWeight;

		return totalWeight;
	}
}
