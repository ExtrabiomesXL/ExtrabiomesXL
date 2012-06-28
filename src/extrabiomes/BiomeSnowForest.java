package extrabiomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;
import extrabiomes.api.Extrabiome;

public class BiomeSnowForest extends BiomeBase {

	private static final String NAME = "Snow Forest";
	private static final Extrabiome BIOME = Extrabiome.SNOW_FOREST;

	public BiomeSnowForest() {
		super(BIOME);

		setColor(0x5BA68D);
		setBiomeName(NAME);
		temperature = 0.0F;
		rainfall = 0.2F;
		minHeight = 0.1F;
		maxHeight = 0.5F;
		setEnableSnow();

		spawnableCreatureList.add(new SpawnListEntry(
				net.minecraft.src.EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(8)
				.setFlowersPerChunk(1).setGrassPerChunk(4);
	}

}
