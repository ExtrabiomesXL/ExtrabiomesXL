/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.configuration;

import java.io.File;
import java.util.Map;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraftforge.common.Property;

public class ExtrabiomesConfig extends EnhancedConfiguration {

	public static final String	CATEGORY_BIOME			= "biome";
	public static final String	CATEGORY_MODULE_CONTROL	= "module_control";

	private boolean				configBiomes[]			= null;

	public ExtrabiomesConfig(File file) {
		super(file);
	}

	public Property getBiome(String key, int defaultId) {
		if (configBiomes == null) {
			configBiomes = new boolean[BiomeGenBase.biomeList.length];

			for (int i = 0; i < configBiomes.length; ++i)
				configBiomes[i] = false;
		}

		final Map<String, Property> properties = categories
				.get(CATEGORY_BIOME);
		if (properties.containsKey(key)) {
			final Property property = get(CATEGORY_BIOME, key,
					defaultId);
			configBiomes[Integer.parseInt(property.value)] = true;
			return property;
		} else {
			final Property property = new Property();
			properties.put(key, property);
			property.setName(key);

			if (BiomeGenBase.biomeList[defaultId] == null
					&& !configBiomes[defaultId])
			{
				property.value = Integer.toString(defaultId);
				configBiomes[defaultId] = true;
				return property;
			} else {
				for (int j = configBiomes.length - 1; j >= 0; --j)
					if (Block.blocksList[j] == null && !configBiomes[j])
					{
						property.value = Integer.toString(j);
						configBiomes[j] = true;
						return property;
					}

				throw new RuntimeException(
						"No more biome ids available for " + key);
			}
		}
	}
}
