/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.ic2;

import java.lang.reflect.Method;

import net.minecraft.world.biome.BiomeGenBase;

import com.google.common.base.Optional;

class IC2API {

	/**
	 * public static void addBiomeBonus(BiomeGenBase biome, int
	 * humidityBonus, int nutrientsBonus);
	 */
	private Optional<Method>	addBiomeBonus		= Optional.absent();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	IC2API() {
		Class cls;
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
}
