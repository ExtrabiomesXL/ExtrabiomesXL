package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomeSnowForest extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.SNOW_FOREST;

	public BiomeSnowForest(int id) {
		super(id);

		setColor(0x5BA68D);
		setBiomeName("Snow Forest");
		temperature = 0.0F;
		rainfall = 0.2F;
		minHeight = 0.1F;
		maxHeight = 0.5F;
		setEnableSnow();

		spawnableCreatureList.add(new SpawnListEntry(
				net.minecraft.src.EntityWolf.class, 5, 4, 4));
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.SNOW_FOREST).setTreesPerChunk(8).setFlowersPerChunk(1).setGrassPerChunk(4);
	}

}
