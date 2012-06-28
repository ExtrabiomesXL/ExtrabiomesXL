package extrabiomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.MapGenVillage;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.TerrainGenBlock;

public class BiomeMountainRidge extends BiomeBase {

	private static final String NAME = "Mountain Ridge";
	private static final Extrabiome BIOME = Extrabiome.MOUNTAIN_RIDGE;
	private static final float MAX_HEIGHT = 1.7F;
	private static final float MIN_HEIGHT = 1.7F;
	private static final float RAINFALL = 0.0F;
	private static final float TEMPERATURE = 2.0F;
	private static final int COLOR = 0xC4722F;

	public BiomeMountainRidge() {
		super(BIOME);
		setProperties();
		spawnableCreatureList.clear();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setBrownGrassPerChunk(100)
				.setBrownGrassShortPerChunk(100).setTinyCactiPerChunk(10)
				.setOasisPerChunk(999).setGrassPerChunk(0).setTreesPerChunk(0);
	}

	private void setProperties() {
		setColor(COLOR);
		setBiomeName(NAME);
		temperature = TEMPERATURE;
		rainfall = RAINFALL;
		minHeight = MIN_HEIGHT;
		maxHeight = MAX_HEIGHT;
		disableRain();
	}
}
