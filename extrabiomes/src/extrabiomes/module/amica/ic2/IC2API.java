/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.ic2;

import java.lang.reflect.Method;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ItemStack;

import com.google.common.base.Optional;

class IC2API {

	/**
	 * public void addCraftingRecipe(ItemStack result, Object[] args);
	 */
	private Optional<Method>	addCraftingRecipe	= Optional.absent();

	/**
	 * public static ItemStack getItem(String name);
	 */
	private Optional<Method>	getItem				= Optional.absent();

	/**
	 * public static void addBiomeBonus(BiomeGenBase biome, int
	 * humidityBonus, int nutrientsBonus);
	 */
	private Optional<Method>	addBiomeBonus		= Optional.absent();

	IC2API() {
		Class cls;
		try {
			cls = Class.forName("ic2.api.Ic2Recipes");
			addCraftingRecipe = Optional.fromNullable(cls.getMethod(
					"addCraftingRecipe", ItemStack.class,
					Object[].class));
		} catch (final Exception e) {
			e.printStackTrace();
			addCraftingRecipe = Optional.absent();
		}

		try {
			cls = Class.forName("ic2.api.Items");
			getItem = Optional.fromNullable(cls.getMethod("getItem",
					String.class));
		} catch (final Exception e) {
			e.printStackTrace();
			getItem = Optional.absent();
		}

		try {
			cls = Class.forName("ic2.api.Crops");
			addBiomeBonus = Optional.fromNullable(cls.getMethod(
					"addBiomeBonus", BiomeGenBase.class, Integer.TYPE,
					Integer.TYPE));
		} catch (final Exception e) {
			e.printStackTrace();
			addBiomeBonus = Optional.absent();
		}
	}

	void addBiomeBonus(BiomeGenBase biome, int humidityBonus,
			int nutrientsBonus)
	{
		try {
			addBiomeBonus.get().invoke(null, biome, humidityBonus,
					nutrientsBonus);
		} catch (final IllegalStateException e) {} catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	void addBiomeBonus(Optional<? extends BiomeGenBase> biome,
			int humidityBonus, int nutrientsBonus)
	{
		if (biome.isPresent())
			addBiomeBonus(biome.get(), humidityBonus, nutrientsBonus);
	}

	void addCraftingRecipe(ItemStack result, Object[] args) {
		try {
			addCraftingRecipe.get().invoke(null, result, args);
		} catch (final IllegalStateException e) {} catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	Optional<ItemStack> getItem(String name) {
		try {
			return Optional.fromNullable((ItemStack) getItem.get()
					.invoke(null, name));
		} catch (final IllegalStateException e) {} catch (final Exception e)
		{
			e.printStackTrace();
		}
		return Optional.absent();
	}
}
