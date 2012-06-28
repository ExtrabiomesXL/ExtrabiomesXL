package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomeForestedHills extends BiomeGenBase {

	private static final  Extrabiome biome = Extrabiome.FORESTED_HILLS;
	
	public BiomeForestedHills(int id) {
		super(id);
		
		setBiomeName("Forested Hills");

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.FORESTED_HILLS).setTreesPerChunk(7)
				.setFlowersPerChunk(1).setGrassPerChunk(5);
	}

}
