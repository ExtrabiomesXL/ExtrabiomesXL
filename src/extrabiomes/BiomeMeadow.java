package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;

public class BiomeMeadow extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.MEADOW;

	public BiomeMeadow(int id) {
		super(id);
		MapGenVillage.villageSpawnBiomes.add(this);
		setBiomeName("Meadow");
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.MEADOW)
				.setFlowersPerChunk(9).setGrassPerChunk(12).setTreesPerChunk(0);
	}

}
