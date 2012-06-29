
package extrabiomes.biomes;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashSet;

import net.minecraft.server.BiomeBase;

public class ExtrabiomeGenBase extends BiomeBase {
	public static Collection	customBiomeList	= new LinkedHashSet();

	protected ExtrabiomeGenBase(int i) {
		super(i);
		customBiomeList.add(this);
	}

	protected void disableRain() {
		try {
			final Field field = net.minecraft.server.BiomeBase.class
					.getDeclaredField("S");
			field.setAccessible(true);
			field.setBoolean(this, false);
		} catch (final Throwable throwable) {}
	}
}
