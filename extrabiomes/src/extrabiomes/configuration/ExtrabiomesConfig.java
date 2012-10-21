/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.configuration;

import java.io.File;

import net.minecraft.src.BiomeGenBase;
import net.minecraftforge.common.Property;

public class ExtrabiomesConfig extends EnhancedConfiguration {

	public static final String	CATEGORY_BIOME			= "biome";
	public static final String	CATEGORY_MODULE_CONTROL	= "module_control";

	private final boolean		configBiomes[]			= null;

	public ExtrabiomesConfig(File file) {
		super(file);
	}

	public Property getBiome(String key, int defaultID) {
		return getBiome(CATEGORY_BIOME, key, defaultID);
	}

	public Property getBiome(String category, String key, int defaultID)
	{
		final Property prop = get(category, key, -1);

		if (prop.getInt() != -1) {
			configBiomes[prop.getInt()] = true;
			return prop;
		}

		if (BiomeGenBase.biomeList[defaultID] == null
				&& !configBiomes[defaultID])
		{
			prop.value = Integer.toString(defaultID);
			configBiomes[defaultID] = true;
			return prop;
		}

		for (int j = configBiomes.length - 1; j > 0; j--)
			if (BiomeGenBase.biomeList[j] == null && !configBiomes[j]) {
				prop.value = Integer.toString(j);
				configBiomes[j] = true;
				return prop;
			}

		throw new RuntimeException("No more biome ids available for "
				+ key);
	}
}
