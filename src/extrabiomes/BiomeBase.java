package extrabiomes;

import java.lang.reflect.Field;

import extrabiomes.api.Extrabiome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;

public class BiomeBase extends BiomeGenBase {
	protected BiomeBase(Extrabiome biome) {
		super(Options.getId(biome));
	}

	protected void disableRain() {
		Field enabledRainField;
		try {
			enabledRainField = BiomeGenBase.class
					.getDeclaredField("field_27079_v"); // enabled rain
			enabledRainField.setAccessible(true);
			enabledRainField.setBoolean(this, false);
		} catch (Throwable e) {
			// Do nothing... (This is NOT critical)
		}
	}
}
