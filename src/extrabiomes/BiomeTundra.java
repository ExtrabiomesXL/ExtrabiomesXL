package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;

public class BiomeTundra extends BiomeBase {

	private static final String NAME = "Tundra";
	private static final Extrabiome BIOME = Extrabiome.TUNDRA;

	public BiomeTundra() {
		super(BIOME);

		setColor(0x305A85);
		setBiomeName(NAME);
		temperature = 0.0F;
		rainfall = 0.0F;
		minHeight = 0.0F;
		maxHeight = 0.2F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setFlowersPerChunk(0)
				.setGrassPerChunk(0).setSandPerChunk(0, 0);
	}

}
