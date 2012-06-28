package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;

public class BiomeTundra extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.TUNDRA;

	public BiomeTundra(int id) {
		super(id);

		setColor(0x305A85);
		setBiomeName("Tundra");
		temperature = 0.0F;
		rainfall = 0.0F;
		minHeight = 0.0F;
		maxHeight = 0.2F;
		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome).setFlowersPerChunk(0)
				.setGrassPerChunk(0).setSandPerChunk(0, 0);
	}

}
