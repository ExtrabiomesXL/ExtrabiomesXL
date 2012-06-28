package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenTaiga;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomePineForest extends BiomeGenTaiga {

	private static final Extrabiome biome = Extrabiome.PINE_FOREST;

	public BiomePineForest(int par1) {
		super(par1);

		setColor(0x469C7E);
		setBiomeName("Pine Forest");
		temperature = 0.4F;
		rainfall = 0.6F;
		minHeight = 0.1F;
		maxHeight = 0.3F;

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome);
	}

}
