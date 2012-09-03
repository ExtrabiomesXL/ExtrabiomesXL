/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashSet;

import net.minecraft.src.BiomeGenBase;

public class ExtrabiomeGenBase extends BiomeGenBase {

	public static Collection<BiomeGenBase>	customBiomeList	= new LinkedHashSet<BiomeGenBase>();

	protected ExtrabiomeGenBase(int id) {
		super(id);
		customBiomeList.add(this);
	}

	protected void disableRain() {
		Field enabledRainField;
		try {
			enabledRainField = BiomeGenBase.class.getDeclaredField("S"); // enableRain
			enabledRainField.setAccessible(true);
			enabledRainField.setBoolean(this, false);
		} catch (final Throwable e) {
			// Do nothing... (This is NOT critical)
		}
	}
}
