/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import java.io.File;
import java.util.TreeMap;

import net.minecraftforge.common.Property;
import extrabiomes.utility.ConfigFile;

public class ExtrabiomesConfig extends ConfigFile {

	public static final String			CATEGORY_BIOME	= "biome";

	public TreeMap<String, Property>	biomeProperties	= new TreeMap<String, Property>();

	public ExtrabiomesConfig(File file) {
		super(file);
		categories.put(CATEGORY_BIOME, biomeProperties);
	}
}
