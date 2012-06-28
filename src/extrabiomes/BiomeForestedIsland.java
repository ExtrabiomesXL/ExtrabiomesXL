package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomeForestedIsland extends BiomeBase {

	private static final String NAME = "Forested Island";
	private static final  Extrabiome BIOME = Extrabiome.FORESTED_ISLAND;

	public BiomeForestedIsland() {
		super(BIOME);

		setProperties();

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(7)
				.setGrassPerChunk(3);
	}

	private void setProperties() {
		setColor(0x62BF6C);
		setBiomeName(NAME);
		temperature = 0.4F;
		rainfall = 0.7F;
		minHeight = -0.8F;
		maxHeight = 0.8F;
	}

}
