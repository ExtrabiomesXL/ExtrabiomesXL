/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.configuration;

import java.io.File;
import java.util.TreeMap;

import net.minecraftforge.common.Property;

public class ExtrabiomesConfig extends EnhancedConfiguration {

	public static final String			CATEGORY_BIOME			= "biome";
	public static final String			CATEGORY_MODULE_CONTROL	= "module_control";

	public TreeMap<String, Property>	biomeProperties			= new TreeMap<String, Property>();
	public TreeMap<String, Property>	moduleControlProperties	= new TreeMap<String, Property>();

	public ExtrabiomesConfig(File file) {
		super(file);
		categories.put(CATEGORY_BIOME, biomeProperties);
		categories
				.put(CATEGORY_MODULE_CONTROL, moduleControlProperties);
	}
}
