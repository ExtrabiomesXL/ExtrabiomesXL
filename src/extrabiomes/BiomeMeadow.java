package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;

public class BiomeMeadow extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.MEADOW;

	public BiomeMeadow(int id) {
		super(id);
		setBiomeName("Meadow");
		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome)
				.setFlowersPerChunk(9).setGrassPerChunk(12).setTreesPerChunk(0);
	}

}
