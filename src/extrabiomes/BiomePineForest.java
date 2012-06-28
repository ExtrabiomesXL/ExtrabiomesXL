package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenTaiga;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomePineForest extends BiomeBase {

	private static final String NAME = "Pine Forest";
	private static final Extrabiome BIOME = Extrabiome.PINE_FOREST;

	public BiomePineForest() {
		super(BIOME);

		setColor(0x469C7E);
		setBiomeName(NAME);
		temperature = 0.4F;
		rainfall = 0.6F;
		minHeight = 0.1F;
		maxHeight = 0.3F;

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(10)
		.setGrassPerChunk(1);
	}

}
