package extrabiomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.MapGenVillage;
import extrabiomes.api.Extrabiome;

public class BiomeMeadow extends BiomeBase {

	private static final String NAME = "Meadow";
	private static final Extrabiome BIOME = Extrabiome.MEADOW;

	public BiomeMeadow() {
		super(BIOME);
		setBiomeName(NAME);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setFlowersPerChunk(9)
				.setGrassPerChunk(12).setTreesPerChunk(0);
	}

}
