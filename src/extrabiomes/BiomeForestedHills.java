package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomeForestedHills extends BiomeBase {

	private static final String NAME = "Forested Hills";
	private static final Extrabiome BIOME = Extrabiome.FORESTED_HILLS;

	public BiomeForestedHills() {
		super(BIOME);

		setBiomeName(NAME);

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(7)
				.setFlowersPerChunk(1).setGrassPerChunk(5);
	}

}
