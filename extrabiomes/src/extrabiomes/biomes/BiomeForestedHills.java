package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeForestedHills extends ExtrabiomeGenBase {

	public BiomeForestedHills(int id) {
		super(id);

		setBiomeName("Forested Hills");

		temperature = BiomeGenBase.forest.temperature - 0.1F;
		rainfall = BiomeGenBase.forest.rainfall;

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(7)
				.flowersPerChunk(1).grassPerChunk(5).build();
	}

}
