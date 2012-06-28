package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenTaiga;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomeMountainTaiga extends BiomeGenTaiga {

	private static final Extrabiome biome = Extrabiome.MOUNTAIN_TAIGA;

	public BiomeMountainTaiga(int id) {
		super(id);

		setColor(0xB6659);
		setBiomeName("Mountain Taiga");
		temperature = 0.0F;
		rainfall = 0.2F;
		minHeight = 0.3F;
		maxHeight = 1.2F;

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.MOUNTAIN_TAIGA);
	}

}
