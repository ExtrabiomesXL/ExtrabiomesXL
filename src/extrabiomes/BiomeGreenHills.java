package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.MapGenVillage;

public class BiomeGreenHills extends BiomeBase {

	private static final String NAME = "Green Hills";
	private static final Extrabiome BIOME = Extrabiome.GREEN_HILLS;

	public BiomeGreenHills() {
		super(BIOME);

		setColor(0x68C474);
		setBiomeName(NAME);
		temperature = 0.7F;
		rainfall = 0.8F;
		minHeight = 0.6F;
		maxHeight = 1.2F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME)
				.setTreesPerChunk(1).setOrangeFlowerPerChunk(1)
				.setWhiteFlowerPerChunk(1);
	}

	@Override
	public int getBiomeFoliageColor() {
			return ColorizerFoliage.getFoliageColor(1.0, 1.0);
	}

	@Override
	public int getBiomeGrassColor() {
		return ColorizerGrass.getGrassColor(1.0, 1.0);
	}

}
