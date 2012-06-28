package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomeForestedIsland extends BiomeGenBase {

	private static final  Extrabiome biome = Extrabiome.FORESTED_ISLAND;

	public BiomeForestedIsland(int id) {
		super(id);

		setColor(0x62BF6C);
		setBiomeName("Forested Island");
		temperature = 0.4F;
		rainfall = 0.7F;
		minHeight = -0.8F;
		maxHeight = 0.8F;

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));

		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.FORESTED_ISLAND).setTreesPerChunk(7)
				.setGrassPerChunk(3);
	}

}
