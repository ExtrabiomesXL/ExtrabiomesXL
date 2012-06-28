package extrabiomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.MapGenVillage;
import extrabiomes.api.Extrabiome;

public class BiomeShrubland extends BiomeBase {

	private static final String NAME = "Shrubland";
	private static final Extrabiome BIOME = Extrabiome.SHRUBLAND;

	public BiomeShrubland() {
		super(BIOME);

		setColor(0x51B57D);
		setBiomeName(NAME);
		temperature = 0.4F;
		rainfall = 0.6F;
		minHeight = 0.1F;
		maxHeight = 0.3F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(0)
				.setFlowersPerChunk(3).setGrassPerChunk(0);
	}

}
