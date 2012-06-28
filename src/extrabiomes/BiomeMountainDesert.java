package extrabiomes;

import java.lang.reflect.Field;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.BiomeGenDesert;
import net.minecraft.src.MapGenVillage;
import extrabiomes.api.Extrabiome;

public class BiomeMountainDesert extends BiomeGenDesert {

	private static final Extrabiome biome = Extrabiome.MOUNTAIN_DESERT;

	public BiomeMountainDesert(int par1) {
		super(par1);
		setColor(0xFA9418);
		setBiomeName("Mountainous Desert");
		temperature = 2.0F;
		rainfall = 0.0F;
		minHeight = 0.4F;
		maxHeight = 1.4F;
		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);
		setDisabledRain();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome);
	}

	void setDisabledRain() {
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
