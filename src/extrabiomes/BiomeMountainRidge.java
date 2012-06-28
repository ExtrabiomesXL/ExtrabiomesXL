package extrabiomes;

import java.lang.reflect.Field;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.TerrainGenBlock;

public class BiomeMountainRidge extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.MOUNTAIN_RIDGE;

	private static final String DISPLAY_NAME = "Mountain Ridge";
	private static final float MAX_HEIGHT = 1.7F;
	private static final float MIN_HEIGHT = 1.7F;
	private static final float RAINFALL = 0.0F;
	private static final float TEMPERATURE = 2.0F;
	private static final int COLOR = 0xC4722F;

	public BiomeMountainRidge(final int id) {
		super(id);
		setProperties();
		spawnableCreatureList.clear();
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.MOUNTAIN_RIDGE)
				.setBrownGrassPerChunk(100).setBrownGrassShortPerChunk(100)
				.setTinyCactiPerChunk(10).setOasisPerChunk(999)
				.setGrassPerChunk(0).setTreesPerChunk(0);
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

	private void setProperties() {
		setColor(COLOR);
		setBiomeName(DISPLAY_NAME);
		temperature = TEMPERATURE;
		rainfall = RAINFALL;
		minHeight = MIN_HEIGHT;
		maxHeight = MAX_HEIGHT;
		MetaBlock redRock = BlockControl.INSTANCE
				.getTerrainGenBlock(TerrainGenBlock.RED_ROCK);
		topBlock = (byte) redRock.blockId();
		fillerBlock = (byte) redRock.blockId();
		setDisabledRain();
	}
}
